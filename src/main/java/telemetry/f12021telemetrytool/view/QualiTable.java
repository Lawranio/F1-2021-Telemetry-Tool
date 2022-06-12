package telemetry.f12021telemetrytool.view;

/**
 * Назначение:
 *      Класс-таблица для интерфейса квалификации
 */

public class QualiTable {

    public int getCarPosition() {
        return carPosition;
    }

    public String getName() {
        return name;
    }

    public String getFastestLap() {
        return fastestLap;
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

    public String getTyreOnFastestLap() {
        return tyreOnFastestLap;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    int carPosition;
    String name;
    String fastestLap;
    String delta;
    String sector1;
    String sector2;
    String sector3;
    String tyreOnFastestLap;
    String driverStatus;
    String resultStatus;

    public QualiTable(int carPosition,
                      String name,
                      String fastestLap,
                      String delta,
                      String sector1,
                      String sector2,
                      String sector3,
                      String tyreOnFastestLap,
                      String driverStatus,
                      String resultStatus) {
        this.carPosition = carPosition;
        this.name = name;
        this.fastestLap = fastestLap;
        this.delta = delta;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.sector3 = sector3;
        this.tyreOnFastestLap = tyreOnFastestLap;
        this.driverStatus = driverStatus;
        this.resultStatus = resultStatus;
    }
}
