package telemetry.f12021telemetrytool.infoProcessing;

import javafx.scene.image.Image;
import telemetry.f12021telemetrytool.packetHandler.LapDataPacket;
import telemetry.f12021telemetrytool.packetHandler.PacketReceiver;

import java.io.File;
import java.util.ArrayList;

/**
 * Назначение:
 *      Обрабатывает информацию из пакетов
 */

public class InfoHandler {

    /** Обработка времени круга
     *
     * @param time
     *      Время круга или сектора в миллисекундах
     * @return
     *      Время круга в виде строки
     */
    public static String processLapSectorTime(long time) {

        if (time == 1000000000) return "Без времени";

        StringBuilder result = new StringBuilder();

        int minutes = (int) (time / 60000);
        float seconds;

        if (minutes >= 1) {
            result.append(minutes).append(":");
            seconds = (float) (time - 60000) / 1000;

            if (seconds < 10)
                result.append("0").append(seconds);
            else
                result.append(seconds);
        }
        else {
            seconds = (float) time / 1000;
            result.append(seconds);
        }

        return result.toString();
    }

    /** Обработка времени сектора 3 для гонки
     *
     * @param sector1Time
     *      Время сектора 1
     * @param sector2Time
     *      Время сектора 2
     * @param lapTime
     *      Время круга
     * @return
     */
    public static String processSector3Time(int sector1Time, int sector2Time, long lapTime) {

        if (lapTime == 0 || sector1Time == 0 || sector2Time == 0) return "";
        return processLapSectorTime(lapTime - sector1Time - sector2Time);
    }

    /** Обработка разницы между временами для квалификации
     *
     * @param firstPlayerLapTime
     *      Время круга первого пилота в миллисекундах
     * @param firstPlayerPosition
     *      Позиция первого пилота
     * @return
     *      Разница между временами в виде строки
     */
    public static String processQualiDelta(long firstPlayerLapTime, int firstPlayerPosition) {

        if (firstPlayerLapTime == 1000000000) return "";
        if (firstPlayerPosition == 1) return "ЛИДЕР";

        StringBuilder result = new StringBuilder();

        long secondPlayerLapTime;
        ArrayList<LapDataPacket.LapData> lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();

        // Берём время лидера
        secondPlayerLapTime = lapDataList.
                stream().
                filter(e -> e.getCarPosition() == 1).
                toList().
                get(0).
                getLastLapTime();

        // Если время ещё не поставлено, то возвращаем пустую строку
        if (secondPlayerLapTime == 0) return "";

        result.append("+ ").append(processLapSectorTime(firstPlayerLapTime - secondPlayerLapTime));
        return result.toString();
    }

    /** Обработка разница между пилота в гонке
     *
     * @param firstPlayerDistance
     *      Дистанция первого игрока
     * @param firstPlayerSpeed
     *      Скорость первого игрока
     * @param firstPlayerPosition
     *      Позиция первого игрока
     * @param secondPlayerPosition
     *      Nullable - Позиция второго игрока
     * @return
     *      Разница между пилотами в виде стркои
     */
    public static String processRaceDelta(float firstPlayerDistance, int firstPlayerSpeed, int firstPlayerPosition, int secondPlayerPosition) {

        if (firstPlayerPosition == 1 && secondPlayerPosition == 0) return "ЛИДЕР";

        float secondPlayerDistance = 0;
        int nonTargetSecondPlayerPosition = firstPlayerPosition - 1;
        float timeDiffrence;

        ArrayList<LapDataPacket.LapData> lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();
        int lapDataListSize = lapDataList.size();
        
        if (secondPlayerPosition == 0) {

            // Время до лидера
            for (int i = 0; i < lapDataListSize; ++i) {

                if (lapDataList.get(i).getCarPosition() == nonTargetSecondPlayerPosition) {

                    secondPlayerDistance = lapDataList.get(i).getTotalDistance();
                    break;
                }
            }
        }
        else {
            // Время до определённого соперника

            for (int i = 0; i < lapDataListSize; ++i) {

                if (lapDataList.get(i).getCarPosition() == secondPlayerPosition) {

                    secondPlayerDistance = lapDataList.get(i).getTotalDistance();
                    break;
                }
            }
        }

        float covertedFirstPlayerSpeed = firstPlayerSpeed / (float) 3.6;

        timeDiffrence = (secondPlayerDistance - firstPlayerDistance) / covertedFirstPlayerSpeed;

        if (firstPlayerPosition > secondPlayerPosition)
            return "+ " + String.format("%.3f", timeDiffrence);
        else
            return "- " + String.format("%.3f", timeDiffrence);
    }

