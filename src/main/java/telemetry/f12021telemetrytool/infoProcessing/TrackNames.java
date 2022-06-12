package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит названия трасс
 */

public enum TrackNames {

    AUSTRALIA("Albert Park Circuit"),
    FRANCE("Paul Ricard Circuit"),
    CHINA("Shanghai International Circuit"),
    BAHRAIN("Bahrain International Circuit"),
    SPAIN("Circuit de Barcelona-Catalunya"),
    MONACO("Circuit de Monaco"),
    CANADA("Circuit Gilles Villeneuve"),
    GREATBRITAIN("Silverstone Circuit"),
    HUNGARY("Hungaroring"),
    BELGIUM("Circuit de Spa-Francorchamps"),
    MONZA("Autodromo Nazionale di Monza"),
    SINGAPORE("Marina Bay Street Circuit"),
    JAPAN("Suzuka International Racing Course"),
    ABUDHABI("Yas Marina Circuit"),
    USA("Circuit of the Americas"),
    BRAZIL("Autodromo Jose Carlos Pace"),
    AUSTRIA("Red Bull Ring"),
    RUSSIA("Sochi Autodrom"),
    MEXICO("Autodromo Hermanos Rodriguez"),
    AZERBAIJAN("Baku City Circuit"),
    NETHERLANDS("Circuit Zandvoort"),
    IMOLA("Autodromo Enzo e Dino Ferrari"),
    PORTUGAL("Autodromo Internacional do Algarve"),
    SAUDIARABIA("Jeddah Street Circuit");

    public final String trackName;

    TrackNames(String trackName) {
        this.trackName = trackName;
    }
}
