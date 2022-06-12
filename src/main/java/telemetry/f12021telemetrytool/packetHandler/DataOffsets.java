package telemetry.f12021telemetrytool.packetHandler;

/**
 * Назначение:
 *      Хранит размер типов данных для смещения указателя
 */

public enum DataOffsets {

    UINT8(1),
    INT8(1),
    UINT16(2),
    INT16(2),
    UINT32(4),
    FLOAT(4),
    UINT64(8),
    PACKETID(5),
    PLAYERCARINDEX(22),
    PACKETHEADER(24),
    MARSHALZONE(105);

    public final int offset;

    DataOffsets(int offset) {
        this.offset = offset;
    }
}
