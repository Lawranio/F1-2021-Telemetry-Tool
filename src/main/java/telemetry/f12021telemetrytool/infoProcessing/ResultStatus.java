package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит статус результата
 */

public enum ResultStatus {

    INVALID("Недействительный"),
    INACTIVE("Неактивный"),
    ACTIVE("Активный"),
    FINISHED("Финишировал"),
    DIDNOTFINISH("Не финишировал"),
    DISQUALIFIED("Дисквалифицирован"),
    NOTCLASSIFIED("Неклассифицирован"),
    RETIRED("Сошёл");

    public final String resultStatus;

    ResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }
}
