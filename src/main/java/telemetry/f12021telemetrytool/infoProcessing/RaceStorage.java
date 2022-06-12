package telemetry.f12021telemetrytool.infoProcessing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import telemetry.f12021telemetrytool.packetHandler.*;
import telemetry.f12021telemetrytool.view.RaceTable;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Назначение:
 *      Промежуточное хранение информации гонки
 */

public class RaceStorage {

    ObservableList<RaceTable> raceTableList = FXCollections.observableArrayList();
    static ArrayList<RaceStorageInfo> raceStorageInfoList = new ArrayList<>();

    ArrayList<LapDataPacket.LapData> lapDataList;
    ArrayList<String> participantsNameList;
    ArrayList<CarTelemetryPacket.CarTelemetryData> carTelemetryDataList;
    ArrayList<CarStatusPacket.CarStatusData> carStatusDataList;

    // Переменные для временного хранения информации
    int numActiveCars;
    int raceStorageInfoListSize;

    public RaceStorage() {

        lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();
        participantsNameList = PacketReceiver.getParticipantsPacket().getParticipantsNameList();
        carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
        carStatusDataList = PacketReceiver.getCarStatusPacket().getCarStatusDataList();
        numActiveCars = PacketReceiver.getParticipantsPacket().getNumberActiveCars();

        for (int i = 0; i < numActiveCars; ++i) {

            raceStorageInfoList.add(new RaceStorageInfo(

                    lapDataList.get(i).getCarPosition(),
                    participantsNameList.get(i),
                    carStatusDataList.get(i).getTyreCompound(),
                    carStatusDataList.get(i).getTyresAge(),
                    lapDataList.get(i).getSector1Time(),
                    lapDataList.get(i).getSector2Time(),
                    lapDataList.get(i).getLastLapTime(),
                    lapDataList.get(i).getWarnings(),
                    lapDataList.get(i).getPenalties(),
                    lapDataList.get(i).getDriverStatus(),
                    lapDataList.get(i).getResultStatus(),
                    lapDataList.get(i).getTotalDistance(),
                    carTelemetryDataList.get(i).getSpeed()
            ));
        }

        raceStorageInfoListSize = raceStorageInfoList.size();
    }

    public ObservableList<RaceTable> getRaceTableList() throws InterruptedException {

        raceTableList.clear();

        lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();
        participantsNameList = PacketReceiver.getParticipantsPacket().getParticipantsNameList();
        carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
        carStatusDataList = PacketReceiver.getCarStatusPacket().getCarStatusDataList();
        numActiveCars = PacketReceiver.getParticipantsPacket().getNumberActiveCars();

        if (raceStorageInfoList.size() < numActiveCars)
            numActiveCars = raceStorageInfoList.size();

        //TODO: Обыграть ситацию, если пилот сошёл - remove index
        for (int i = 0; i < raceStorageInfoListSize; ++i) {

            raceStorageInfoList.get(i).carPosition = lapDataList.get(i).getCarPosition();
            raceStorageInfoList.get(i).name = participantsNameList.get(i);
            raceStorageInfoList.get(i).tyreCompound = carStatusDataList.get(i).getTyreCompound();
            raceStorageInfoList.get(i).tyresAge = carStatusDataList.get(i).getTyresAge();
            raceStorageInfoList.get(i).lastLap = lapDataList.get(i).getLastLapTime();

            if (lapDataList.get(i).getSector1Time() != 0)
                raceStorageInfoList.get(i).sector1Time = lapDataList.get(i).getSector1Time();

            if (lapDataList.get(i).getSector2Time() != 0)
                raceStorageInfoList.get(i).sector2Time = lapDataList.get(i).getSector2Time();

            raceStorageInfoList.get(i).warning = lapDataList.get(i).getWarnings();
            raceStorageInfoList.get(i).penalty = lapDataList.get(i).getPenalties();
            raceStorageInfoList.get(i).driverStatus = lapDataList.get(i).getDriverStatus();
            raceStorageInfoList.get(i).resultStatus = lapDataList.get(i).getResultStatus();
            raceStorageInfoList.get(i).totalDistance = lapDataList.get(i).getTotalDistance();
            raceStorageInfoList.get(i).speed = carTelemetryDataList.get(i).getSpeed();

            raceTableList.add(new RaceTable(

                    raceStorageInfoList.get(i).carPosition,
                    raceStorageInfoList.get(i).name,
                    InfoHandler.processRaceDelta(raceStorageInfoList.get(i).totalDistance, raceStorageInfoList.get(i).speed, raceStorageInfoList.get(i).carPosition, 0),
                    InfoHandler.processTyre(raceStorageInfoList.get(i).tyreCompound, raceStorageInfoList.get(i).tyresAge),
                    InfoHandler.processLapSectorTime(raceStorageInfoList.get(i).sector1Time),
                    InfoHandler.processLapSectorTime(raceStorageInfoList.get(i).sector2Time),
                    InfoHandler.processSector3Time(raceStorageInfoList.get(i).sector2Time, raceStorageInfoList.get(i).sector1Time, raceStorageInfoList.get(i).lastLap),
                    InfoHandler.processLapSectorTime(raceStorageInfoList.get(i).lastLap),
                    raceStorageInfoList.get(i).warning,
                    raceStorageInfoList.get(i).penalty,
                    InfoHandler.getDriverStatus(raceStorageInfoList.get(i).driverStatus),
                    InfoHandler.getResultStatus(raceStorageInfoList.get(i).resultStatus)
            ));
        }

        return raceTableList;
    }

    static class RaceStorageInfo {

        int carPosition;
        String name;
        int tyreCompound;
        int tyresAge;
        long lastLap;
        int sector1Time;
        int sector2Time;
        int warning;
        int penalty;
        int driverStatus;
        int resultStatus;
        float totalDistance;
        int speed;

        public RaceStorageInfo(int carPosition,
                               String name,
                               int tyreCompound,
                               int tyresAge,
                               int sector1Time,
                               int sector2Time,
                               long lastLap,
                               int warning,
                               int penalty,
                               int driverStatus,
                               int resultStatus,
                               float totalDistance,
                               int speed) {
            this.carPosition = carPosition;
            this.name = name;
            this.tyreCompound = tyreCompound;
            this.tyresAge = tyresAge;
            this.sector1Time = sector1Time;
            this.sector2Time = sector2Time;
            this.lastLap = lastLap;
            this.warning = warning;
            this.penalty = penalty;
            this.driverStatus = driverStatus;
            this.resultStatus = resultStatus;
            this.totalDistance = totalDistance;
            this.speed = speed;
        }
    }

}
