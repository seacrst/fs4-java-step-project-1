package dev.flight_app.entity;

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
    VIRGIN_ATLANTIC("VA"),
    AIR_ASTANA("AS"),
    AIRBALTIC("AB"),
    ICELANDAIR_GROUP("IC"),
    AZERBAIJAN_AIRLINES("AZ"),
    TAROM("TR"),
    VOLOTEA("VL"),
    AIR_SERBIA("SB"),
    ENTER_AIR("EN"),
    SKY_EXPRESS("SE"),
    LUXAIR("LX"),
    SKYUP("KY"),
    CROATIA_AIRLINES("CR"),
    WINDROSE("WR"),
    BULGARIA_AIR("BA"),
    AIR_MALTA("AM");
    private final String code;

    Airline(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
