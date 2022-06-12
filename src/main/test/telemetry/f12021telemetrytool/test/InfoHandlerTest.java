package telemetry.f12021telemetrytool.test;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telemetry.f12021telemetrytool.infoProcessing.InfoHandler;

/**
 * Назначение:
 *      Тест класс для InfoHandler
 */

class InfoHandlerTest {

    @Test
    void processLapSectorTime() {

        Assertions.assertAll(() -> assertEquals("1:12.916", InfoHandler.processLapSectorTime(72916)),
                             () -> assertEquals("1:03.152", InfoHandler.processLapSectorTime(63152)),
                             () -> assertEquals("12.916", InfoHandler.processLapSectorTime(12916)),
                             () -> assertEquals("0.916", InfoHandler.processLapSectorTime(916)));
    }

    @Test
    void processSessionTime() {

        Assertions.assertAll(() -> assertEquals("13:33", InfoHandler.processSessionTime(813)),
                             () -> assertEquals("08:20", InfoHandler.processSessionTime(500)),
                             () -> assertEquals("08:00", InfoHandler.processSessionTime(480)),
                             () -> assertEquals("00:00", InfoHandler.processSessionTime(0)),
                             () -> assertEquals("16:05", InfoHandler.processSessionTime(965)));
    }

    @Test
    void processRaceLaps() {

        Assertions.assertAll(() -> assertEquals("1 / 25", InfoHandler.processRaceLaps(1, 25)),
                             () -> assertEquals("5 / 13", InfoHandler.processRaceLaps(5, 13)));
    }

    @Test
    void processTyre() {

        Assertions.assertAll(() -> assertEquals("Софт - 4 круга", InfoHandler.processTyre(16, 4)),
                             () -> assertEquals("Медиум - 11 кругов", InfoHandler.processTyre(17, 11)),
                             () -> assertEquals("Дождевая - 0 кругов", InfoHandler.processTyre(8, 0)),
                             () -> assertEquals("Промежуточная - 1 круг", InfoHandler.processTyre(7, 1)),
                             () -> assertEquals("Хард - 6 кругов", InfoHandler.processTyre(18, 6)));
    }

    @Test
    void processWeatherOffset() {

        Assertions.assertAll(() -> assertEquals("+ 5 минут", InfoHandler.processWeatherOffset(5)),
                             () -> assertEquals("+ 30 минут", InfoHandler.processWeatherOffset(30)),
                             () -> assertEquals("+ 0 минут", InfoHandler.processWeatherOffset(0)));
    }

    @Test
    void processTrackWeatherTemperature() {

        Assertions.assertAll(() -> assertEquals("25 °C", InfoHandler.processTrackWeatherTemperature(25)),
                             () -> assertEquals("0 °C", InfoHandler.processTrackWeatherTemperature(0)));
    }

    @Test
    void processWeatherRain() {

        Assertions.assertAll(() -> assertEquals("10 %", InfoHandler.processWeatherRain(10)),
                             () -> assertEquals("0 %", InfoHandler.processWeatherRain(0)));
    }



    @Test
    void getTrackName() {

        Assertions.assertAll(() -> assertEquals("Jeddah Street Circuit", InfoHandler.getTrackName(29)),
                             () -> assertEquals("Autodromo Jose Carlos Pace", InfoHandler.getTrackName(16)));
    }

    @Test
    void getSessionType() {

        Assertions.assertAll(() -> assertEquals("Разовая квалификация", InfoHandler.getSessionType(9)),
                             () -> assertEquals("Квалификация 2", InfoHandler.getSessionType(6)));
    }

    @Test
    void getDriverStatus() {

        Assertions.assertAll(() -> assertEquals("На трассе", InfoHandler.getDriverStatus(4)),
                             () -> assertEquals("Круг заезда", InfoHandler.getDriverStatus(2)));
    }

    @Test
    void getResultStatus() {

        Assertions.assertAll(() -> assertEquals("Активный", InfoHandler.getResultStatus(2)),
                             () -> assertEquals("Сошёл", InfoHandler.getResultStatus(7)));
    }
}