package org.example.app;

public class Transition {
    private String source;
    private String destination;
    private String action;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Transition   [source=" + source + " destination=" + destination + " action=" + action + "]";
    }

}

