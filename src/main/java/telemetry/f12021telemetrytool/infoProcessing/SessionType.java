package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит типы сессий
 */

public enum SessionType {

    Q1("Квалификация 1"),
    Q2("Квалификация 2"),
    Q3("Квалификация 3"),
    SHORT("Короткая квалификация"),
    OSQ("Разовая квалификация"),
    RACE("Гонка");

    public final String sessionType;

    SessionType(String sessionType) {
        this.sessionType = sessionType;
    }
}
