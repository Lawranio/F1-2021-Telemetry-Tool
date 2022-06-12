package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит информацию об игроках
 *
 * Тест пройден
 */

public class ParticipantsPacket extends PacketHeader {

    private final int numberActiveCars;
    private final ArrayList<String> participantsNameList = new ArrayList<>();

    public ParticipantsPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        numberActiveCars = Byte.toUnsignedInt(byteBuffer.get());

        for(int i = 0; i < 22; ++i) {

            byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset * 7);
            byte[] bufferedName = new byte[48];
            byteBuffer.get(bufferedName, 0, 48);
            String name = new String(bufferedName, StandardCharsets.UTF_8);
            name = name.substring(0, name.indexOf(0));
            byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset);
            participantsNameList.add(name);
        }
    }

    public int getNumberActiveCars() {
        return numberActiveCars;
    }

    public ArrayList<String> getParticipantsNameList() {
        return participantsNameList;
    }
}
