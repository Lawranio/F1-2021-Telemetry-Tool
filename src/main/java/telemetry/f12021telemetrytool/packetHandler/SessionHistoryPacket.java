package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит историю сессии
 *
 * Тест пройден
 */

public class SessionHistoryPacket extends PacketHeader {

    private final ArrayList<LapHistoryData> lapHistoryDataList = new ArrayList<>();

    int carID;
    int numberLaps;

    public SessionHistoryPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        carID = Byte.toUnsignedInt(byteBuffer.get());
        numberLaps = Byte.toUnsignedInt(byteBuffer.get());
        byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset * 5);
        for (int i = 0; i < numberLaps; ++i) {
            lapHistoryDataList.add(new LapHistoryData(byteBuffer));
        }

    }

    public static class LapHistoryData {

        long lapTime;
        int sector1Time;
        int sector2Time;
        int sector3Time;
        int lapValid;

        public LapHistoryData(ByteBuffer byteBuffer) {

            lapTime = Integer.toUnsignedLong(byteBuffer.getInt());
            sector1Time = Short.toUnsignedInt(byteBuffer.getShort());
            sector2Time = Short.toUnsignedInt(byteBuffer.getShort());
            sector3Time = Short.toUnsignedInt(byteBuffer.getShort());
            lapValid = Byte.toUnsignedInt(byteBuffer.get()) & 0x01;
        }

        public long getLapTime() {
            return lapTime;
        }

        public int getSector1Time() {
            return sector1Time;
        }

        public int getSector2Time() {
            return sector2Time;
        }

        public int getSector3Time() {
            return sector3Time;
        }

        public int getLapValid() {
            return lapValid;
        }
    }

    public ArrayList<LapHistoryData> getLapHistoryDataList() {
        return lapHistoryDataList;
    }

    public int getCarID() {
        return carID;
    }

    public int getNumberLaps() {
        return numberLaps;
    }
}