    /** Обработка времени сессии
     *
     * @param sessionTime
     *      Время сессии в секундах
     * @return
     *      Время сессии в виде строки
     */
    public static String processSessionTime(int sessionTime) {

        StringBuilder result = new StringBuilder();

        int minutes = sessionTime / 60;
        int seconds = sessionTime - (minutes * 60);

        if (minutes > 10)
            result.append(minutes).append(":");

        else
            result.append("0").append(minutes).append(":");

        if (seconds == 0) {
            result.append("00");
            return result.toString();
        }

        if (seconds < 10)
            result.append("0").append(seconds);
        else
            result.append(seconds);

        return result.toString();
    }

    /** Обработка кругов в гонке
     *
     * @param currentLap
     *      Текущий круг
     * @param totalLaps
     *      Общее количество кругов
     * @return
     *      Круги в виде строки
     */
    public static String processRaceLaps(int currentLap, int totalLaps) {
        return currentLap + " / " + totalLaps;
    }

    /** Обработка информации о резине
     *
     * @param tyreCompound
     *      Тип резины
     * @param tyreAge
     *      Количество кругов в резине
     * @return
     *      Информация о резине в виде строки
     */
    public static String processTyre(int tyreCompound, int tyreAge) {

        StringBuilder result = new StringBuilder();

        switch (tyreCompound) {

            case 7 -> result.append(Tyres.INTER.tyreCompound);
            case 8 -> result.append(Tyres.WET.tyreCompound);
            case 16 -> result.append(Tyres.SOFT.tyreCompound);
            case 17 -> result.append(Tyres.MEDIUM.tyreCompound);
            case 18 -> result.append(Tyres.HARD.tyreCompound);
        }
        result.append(" - ").append(tyreAge);

        if (tyreAge == 11 || tyreAge == 12 || tyreAge == 13 || tyreAge == 14) {
            result.append(" кругов");
            return result.toString();
        }

        int lastDigit = tyreAge % 10;

        if (lastDigit == 1) {
            result.append(" круг");
            return result.toString();
        }
        if (lastDigit == 2 || lastDigit == 3 || lastDigit == 4) {
            result.append(" круга");
            return result.toString();
        }
        if (lastDigit == 5 || lastDigit == 6 || lastDigit == 7 || lastDigit == 8 ||
                                                lastDigit == 9 || lastDigit == 0) {
            result.append(" кругов");
            return result.toString();
        }
        return "";
    }

    /** Обработка сдвига погоды
     *
     * @param offset
     *      Сдвиг погоды по времени
     * @return
     *      Сдвиг погоды по времени с плюсом в начале
     */
    public static String processWeatherOffset(int offset) {

        return "+ " + offset + " минут";
    }

    /** Обработка температуры трассы или воздуха
     *
     * @param temperature
     *      Температура трассы или воздуха
     * @return
     *      Температура трассы или воздуха со знаком цельсия в конце
     */
    public static String processTrackWeatherTemperature(int temperature) {

        return temperature + " °C";
    }

    /** Обработка вероятности дождя
     *
     * @param rain
     *      Вероятность дождя
     * @return
     *      Вероятность дождя со знаком процента в конце
     */
    public static String processWeatherRain(int rain) {

        return rain + " %";
    }




    /** Получить иконку машины безопасности
     *
     * @param safetyCarStatus
     *      Статус машины безопасности
     * @return
     *      Иконка машины безопасности
     */
    public static Image getSafetyCar(int safetyCarStatus) {

        File file = null;

        switch (safetyCarStatus) {

            case 0 -> {
                return null;
            }
            case 1 -> file = new File(SafetyCarStatus.FULL.safetyCarStatus);
            case 2 -> file = new File(SafetyCarStatus.VIRTUAL.safetyCarStatus);
            case 3 -> file = new File(SafetyCarStatus.FORMATIONLAP.safetyCarStatus);
        }

        assert file != null;
        return new Image(file.getAbsolutePath());
    }

