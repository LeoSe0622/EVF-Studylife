package Webtech.htw.demo.buisness.service;

import org.springframework.stereotype.Service;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;

@Service
public class GeminiService {
    // Verbindung zu Gemini, wird einmal beim Start erstellt
    private final Client client;

    // Name des Modells, z. B. "gemini-3.1-flash-lite"
    private final String model;

    // Spring ruft diesen Konstruktor beim Start auf und
    // setzt die Werte aus application.properties ein
    public GeminiService(@Value("${gemini.api-key}") String apiKey,
                         @Value("${gemini.model}") String model) {
        this.client = Client.builder().apiKey(apiKey).build();
        this.model = model;
    }

    // Schickt den Prompt an Gemini und gibt die Textantwort zurück
    public String ask(String prompt) {
        GenerateContentResponse response = client.models.generateContent(model, prompt, null);
        return response.text();
    }
}
