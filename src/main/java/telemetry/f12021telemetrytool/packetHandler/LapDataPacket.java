package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;

import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит информацию о кругах игроков
 *
 * Тест пройден
 */

public class LapDataPacket extends PacketHeader {

    private final ArrayList<LapData> lapDataList = new ArrayList<>();

    public LapDataPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        for(int i = 0; i < 22; ++i)
            lapDataList.add(new LapData(byteBuffer));
    }

    public static class LapData {

        long lastLapTime;
        int sector1Time;
        int sector2Time;
        float totalDistance;

        int carPosition;
        int currentLapNumber;
        int pitStatus;
        int numberPitStops;
        int sector;
        int penalties;
        int warnings;
        int driverStatus;
        int resultStatus;

        public LapData(ByteBuffer byteBuffer) {

            lastLapTime = Integer.toUnsignedLong(byteBuffer.getInt());
            byteBuffer.position(byteBuffer.position() + DataOffsets.UINT32.offset);
            sector1Time = Short.toUnsignedInt(byteBuffer.getShort());
            sector2Time = Short.toUnsignedInt(byteBuffer.getShort());
            byteBuffer.position(byteBuffer.position() + DataOffsets.FLOAT.offset);
            totalDistance = byteBuffer.getFloat();
            byteBuffer.position(byteBuffer.position() + DataOffsets.FLOAT.offset);

            carPosition = Byte.toUnsignedInt(byteBuffer.get());
            currentLapNumber = Byte.toUnsignedInt(byteBuffer.get());
            pitStatus = Byte.toUnsignedInt(byteBuffer.get());
            numberPitStops = Byte.toUnsignedInt(byteBuffer.get());
            sector = Byte.toUnsignedInt(byteBuffer.get());
            byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset);
            penalties = Byte.toUnsignedInt(byteBuffer.get());
            warnings = Byte.toUnsignedInt(byteBuffer.get());
            byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset * 3);
            driverStatus = Byte.toUnsignedInt(byteBuffer.get());
            resultStatus = Byte.toUnsignedInt(byteBuffer.get());
            byteBuffer.position(byteBuffer.position() +
                                DataOffsets.UINT8.offset * 2 +
                                DataOffsets.UINT16.offset * 2);
        }

        public long getLastLapTime() {
            return lastLapTime;
        }

        public int getSector1Time() {
            return sector1Time;
        }

        public int getSector2Time() {
            return sector2Time;
        }

        public float getTotalDistance() {
            return totalDistance;
        }

        public int getCarPosition() {
            return carPosition;
        }

        public int getCurrentLapNumber() {
            return currentLapNumber;
        }

        public int getPitStatus() {
            return pitStatus;
        }

        public int getNumberPitStops() {
            return numberPitStops;
        }

        public int getSector() {
            return sector;
        }

        public int getPenalties() {
            return penalties;
        }

        public int getWarnings() {
            return warnings;
        }

        public int getDriverStatus() {
            return driverStatus;
        }

        public int getResultStatus() {
            return resultStatus;
        }
    }

    public ArrayList<LapData> getLapDataList() {
        return lapDataList;
    }
}
