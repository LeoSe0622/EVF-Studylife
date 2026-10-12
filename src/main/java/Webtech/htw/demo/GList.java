package Webtech.htw.demo;

public class GList {

    private String name;
    private boolean done;
    private int anzahl;

        public GList(String name, int anzahl) {
            this.name = name;
            this.anzahl = anzahl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        public int getAnzahl(){ return anzahl;}

        public void setAnzahl(int anzahl){ this.anzahl=anzahl;}
    }

