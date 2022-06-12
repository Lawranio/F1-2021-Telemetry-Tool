package telemetry.f12021telemetrytool.view;

/**
 * Назначение:
 *      Класс-таблица для интерфейса гонки
 */

public class RaceTable {

    public int getCarPosition() {
        return carPosition;
    }

    public String getName() {
        return name;
    }

    public String getTyre() {
        return tyre;
    }

    public String getDelta() {
        return delta;
    }

    public String getSector1() {
        return sector1;
    }

    public String getSector2() {
        return sector2;
    }

    public String getSector3() {
        return sector3;
    }

    public String getLastLap() {
        return lastLap;
    }

    public int getWarning() {
        return warning;
    }

    public int getPenalty() {
        return penalty;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    int carPosition;
    String name;
    String delta;
    String tyre;
    String sector1;
    String sector2;
    String sector3;
    String lastLap;
    int warning;
    int penalty;
    String driverStatus;
    String resultStatus;

    public RaceTable(int carPosition,
                     String name,
                     String delta,
                     String tyre,
                     String sector1,
                     String sector2,
                     String sector3,
                     String lastLap,
                     int warning,
                     int penalty,
                     String driverStatus,
                     String resultStatus) {
        this.carPosition = carPosition;
        this.name = name;
        this.tyre = tyre;
        this.delta = delta;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.sector3 = sector3;
        this.lastLap = lastLap;
        this.warning = warning;
        this.penalty = penalty;
        this.driverStatus = driverStatus;
        this.resultStatus = resultStatus;
    }
}
