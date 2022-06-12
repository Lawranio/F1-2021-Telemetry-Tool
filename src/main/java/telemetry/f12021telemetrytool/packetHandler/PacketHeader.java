package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;

/**
 * Назначение:
 *      Класс-родитель для других пакетов
 */

abstract class PacketHeader {

    private final int packetID;
    private final int playerCarIndex;

    public PacketHeader(ByteBuffer byteBuffer) {

        packetID = Byte.toUnsignedInt(byteBuffer.get(DataOffsets.PACKETID.offset));
        playerCarIndex = Byte.toUnsignedInt(byteBuffer.get(DataOffsets.PLAYERCARINDEX.offset));
        byteBuffer.position(DataOffsets.PACKETHEADER.offset);
    }

    public int getPacketID() {
        return packetID;
    }

    public int getPlayerCarIndex() {
        return playerCarIndex;
    }
}
