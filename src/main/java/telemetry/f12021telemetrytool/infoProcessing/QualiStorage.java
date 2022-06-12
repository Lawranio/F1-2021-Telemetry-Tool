package telemetry.f12021telemetrytool.infoProcessing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import telemetry.f12021telemetrytool.packetHandler.CarStatusPacket;
import telemetry.f12021telemetrytool.packetHandler.LapDataPacket;
import telemetry.f12021telemetrytool.packetHandler.PacketReceiver;
import telemetry.f12021telemetrytool.packetHandler.SessionHistoryPacket;
import telemetry.f12021telemetrytool.view.QualiTable;

import java.util.ArrayList;

/**
 * Назначение:
 *      Промежуточное хранение информации квалификации
 */

//TODO: Поработать над инвалидными / валидными кругами
public class QualiStorage {

    ObservableList<QualiTable> qualiTableList = FXCollections.observableArrayList();
    ArrayList<QualiStorageInfo> qualiStorageInfoList = new ArrayList<>();

    ArrayList<LapDataPacket.LapData> lapDataList;
    ArrayList<String> participantsNameList;
    ArrayList<CarStatusPacket.CarStatusData> carStatusDataList;
    ArrayList<SessionHistoryPacket.LapHistoryData> lapHistoryDataList;

    // Переменные для временного хранения информации
    long lapTimeOld;
    long lapTimeNew;
    int numActiveCars;
    int carID;
    int numLapsHistory;
    int lapValid;
    int qualiStorageInfoListSize;

    public QualiStorage() {

        lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();
        participantsNameList = PacketReceiver.getParticipantsPacket().getParticipantsNameList();
        carStatusDataList = PacketReceiver.getCarStatusPacket().getCarStatusDataList();
        numActiveCars = PacketReceiver.getParticipantsPacket().getNumberActiveCars();

        for (int i = 0; i < numActiveCars; ++i) {

            qualiStorageInfoList.add(new QualiStorageInfo(

                    lapDataList.get(i).getCarPosition(),
                    participantsNameList.get(i),
                    1000000000,
                    0,
                    lapDataList.get(i).getSector1Time(),
                    lapDataList.get(i).getSector2Time(),
                    0,
                    carStatusDataList.get(i).getTyreCompound(),
                    carStatusDataList.get(i).getTyresAge(),
                    lapDataList.get(i).getDriverStatus(),
                    lapDataList.get(i).getResultStatus()
            ));
        }

        qualiStorageInfoListSize = qualiStorageInfoList.size();
    }

    public ObservableList<QualiTable> getQualiTableList() {

        qualiTableList.clear();

        lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();
        participantsNameList = PacketReceiver.getParticipantsPacket().getParticipantsNameList();
        carStatusDataList = PacketReceiver.getCarStatusPacket().getCarStatusDataList();
        numActiveCars = PacketReceiver.getParticipantsPacket().getNumberActiveCars();

        for (int i = 0; i < qualiStorageInfoListSize; ++i) {

            lapTimeOld = qualiStorageInfoList.get(i).lastLapTime;
            lapTimeNew = lapDataList.get(i).getLastLapTime();

            if (lapTimeNew < lapTimeOld && lapTimeNew != 0) {
                // Был поставлен новый лучший круг
                carID = PacketReceiver.getSessionHistoryPacket().getCarID();

                //TODO: Сделать так, чтобы не нужно было выводить в консоль для отображения в таблице

                // Ждём пакет с историей кругов определённого пилота с индексом i
                while (carID != i) {

                    carID = PacketReceiver.getSessionHistoryPacket().getCarID();
                    System.out.println("i - ");
                    System.out.println(i);
                    System.out.println(" carID - ");
                    System.out.println(carID);
                }

                numLapsHistory = PacketReceiver.getSessionHistoryPacket().getNumberLaps();
                lapHistoryDataList = PacketReceiver.getSessionHistoryPacket().getLapHistoryDataList();
                lapValid = lapHistoryDataList.get(numLapsHistory - 2).getLapValid();

                // Если круг валидный, то записываем
                if (lapValid == 1) {
                    qualiStorageInfoList.get(i).lastLapTime = lapTimeNew;
                    qualiStorageInfoList.get(i).lapValid = lapHistoryDataList.get(numLapsHistory - 2).getLapValid();
                    qualiStorageInfoList.get(i).sector1Time = lapHistoryDataList.get(numLapsHistory - 2).getSector1Time();
                    qualiStorageInfoList.get(i).sector2Time = lapHistoryDataList.get(numLapsHistory - 2).getSector2Time();
                    qualiStorageInfoList.get(i).sector3Time = lapHistoryDataList.get(numLapsHistory - 2).getSector3Time();
                }

                qualiStorageInfoList.get(i).tyreCompound = carStatusDataList.get(i).getTyreCompound();
                qualiStorageInfoList.get(i).tyresAge = carStatusDataList.get(i).getTyresAge();
            }

            qualiStorageInfoList.get(i).carPosition = lapDataList.get(i).getCarPosition();
            qualiStorageInfoList.get(i).driverStatus = lapDataList.get(i).getDriverStatus();
            qualiStorageInfoList.get(i).resultStatus = lapDataList.get(i).getResultStatus();

            // Заполнение массива данных для возврата
            qualiTableList.add(new QualiTable(
                    qualiStorageInfoList.get(i).carPosition,
                    qualiStorageInfoList.get(i).name,
                    InfoHandler.processLapSectorTime(qualiStorageInfoList.get(i).lastLapTime),
                    InfoHandler.processQualiDelta(qualiStorageInfoList.get(i).lastLapTime, qualiStorageInfoList.get(i).carPosition),
                    InfoHandler.processLapSectorTime(qualiStorageInfoList.get(i).sector1Time),
                    InfoHandler.processLapSectorTime(qualiStorageInfoList.get(i).sector2Time),
                    InfoHandler.processLapSectorTime(qualiStorageInfoList.get(i).sector3Time),
                    InfoHandler.processTyre(qualiStorageInfoList.get(i).tyreCompound, qualiStorageInfoList.get(i).tyresAge),
                    InfoHandler.getDriverStatus(qualiStorageInfoList.get(i).driverStatus),
                    InfoHandler.getResultStatus(qualiStorageInfoList.get(i).resultStatus)));
        }

        return qualiTableList;
    }

    static class QualiStorageInfo {

        int carPosition;
        String name;
        long lastLapTime;
        int lapValid; // 1 - valid, 0 - invalid
        int sector1Time;
        int sector2Time;
        int sector3Time;
        int tyreCompound;
        int tyresAge;
        int driverStatus;
        int resultStatus;

        public QualiStorageInfo(int carPosition,
                                String name,
                                long lastLapTime,
                                int lapValid,
                                int sector1Time,
                                int sector2Time,
                                int sector3Time,
                                int tyreCompound,
                                int tyresAge,
                                int driverStatus,
                                int resultStatus) {
            this.carPosition = carPosition;
            this.name = name;
            this.lastLapTime = lastLapTime;
            this.lapValid = lapValid;
            this.sector1Time = sector1Time;
            this.sector2Time = sector2Time;
            this.sector3Time = sector3Time;
            this.tyreCompound = tyreCompound;
            this.tyresAge = tyresAge;
            this.driverStatus = driverStatus;
            this.resultStatus = resultStatus;
        }
    }
}
