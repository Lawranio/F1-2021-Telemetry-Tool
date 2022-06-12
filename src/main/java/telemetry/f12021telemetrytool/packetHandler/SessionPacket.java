package telemetry.f12021telemetrytool.packetHandler;

import java.nio.ByteBuffer;

import java.util.ArrayList;

/**
 * Назначение:
 *      Хранит информацию о сессии
 *
 * Тест пройден
 */

public class SessionPacket extends PacketHeader {

    private final int weather;
    private final int trackTemperature;
    private final int airTemperature;

    private final int totalLaps;
    private final int sessionType;
    private final int trackID;

    private final int sessionTimeLeft;
    private final int safetyCarStatus;

    private final int numberWeatherForecast;
    private final ArrayList<WeatherForecast> weatherForecastList = new ArrayList<>();

    public SessionPacket(ByteBuffer byteBuffer) {

        super(byteBuffer);

        weather = Byte.toUnsignedInt(byteBuffer.get());
        trackTemperature = Byte.toUnsignedInt(byteBuffer.get());
        airTemperature = Byte.toUnsignedInt(byteBuffer.get());

        totalLaps = Byte.toUnsignedInt(byteBuffer.get());
        byteBuffer.position(byteBuffer.position() + DataOffsets.UINT16.offset);
        sessionType = Byte.toUnsignedInt(byteBuffer.get());
        trackID = Byte.toUnsignedInt(byteBuffer.get());
        byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset);

        sessionTimeLeft = Short.toUnsignedInt(byteBuffer.getShort());
        byteBuffer.position(byteBuffer.position() +
                            DataOffsets.UINT16.offset +
                            DataOffsets.UINT8.offset * 6 +
                            DataOffsets.MARSHALZONE.offset);
        safetyCarStatus = Byte.toUnsignedInt(byteBuffer.get());
        byteBuffer.position(byteBuffer.position() + DataOffsets.UINT8.offset);

        numberWeatherForecast = Byte.toUnsignedInt(byteBuffer.get());
        for(int i = 0; i < numberWeatherForecast; ++i)
            weatherForecastList.add(new WeatherForecast(byteBuffer));
     }

    public static class WeatherForecast {

        int sessionType;
        int timeOffset;
        int weather;

        int trackTemperature;
        int airTemperature;
        int rainPercentage;

        public WeatherForecast(ByteBuffer byteBuffer) {

            sessionType = Byte.toUnsignedInt(byteBuffer.get());
            timeOffset = Byte.toUnsignedInt(byteBuffer.get());
            weather = Byte.toUnsignedInt(byteBuffer.get());

            trackTemperature = Byte.toUnsignedInt(byteBuffer.get());
            byteBuffer.position(byteBuffer.position() + DataOffsets.INT8.offset);
            airTemperature = Byte.toUnsignedInt(byteBuffer.get());
            byteBuffer.position(byteBuffer.position() + DataOffsets.INT8.offset);
            rainPercentage = Byte.toUnsignedInt(byteBuffer.get());
        }

        public int getSessionType() {
            return sessionType;
        }

        public int getTimeOffset() {
            return timeOffset;
        }

        public int getWeather() {
            return weather;
        }

        public int getTrackTemperature() {
            return trackTemperature;
        }

        public int getAirTemperature() {
            return airTemperature;
        }

        public int getRainPercentage() {
            return rainPercentage;
        }
    }

    public int getWeather() {
        return weather;
    }

    public int getTrackTemperature() {
        return trackTemperature;
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public int getTotalLaps() {
        return totalLaps;
    }

    public int getSessionType() {
        return sessionType;
    }

    public int getTrackID() {
        return trackID;
    }

    public int getSessionTimeLeft() {
        return sessionTimeLeft;
    }

    public int getSafetyCarStatus() {
        return safetyCarStatus;
    }

    public int getNumberWeatherForecast() {
        return numberWeatherForecast;
    }

    public ArrayList<WeatherForecast> getWeatherForecastList() {
        return weatherForecastList;
    }
}