    /** Получить название трассы
     *
     * @param trackID
     *      Номер трассы
     * @return
     *      Название трассы
     */
    public static String getTrackName(int trackID) {

        String result = "";
        switch (trackID) {

            case 0 -> result = TrackNames.AUSTRALIA.trackName;
            case 1 -> result = TrackNames.FRANCE.trackName;
            case 2 -> result = TrackNames.CHINA.trackName;
            case 3 -> result = TrackNames.BAHRAIN.trackName;
            case 4 -> result = TrackNames.SPAIN.trackName;
            case 5 -> result = TrackNames.MONACO.trackName;
            case 6 -> result = TrackNames.CANADA.trackName;
            case 7 -> result = TrackNames.GREATBRITAIN.trackName;
            case 9 -> result = TrackNames.HUNGARY.trackName;
            case 10 -> result = TrackNames.BELGIUM.trackName;
            case 11 -> result = TrackNames.MONZA.trackName;
            case 12 -> result = TrackNames.SINGAPORE.trackName;
            case 13 -> result = TrackNames.JAPAN.trackName;
            case 14 -> result = TrackNames.ABUDHABI.trackName;
            case 15 -> result = TrackNames.USA.trackName;
            case 16 -> result = TrackNames.BRAZIL.trackName;
            case 17 -> result = TrackNames.AUSTRIA.trackName;
            case 18 -> result = TrackNames.RUSSIA.trackName;
            case 19 -> result = TrackNames.MEXICO.trackName;
            case 20 -> result = TrackNames.AZERBAIJAN.trackName;
            case 26 -> result = TrackNames.NETHERLANDS.trackName;
            case 27 -> result = TrackNames.IMOLA.trackName;
            case 28 -> result = TrackNames.PORTUGAL.trackName;
            case 29 -> result = TrackNames.SAUDIARABIA.trackName;
        }

        return result;
    }

    /** Получить название сессии
     *
     * @param sessionType
     *      Тип сессии
     * @return
     *      Тип сессии в виде строки
     */
    public static String getSessionType(int sessionType) {

        String result = "";

        switch (sessionType) {

            case 5 -> result = SessionType.Q1.sessionType;
            case 6 -> result = SessionType.Q2.sessionType;
            case 7 -> result = SessionType.Q3.sessionType;
            case 8 -> result = SessionType.SHORT.sessionType;
            case 9 -> result = SessionType.OSQ.sessionType;
            case 10 -> result = SessionType.RACE.sessionType;
        }
        return result;
    }

    /** Получить статус пилота
     *
     * @param driverStatus
     *      Статус пилота
     * @return
     *      Статус пилота в виде строки
     */
    public static String getDriverStatus(int driverStatus) {

        String result = "";

        switch (driverStatus) {

            case 0 -> result = DriverStatus.PIT.driverStatus;
            case 1 -> result = DriverStatus.FLYINGLAP.driverStatus;
            case 2 -> result = DriverStatus.INLAP.driverStatus;
            case 3 -> result = DriverStatus.OUTLAP.driverStatus;
            case 4 -> result = DriverStatus.ONTRACK.driverStatus;
        }

        return result;
    }

    /** Получить статус результата
     *
     * @param resultStatus
     *      Статус результата
     * @return
     *      Статус результата в виде строки
     */
    public static String getResultStatus(int resultStatus) {

        String result = "";

        switch (resultStatus) {

            case 0 -> result = ResultStatus.INVALID.resultStatus;
            case 1 -> result = ResultStatus.INACTIVE.resultStatus;
            case 2 -> result = ResultStatus.ACTIVE.resultStatus;
            case 3 -> result = ResultStatus.FINISHED.resultStatus;
            case 4 -> result = ResultStatus.DIDNOTFINISH.resultStatus;
            case 5 -> result = ResultStatus.DISQUALIFIED.resultStatus;
            case 6 -> result = ResultStatus.NOTCLASSIFIED.resultStatus;
            case 7 -> result = ResultStatus.RETIRED.resultStatus;
        }
        return result;
    }

    /** Получить иконку погоды
     *
     * @param weatherType
     *      Тип погоды
     * @return
     *      Иконка погоды
     */
    public static Image getWeatherIcon(int weatherType) {

        File file = null;

        switch (weatherType) {

            case 0 -> file = new File(WeatherIcons.CLEAR.path);
            case 1 -> file = new File(WeatherIcons.LIGHTCLOUD.path);
            case 2 -> file = new File(WeatherIcons.OVERCAST.path);
            case 3 -> file = new File(WeatherIcons.LIGHTRAIN.path);
            case 4 -> file = new File(WeatherIcons.HEAVYRAIN.path);
            case 5 -> file = new File(WeatherIcons.STORM.path);
        }

        assert file != null;
        return new Image(file.getAbsolutePath());
    }
}
