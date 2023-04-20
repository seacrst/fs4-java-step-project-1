package dev.flight_app.events;

public record Selector(String prompt) {
    public Selector(String prompt, String triggerValue) {
        this(prompt);
    }
    public Selector(String prompt, int triggerValue) {
        this(prompt);
    }
}
