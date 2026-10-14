package Webtech.htw.demo;

public class Todolist {
    private String name;
    private boolean done = false;

    public Todolist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDone() {
        return done;
    }

    public void isDone(boolean done) {
        this.done = true;
    }

}
