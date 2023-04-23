package dev.flight_app.entities;

public enum Airline {
    LUFTHANSA_GROUP("LF"),
    AIR_FRANCE_KLM("AF"),
    INTERNATIONAL_AIRLINES_GROUP("IA"),
    RYANAIR("RY"),
    TURKISH_AIRLINES("TA"),
    EASYJET("EA"),
    WIZZ_AIR("WA"),
    SAS_GROUP("SG"),
    TUI_AIRWAYS("TU"),
    TAP_AIR_PORTUGAL("TP"),
    PEGASUS_AIRLINES("PG"),
    LOT_POLISH_AIRLINES("LP"),
    JET2_COM("JC"),
    FINNAIR("FN"),
    AEGEAN_AIRLINES("AG"),
    NORWEGIAN_AIR_SHUTTLE_ASA("NA"),
    SMARTWINGS("SM"),
    CONDOR("CN"),
    AIR_EUROPA("EU"),
    UKRAINE_INTERNATIONAL_AIRLINES("UA"),
    AIR_MALTA("AM");
    private final String code;

    Airline(String code) {
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
