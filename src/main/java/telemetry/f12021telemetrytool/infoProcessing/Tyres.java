package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит типы резины
 */

public enum Tyres {

    INTER("Промежуточная"),
    WET("Дождевая"),
    SOFT("Софт"),
    MEDIUM("Медиум"),
    HARD("Хард");

    public final String tyreCompound;

    Tyres(String tyreCompound) {
        this.tyreCompound = tyreCompound;
    }
}
