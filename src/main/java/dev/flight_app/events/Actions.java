package dev.flight_app.events;

public enum Actions {
    MyFlights(new Selector("1. Мої рейси", 1)),

    Booking(new Selector("2. Забронювати", 2)),
    Search(new Selector("3. Пошук", 3)),
    DisplayAll(new Selector("4. Показати всі рейси", 4)),
    DisplayOne(new Selector("5. Показати певний рейс", 5)),
    Cancel(new Selector("6. Відмінити", 6)),
    Exit(new Selector("0. Вихід", 0));

    private final Selector Selector;
    Actions(Selector sel) {
        Selector = sel;
    }

    public String get() {
        return Selector.prompt();
    }
}
