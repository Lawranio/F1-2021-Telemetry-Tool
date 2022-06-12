package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит информацию о настройках
 *
 * Тест пройден
 */

public class CarSetupsPacket extends PacketHeader {

    private final ArrayList<CarSetupsData> carSetupsDataList = new ArrayList<>();

    public CarSetupsPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        for(int i = 0; i < 22; ++i)
            carSetupsDataList.add(new CarSetupsData(byteBuffer));
    }

    public static class CarSetupsData {

        int frontWing;
        int rearWing;

        public CarSetupsData(ByteBuffer byteBuffer) {

            frontWing = Byte.toUnsignedInt(byteBuffer.get());
            rearWing = Byte.toUnsignedInt(byteBuffer.get());
            byteBuffer.position(byteBuffer.position() +
                                DataOffsets.UINT8.offset * 11 +
                                DataOffsets.FLOAT.offset * 9);
        }

        public int getFrontWing() {
            return frontWing;
        }

        public int getRearWing() {
            return rearWing;
        }
    }

    public ArrayList<CarSetupsData> getCarSetupsDataList() {
        return carSetupsDataList;
    }
}
