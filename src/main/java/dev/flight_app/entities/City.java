package dev.flight_app.entities;

public enum City {
    AMSTERDAM("AMS"),
    ATHENS("ATH"),
    BELGRADE("BEL"),
    BERLIN("BER"),
    BERN("BEN"),
    BRATISLAVA("BRA"),
    BRUSSELS("BRU"),
    BUDAPEST("BUD"),
    BUCHAREST("BUC"),
    VADUZ("VAD"),
    VALLETTA("VAL"),
    WARSAW("WRS"),
    VIENNA("VIE"),
    VILNIUS("VIL"),
    DUBLIN("DUB"),
    ZAGREB("ZAG"),
    KYIV("KYV"),
    CHISINAU("CHI"),
    COPENHAGEN("COP"),
    LISBON("LIS"),
    LONDON("LON"),
    HELSINKI("HLS");
    private final String code;

    City(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name().replace("_"," ");
    }
}
