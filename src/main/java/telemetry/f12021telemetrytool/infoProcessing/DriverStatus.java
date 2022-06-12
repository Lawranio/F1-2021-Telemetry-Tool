package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит статус пилота
 */

public enum DriverStatus {

    PIT("В боксах"),
    FLYINGLAP("Быстрый круг"),
    INLAP("Круг заезда"),
    OUTLAP("Круг выезда"),
    ONTRACK("На трассе");

    public final String driverStatus;

    DriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }
}
