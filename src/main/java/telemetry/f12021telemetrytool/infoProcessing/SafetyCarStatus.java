package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит иконки машины безопасности
 */

public enum SafetyCarStatus {

    FORMATIONLAP("assets\\safetycar\\formation_lap.png"),
    FULL("assets\\safetycar\\safety_car.png"),
    VIRTUAL("assets\\safetycar\\virtual_safety_car.png");

    public final String safetyCarStatus;

    SafetyCarStatus(String safetyCarStatus) {
        this.safetyCarStatus = safetyCarStatus;
    }
}
