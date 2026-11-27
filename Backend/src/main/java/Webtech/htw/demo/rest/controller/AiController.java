package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.buisness.service.GeminiService;
import Webtech.htw.demo.buisness.service.ListenEintragEService;
import Webtech.htw.demo.persistence.entity.ListenEintragE;
import Webtech.htw.demo.rest.model.RAntwort;
import Webtech.htw.demo.rest.model.RezeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AiController {
    @Autowired
    ListenEintragEService listenService;

    @Autowired
    GeminiService geminiService;

    @PostMapping("/api/rezeptvorschlag")
    public ResponseEntity<RezeptDTO> rezeptVorschlagen(@RequestBody RezeptDTO request) {

        // 1. Vorräte aus der Datenbank holen
        List<ListenEintragE> vorraete = listenService.getListe("STOCK");

        // Ohne Vorräte brauchen wir Gemini gar nicht zu fragen
        if (vorraete.isEmpty()) {
            return ResponseEntity.ok(new RezeptDTO("Deine Vorratsliste ist leer. Füge zuerst Vorräte hinzu."));
        }

        // 2. Zutatenliste bauen, z. B. "- Nudeln (Menge: 2)"
        String zutaten = "";
        for (ListenEintragE eintrag : vorraete) {
            zutaten += "- " + eintrag.getArtikel().getName() + " (Menge: " + eintrag.getMenge() + ")\n";
        }

        // Wunsch des Nutzers holen, kann leer sein; auf 200 Zeichen kürzen
        String wunsch = "";
        if (request != null && request.text() != null) {
            wunsch = request.text().trim();
        }
        if (wunsch.length() > 200) {
            wunsch = wunsch.substring(0, 200);
        }
        // 3. Prompt zusammenbauen
        String prompt = "Du bist ein hilfreicher Kochassistent.\n"
                + "Das sind meine Vorräte:\n"
                + zutaten
                + "Schlage mir genau ein Rezept vor, das möglichst nur diese Vorräte nutzt. "
                + "Salz, Pfeffer, Öl und Wasser habe ich zusätzlich.\n"
                + "Antworte auf Deutsch als einfacher Text ohne Markdown, "
                + "mit Rezeptname, Zutaten mit Mengen und Zubereitungsschritten.\n";
        if (!wunsch.isEmpty()) {
            prompt += "Mein Wunsch: " + wunsch + "\n";
        }

        // 4. Gemini fragen, Fehler abfangen
        try {
            String antwort = geminiService.ask(prompt);

            // Gemini kann in seltenen Fällen keinen Text liefern
            if (antwort == null || antwort.isBlank()) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new RezeptDTO("Es konnte kein Rezept erstellt werden. Bitte versuche es nochmal."));
            }

            return ResponseEntity.ok(new RezeptDTO(antwort));
        } catch (Exception e) {
            // Fehler in der Konsole ausgeben, z. B. 503 "overloaded" oder 429 "zu viele Anfragen"
            System.out.println("Fehler bei Gemini: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new RezeptDTO("Der Rezeptvorschlag ist gerade nicht verfügbar. Bitte versuche es in einer Minute nochmal."));
        }
}
}
