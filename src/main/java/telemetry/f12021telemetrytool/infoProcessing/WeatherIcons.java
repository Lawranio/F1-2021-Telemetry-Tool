package telemetry.f12021telemetrytool.infoProcessing;

/**
 * Назначение:
 *      Хранит иконки погоды
 */

public enum WeatherIcons {

    CLEAR("assets\\weather\\clear_weather.png"),
    LIGHTCLOUD("assets\\weather\\lightcloud_weather.png"),
    OVERCAST("assets\\weather\\overcast_weather.png"),
    LIGHTRAIN("assets\\weather\\lightrain_weather.png"),
    HEAVYRAIN("assets\\weather\\heavyrain_weather.png"),
    STORM("assets\\weather\\storm_weather.png");

    public final String path;

    WeatherIcons(String path) {
        this.path = path;
    }
}
