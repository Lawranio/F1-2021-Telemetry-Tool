package telemetry.f12021telemetrytool.packetHandler;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Назначение:
 *      Приём пакетов
 *      Создание объектов
 */

public class PacketReceiver extends Thread{

    public PacketReceiver() throws SocketException {}

    byte[] receiveBuffer = new byte[2048];
    ByteBuffer byteBuffer = ByteBuffer.allocate(2048);

    //TODO: Ввод порта пользователем
    DatagramSocket socket = new DatagramSocket(8888);
    DatagramPacket datagram = new DatagramPacket(receiveBuffer, receiveBuffer.length);

    static SessionPacket sessionPacket;
    static LapDataPacket lapDataPacket;
    static ParticipantsPacket participantsPacket;
    static CarSetupsPacket carSetupsPacket;
    static CarTelemetryPacket carTelemetryPacket;
    static CarStatusPacket carStatusPacket;
    static SessionHistoryPacket sessionHistoryPacket;

    public void run() {

        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println("НАЧАЛО ПРИЁМА ПАКЕТОВ");
        while(true) {

            try {
                socket.receive(datagram);
                byteBuffer.put(datagram.getData());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch(byteBuffer.get(DataOffsets.PACKETID.offset)) {

                case 1 -> sessionPacket = new SessionPacket(byteBuffer);
                case 2 -> lapDataPacket = new LapDataPacket(byteBuffer);
                case 4 -> participantsPacket = new ParticipantsPacket(byteBuffer);
                case 5 -> carSetupsPacket = new CarSetupsPacket(byteBuffer);
                case 6 -> carTelemetryPacket = new CarTelemetryPacket(byteBuffer);
                case 7 -> carStatusPacket = new CarStatusPacket(byteBuffer);
                case 11 -> sessionHistoryPacket = new SessionHistoryPacket(byteBuffer);
            }

            byteBuffer.clear();
        }
    }

    public static SessionPacket getSessionPacket() {
        return sessionPacket;
    }

    public static LapDataPacket getLapDataPacket() {
        return lapDataPacket;
    }

    public static ParticipantsPacket getParticipantsPacket() {
        return participantsPacket;
    }

    public static CarSetupsPacket getCarSetupsPacket() {
        return carSetupsPacket;
    }

    synchronized public static CarTelemetryPacket getCarTelemetryPacket() {
        return carTelemetryPacket;
    }

    public static CarStatusPacket getCarStatusPacket() {
        return carStatusPacket;
    }

    synchronized public static SessionHistoryPacket getSessionHistoryPacket() {
        return sessionHistoryPacket;
    }
}
