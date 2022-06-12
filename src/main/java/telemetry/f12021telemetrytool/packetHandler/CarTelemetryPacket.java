package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит информацию о телеметрии машины
 *
 * Тест пройден
 */

public class CarTelemetryPacket extends PacketHeader {

    private final ArrayList<CarTelemetryData> carTelemetryDataList = new ArrayList<>();

    public CarTelemetryPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        for(int i = 0; i < 20; ++i)
            carTelemetryDataList.add(new CarTelemetryData(byteBuffer));

        //Print();
    }

    void Print() {

        try {

            ArrayList<String> names = PacketReceiver.getParticipantsPacket().getParticipantsNameList();
            ArrayList<LapDataPacket.LapData> laps = PacketReceiver.getLapDataPacket().getLapDataList();

            for (int i = 0; i < carTelemetryDataList.size(); ++i) {

                System.out.println(names.get(i) + " " + carTelemetryDataList.get(i).speed);
            }
            System.out.println();

        } catch (NullPointerException ignored) {}


    }

    public static class CarTelemetryData {

        int speed;
        float throttle;
        float steering;
        float brake;
        int gear;
        int[] tyresInnerTemperature = new int[4];

        public CarTelemetryData(ByteBuffer byteBuffer) {

            speed = Short.toUnsignedInt(byteBuffer.getShort());
            throttle = byteBuffer.getFloat();
            steering = byteBuffer.getFloat();
            brake = byteBuffer.getFloat();
            byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset);
            gear = byteBuffer.get();
            byteBuffer.position(byteBuffer.position() +
                                DataOffsets.UINT16.offset * 6 +
                                DataOffsets.UINT8.offset * 6);

            for(int i = 0; i < 4; ++i)
                tyresInnerTemperature[i] = Byte.toUnsignedInt(byteBuffer.get());

            byteBuffer.position(byteBuffer.position() +
                                DataOffsets.UINT16.offset +
                                DataOffsets.FLOAT.offset * 4 +
                                DataOffsets.UINT8.offset * 4);
        }

        public int getSpeed() {
            return speed;
        }

        public float getThrottle() {
            return throttle;
        }

        public float getSteering() {
            return steering;
        }

        public float getBrake() {
            return brake;
        }

        public int getGear() {
            return gear;
        }

        public int[] getTyresInnerTemperature() {
            return tyresInnerTemperature;
        }

    }

    public ArrayList<CarTelemetryData> getCarTelemetryDataList() {
        return carTelemetryDataList;
    }
}
