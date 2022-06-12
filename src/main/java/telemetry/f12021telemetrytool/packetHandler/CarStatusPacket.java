package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит информацию о состоянии машины
 *
 * Тест пройден
 */

public class CarStatusPacket extends PacketHeader {

    private final ArrayList<CarStatusData> carStatusDataList = new ArrayList<>();

    public CarStatusPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        for(int i = 0; i < 22; ++i)
            carStatusDataList.add(new CarStatusData(byteBuffer));
    }

    public static class CarStatusData {

        int tyreCompound;
        int tyresAge;

        public CarStatusData(ByteBuffer byteBuffer) {

            byteBuffer.position(byteBuffer.position() +
                                DataOffsets.UINT8.offset * 8 +
                                DataOffsets.FLOAT.offset * 3 +
                                DataOffsets.UINT16.offset * 3);

            tyreCompound = Byte.toUnsignedInt(byteBuffer.get());
            tyresAge = Byte.toUnsignedInt(byteBuffer.get());

            byteBuffer.position(byteBuffer.position() +
                                DataOffsets.UINT8.offset * 3 +
                                DataOffsets.FLOAT.offset * 4);
        }

        public int getTyreCompound() {
            return tyreCompound;
        }

        public int getTyresAge() {
            return tyresAge;
        }
    }

    public ArrayList<CarStatusData> getCarStatusDataList() {
        return carStatusDataList;
    }
}
