package telemetry.f12021telemetrytool.view;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import telemetry.f12021telemetrytool.infoProcessing.InfoHandler;
import telemetry.f12021telemetrytool.infoProcessing.QualiStorage;
import telemetry.f12021telemetrytool.infoProcessing.RaceStorage;
import telemetry.f12021telemetrytool.packetHandler.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Назначение:
 *      Контроллер для интерфейса
 */

public class ApplicationController {

    // Таблица для квалификации
    QualiStorage qualiStorage;
    @FXML
    TableView<QualiTable> qualiTable;
    @FXML
    TableColumn<QualiTable, Integer> carPositionQuali;
    @FXML
    TableColumn<QualiTable, String> nameQuali;
    @FXML
    TableColumn<QualiTable, String> fastestLapQuali;
    @FXML
    TableColumn<QualiTable, String> deltaQuali;
    @FXML
    TableColumn<QualiTable, String> sector1Quali;
    @FXML
    TableColumn<QualiTable, String> sector2Quali;
    @FXML
    TableColumn<QualiTable, String> sector3Quali;
    @FXML
    TableColumn<QualiTable, String> tyreOnFastestLapQuali;
    @FXML
    TableColumn<QualiTable, String> driverStatusQuali;
    @FXML
    TableColumn<QualiTable, String> resultStatusQuali;

    // Таблица для гонки
    RaceStorage raceStorage;
    @FXML
    TableView<RaceTable> raceTable;
    @FXML
    TableColumn<RaceTable, Integer> carPositionRace;
    @FXML
    TableColumn<RaceTable, String> nameRace;
    @FXML
    TableColumn<RaceTable, String> deltaRace;
    @FXML
    TableColumn<RaceTable, String> tyreRace;
    @FXML
    TableColumn<RaceTable, String> sector1Race;
    @FXML
    TableColumn<RaceTable, String> sector2Race;
    @FXML
    TableColumn<RaceTable, String> sector3Race;
    @FXML
    TableColumn<RaceTable, String> lastLapRace;
    @FXML
    TableColumn<RaceTable, Integer> warningRace;
    @FXML
    TableColumn<RaceTable, Integer> penaltyRace;
    @FXML
    TableColumn<RaceTable, String> driverStatusRace;
    @FXML
    TableColumn<RaceTable, String> resultStatusRace;

    // Иконки погоды
    @FXML
    ImageView weatherIcon1 = new ImageView();
    @FXML
    ImageView weatherIcon2 = new ImageView();
    @FXML
    ImageView weatherIcon3 = new ImageView();
    @FXML
    ImageView weatherIcon4 = new ImageView();
    @FXML
    ImageView weatherIcon5 = new ImageView();
    @FXML
    ImageView weatherIcon6 = new ImageView();
    @FXML
    ImageView weatherIcon7 = new ImageView();

    // Сдвиг погоды по времени
    @FXML
    Text weatherOffset1 = new Text();
    @FXML
    Text weatherOffset2 = new Text();
    @FXML
    Text weatherOffset3 = new Text();
    @FXML
    Text weatherOffset4 = new Text();
    @FXML
    Text weatherOffset5 = new Text();
    @FXML
    Text weatherOffset6 = new Text();
    @FXML
    Text weatherOffset7 = new Text();

    // Температура воздуха
    @FXML
    Text airTemperature1 = new Text();
    @FXML
    Text airTemperature2 = new Text();
    @FXML
    Text airTemperature3 = new Text();
    @FXML
    Text airTemperature4 = new Text();
    @FXML
    Text airTemperature5 = new Text();
    @FXML
    Text airTemperature6 = new Text();
    @FXML
    Text airTemperature7 = new Text();

    // Температура трассы
    @FXML
    Text trackTemperature1 = new Text();
    @FXML
    Text trackTemperature2 = new Text();
    @FXML
    Text trackTemperature3 = new Text();
    @FXML
    Text trackTemperature4 = new Text();
    @FXML
    Text trackTemperature5 = new Text();
    @FXML
    Text trackTemperature6 = new Text();
    @FXML
    Text trackTemperature7 = new Text();

    // Дождь
    @FXML
    Text rain1 = new Text();
    @FXML
    Text rain2 = new Text();
    @FXML
    Text rain3 = new Text();
    @FXML
    Text rain4 = new Text();
    @FXML
    Text rain5 = new Text();
    @FXML
    Text rain6 = new Text();
    @FXML
    Text rain7 = new Text();

    // Информация о сессии
    @FXML
    Text weatherSession = new Text();
    @FXML
    Text sessionType = new Text();
    @FXML
    Text sessionTime = new Text();
    @FXML
    Text trackName = new Text();
    @FXML
    ImageView safetyCar = new ImageView();

    // Расширенная информация о пилоте
    @FXML
    Text name = new Text();
    @FXML
    Text leftFrontTyreTemperature = new Text();
    @FXML
    Text leftRearTyreTemperature = new Text();
    @FXML
    Text rightFrontTyreTemperature = new Text();
    @FXML
    Text rightRearTyreTemperature = new Text();
    @FXML
    Text frontWing = new Text();
    @FXML
    Text rearWing = new Text();
    @FXML
    Text lastLapNumber1 = new Text();
    @FXML
    Text lastLapNumber2 = new Text();
    @FXML
    Text lastLapNumber3 = new Text();
    @FXML
    Text lastLapNumber4 = new Text();
    @FXML
    Text lastLapNumber5 = new Text();
    @FXML
    Text lastLap1 = new Text();
    @FXML
    Text lastLap2 = new Text();
    @FXML
    Text lastLap3 = new Text();
    @FXML
    Text lastLap4 = new Text();
    @FXML
    Text lastLap5 = new Text();
    @FXML
    Text bestLap = new Text();
    @FXML
    Text rivalCarPosition = new Text();
    @FXML
    Text playerCarPosition = new Text();
    @FXML
    Text delta = new Text();

    // Заезд на время
    @FXML
    NumberAxis xAxis = new NumberAxis();
    @FXML
    NumberAxis yAxis = new NumberAxis();
    @FXML
    LineChart<Number, Number> lineChartSpeed = new LineChart(xAxis, yAxis);
    @FXML
    LineChart<Number, Number> lineChartBrake = new LineChart(xAxis, yAxis);
    @FXML
    LineChart<Number, Number> lineChartGear = new LineChart(xAxis, yAxis);

    XYChart.Series<Number, Number> firstPlayerDataSpeed = new XYChart.Series<>();
    XYChart.Series<Number, Number> secondPlayerDataSpeed = new XYChart.Series<>();

    XYChart.Series<Number, Number> firstPlayerDataBrake = new XYChart.Series<>();
    XYChart.Series<Number, Number> secondPlayerDataBrake = new XYChart.Series<>();

    XYChart.Series<Number, Number> firstPlayerDataGear = new XYChart.Series<>();
    XYChart.Series<Number, Number> secondPlayerDataGear = new XYChart.Series<>();

    @FXML
    AnchorPane weatherAnchorPane;
    @FXML
    AnchorPane sessionInfoAnchorPane;
    @FXML
    AnchorPane extendedInfoAnchorPane;

    static Thread qualiThread = new Thread();
    static Thread raceThread = new Thread();
    Thread extendedInfoThread;

    boolean extendedInfoThreadIsRunning = false;

    int sessionTypeCount;
    int totalLaps;
    int playerID;
    int rivalIndexTT;

    // Заезд на время и работа с графиками
    @FXML
    void rivalIndexCalibration() throws InterruptedException {

        ArrayList<CarTelemetryPacket.CarTelemetryData> carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();

        // Ждём начала круга
        while (carTelemetryDataList.get(1).getSpeed() == 0) {

            carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
        }
        Thread.sleep(500);
        int size = carTelemetryDataList.size();
        for (int i = 2; i < size; ++i) {

            if (carTelemetryDataList.get(i).getSpeed() != 0) {

                rivalIndexTT = i;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Калибровка завершена");
                alert.showAndWait();
                break;
            }
        }
    }
    @FXML
    void loadTimeTrialLap() {

        System.out.println("START");

        weatherAnchorPane.setVisible(false);
        extendedInfoAnchorPane.setVisible(false);
        sessionInfoAnchorPane.setVisible(false);
        safetyCar.setVisible(false);

        lineChartSpeed.setCreateSymbols(false);
        lineChartBrake.setCreateSymbols(false);
        lineChartGear.setCreateSymbols(false);

        ArrayList<CarTelemetryPacket.CarTelemetryData> carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
        ArrayList<String> participantsNameList = PacketReceiver.getParticipantsPacket().getParticipantsNameList();

        // Ждём начала круга
        while (carTelemetryDataList.get(1).getSpeed() == 0) {

            carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
            System.out.println();
        }

        int iterator = 1;
        System.out.println("запись старт");
        System.out.println(rivalIndexTT);
        while (carTelemetryDataList.get(1).getSpeed() != 0) {

            carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
            //Thread.sleep(500);
            firstPlayerDataSpeed.getData().add(new XYChart.Data<>(iterator, carTelemetryDataList.get(1).getSpeed()));
            secondPlayerDataSpeed.getData().add(new XYChart.Data<>(iterator, carTelemetryDataList.get(rivalIndexTT).getSpeed()));

            firstPlayerDataBrake.getData().add(new XYChart.Data<>(iterator, carTelemetryDataList.get(1).getBrake()));
            secondPlayerDataBrake.getData().add(new XYChart.Data<>(iterator, carTelemetryDataList.get(rivalIndexTT).getBrake()));

            firstPlayerDataGear.getData().add(new XYChart.Data<>(iterator, carTelemetryDataList.get(1).getGear()));
            secondPlayerDataGear.getData().add(new XYChart.Data<>(iterator, carTelemetryDataList.get(rivalIndexTT).getGear()));
            ++iterator;
        }
        System.out.println("запись стоп");

        lineChartSpeed.getData().addAll(firstPlayerDataSpeed, secondPlayerDataSpeed);
        lineChartBrake.getData().addAll(firstPlayerDataBrake, secondPlayerDataBrake);
        lineChartGear.getData().addAll(firstPlayerDataGear, secondPlayerDataGear);

        firstPlayerDataSpeed.setName(participantsNameList.get(1));
        secondPlayerDataSpeed.setName(participantsNameList.get(rivalIndexTT));

        firstPlayerDataGear.setName(participantsNameList.get(1));
        secondPlayerDataGear.setName(participantsNameList.get(rivalIndexTT));

        firstPlayerDataBrake.setName(participantsNameList.get(1));
        secondPlayerDataBrake.setName(participantsNameList.get(rivalIndexTT));

        firstPlayerDataSpeed.getNode().setStyle("-fx-stroke-width: 1px");
        secondPlayerDataSpeed.getNode().setStyle("-fx-stroke-width: 1px");

        firstPlayerDataGear.getNode().setStyle("-fx-stroke-width: 1px");
        secondPlayerDataGear.getNode().setStyle("-fx-stroke-width: 1px");

        firstPlayerDataBrake.getNode().setStyle("-fx-stroke-width: 1px");
        secondPlayerDataBrake.getNode().setStyle("-fx-stroke-width: 1px");
    }
    @FXML
    void showLineCharts() {

        lineChartBrake.setVisible(true);
        lineChartGear.setVisible(true);
        lineChartSpeed.setVisible(true);
    }
    @FXML
    void hideLineCharts() {

        lineChartBrake.setVisible(false);
        lineChartGear.setVisible(false);
        lineChartSpeed.setVisible(false);
    }
    @FXML
    void removeRivalLine() {

        if (lineChartSpeed.getData().size() == 2) {

            lineChartSpeed.getData().remove(1);
            lineChartGear.getData().remove(1);
            lineChartBrake.getData().remove(1);
        }
        else {

            if (!Objects.equals(lineChartSpeed.getData().get(0).getName(), "Личный рекорд")) {

                lineChartSpeed.getData().remove(0);
                lineChartGear.getData().remove(0);
                lineChartBrake.getData().remove(0);
            }
        }
    }
    @FXML
    void addRivalLine() {

        if (!(lineChartSpeed.getData().size() == 2)) {

            if (Objects.equals(lineChartSpeed.getData().get(0).getName(), "Личный рекорд")) {

                lineChartSpeed.getData().add(secondPlayerDataSpeed);
                lineChartBrake.getData().add(secondPlayerDataBrake);
                lineChartGear.getData().add(secondPlayerDataGear);
            }
        }

        secondPlayerDataSpeed.getNode().setStyle("-fx-stroke-width: 1px");
        secondPlayerDataGear.getNode().setStyle("-fx-stroke-width: 1px");
        secondPlayerDataBrake.getNode().setStyle("-fx-stroke-width: 1px");
    }
    @FXML
    void removePlayerLine() {

        if (lineChartSpeed.getData().size() == 2) {

            lineChartSpeed.getData().remove(0);
            lineChartGear.getData().remove(0);
            lineChartBrake.getData().remove(0);
        }
        else {

            if (Objects.equals(lineChartSpeed.getData().get(0).getName(), "Личный рекорд")) {

                lineChartSpeed.getData().remove(0);
                lineChartGear.getData().remove(0);
                lineChartBrake.getData().remove(0);
            }
        }
    }
    @FXML
    void addPlayerLine() {

        if (!(lineChartSpeed.getData().size() == 2)) {

            if (!Objects.equals(lineChartSpeed.getData().get(0).getName(), "Личный рекорд")) {

                lineChartSpeed.getData().add(firstPlayerDataSpeed);
                lineChartBrake.getData().add(firstPlayerDataBrake);
                lineChartGear.getData().add(firstPlayerDataGear);
            }
        }

        firstPlayerDataSpeed.getNode().setStyle("-fx-stroke-width: 1px");
        firstPlayerDataGear.getNode().setStyle("-fx-stroke-width: 1px");
        firstPlayerDataBrake.getNode().setStyle("-fx-stroke-width: 1px");
    }

    // Запуск и остановка потока квалификации
    @FXML
    void startQuali() {

        System.out.println("НАЧАЛО КВАЛИФИКАЦИИ");

        qualiStorage = new QualiStorage();

        if (qualiThread.isAlive())
            stopQuali();

        sessionInfoAnchorPane.setVisible(true);
        weatherAnchorPane.setVisible(true);
        qualiTable.setVisible(true);
        raceTable.setVisible(false);

        sessionType.setText(InfoHandler.getSessionType(PacketReceiver.getSessionPacket().getSessionType()));
        trackName.setText(InfoHandler.getTrackName(PacketReceiver.getSessionPacket().getTrackID()));

        carPositionQuali.setCellValueFactory(new PropertyValueFactory<>("carPosition"));
        nameQuali.setCellValueFactory(new PropertyValueFactory<>("name"));
        fastestLapQuali.setCellValueFactory(new PropertyValueFactory<>("fastestLap"));
        deltaQuali.setCellValueFactory(new PropertyValueFactory<>("delta"));
        sector1Quali.setCellValueFactory(new PropertyValueFactory<>("sector1"));
        sector2Quali.setCellValueFactory(new PropertyValueFactory<>("sector2"));
        sector3Quali.setCellValueFactory(new PropertyValueFactory<>("sector3"));
        tyreOnFastestLapQuali.setCellValueFactory(new PropertyValueFactory<>("tyreOnFastestLap"));
        driverStatusQuali.setCellValueFactory(new PropertyValueFactory<>("driverStatus"));
        resultStatusQuali.setCellValueFactory(new PropertyValueFactory<>("resultStatus"));

        qualiTable.setEditable(false);
        qualiTable.getColumns().forEach(e -> e.setReorderable(false));
        qualiTable.getColumns().forEach(e -> e.setSortable(false));

        qualiThread = new Thread(() -> {

            System.out.println("СТАРТ ПОТОКА КВАЛИФИКАЦИИ");
            while (true) {

                ObservableList<QualiTable> qualiTableList = qualiStorage.getQualiTableList();
                qualiTableList.sort(Comparator.comparingInt(o -> o.carPosition));

                //Platform.runLater(() -> qualiTable.setItems(qualiTableList));
                qualiTable.setItems(qualiTableList);
                sessionTime.setText(InfoHandler.processSessionTime(PacketReceiver.getSessionPacket().getSessionTimeLeft()));


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }
            }
        });

        qualiThread.start();
    }
    @FXML
    void stopQuali() {

        System.out.println("ОКОНЧАНИЕ КВАЛИФИКАЦИИ");
        qualiThread.interrupt();
        qualiTable.setVisible(false);
        sessionInfoAnchorPane.setVisible(false);
        weatherAnchorPane.setVisible(false);
        safetyCar.setVisible(false);
        qualiTable.setVisible(false);
    }

    // Запуск и остановка потока гонки
    @FXML
    void startRace() {

        System.out.println("НАЧАЛО ГОНКИ");

        raceStorage = new RaceStorage();
        if (raceThread.isAlive())
            stopQuali();

        totalLaps = PacketReceiver.getSessionPacket().getTotalLaps();
        playerID = PacketReceiver.getLapDataPacket().getPlayerCarIndex();

        sessionInfoAnchorPane.setVisible(true);
        weatherAnchorPane.setVisible(true);
        safetyCar.setVisible(true);
        extendedInfoAnchorPane.setVisible(true);
        raceTable.setVisible(true);
        qualiTable.setVisible(false);

        sessionType.setText(InfoHandler.getSessionType(PacketReceiver.getSessionPacket().getSessionType()));
        trackName.setText(InfoHandler.getTrackName(PacketReceiver.getSessionPacket().getTrackID()));

        carPositionRace.setCellValueFactory(new PropertyValueFactory<>("carPosition"));
        nameRace.setCellValueFactory(new PropertyValueFactory<>("name"));
        deltaRace.setCellValueFactory(new PropertyValueFactory<>("delta"));
        tyreRace.setCellValueFactory(new PropertyValueFactory<>("tyre"));
        sector1Race.setCellValueFactory(new PropertyValueFactory<>("sector1"));
        sector2Race.setCellValueFactory(new PropertyValueFactory<>("sector2"));
        sector3Race.setCellValueFactory(new PropertyValueFactory<>("sector3"));
        lastLapRace.setCellValueFactory(new PropertyValueFactory<>("lastLap"));
        warningRace.setCellValueFactory(new PropertyValueFactory<>("warning"));
        penaltyRace.setCellValueFactory(new PropertyValueFactory<>("penalty"));
        driverStatusRace.setCellValueFactory(new PropertyValueFactory<>("driverStatus"));
        resultStatusRace.setCellValueFactory(new PropertyValueFactory<>("resultStatus"));

        raceTable.setEditable(false);
        raceTable.getColumns().forEach(e -> e.setReorderable(false));
        raceTable.getColumns().forEach(e -> e.setSortable(false));

        raceThread = new Thread(() -> {

            System.out.println("СТАРТ ПОТОКА ГОНКИ");
            while (true) {

                ObservableList<RaceTable> raceTableList;
                try {
                    raceTableList = raceStorage.getRaceTableList();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                raceTableList.sort(Comparator.comparingInt(o -> o.carPosition));

                raceTable.setItems(raceTableList);
                sessionTime.setText(InfoHandler.processRaceLaps(PacketReceiver.getLapDataPacket().getLapDataList().get(playerID).getCurrentLapNumber(), totalLaps));
                safetyCar.setImage(InfoHandler.getSafetyCar(PacketReceiver.getSessionPacket().getSafetyCarStatus()));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        });

        raceThread.start();
    }
    @FXML
    void stopRace() {

        System.out.println("ОКОНЧАНИЕ ГОНКИ");
        raceThread.interrupt();
        raceTable.setVisible(false);
        extendedInfoAnchorPane.setVisible(false);
        sessionInfoAnchorPane.setVisible(false);
        weatherAnchorPane.setVisible(false);
        safetyCar.setVisible(false);
        extendedInfoThread.interrupt();
    }

    // Загрузка и прокрутка погоды
    // Если нет погоды для следующей сессии (такой сессии не будет), то надо проскроллить ещё раз
    @FXML
    void loadWeather() {

        sessionTypeCount = PacketReceiver.getSessionPacket().getSessionType();
        setUpWeather();
    }
    @FXML
    void scrollWeatherForward() {

        sessionTypeCount += 1;
        if (setUpWeather() == 0 && sessionTypeCount < 11)
            scrollWeatherForward();
    }
    @FXML
    void scrollWeatherBackward() {

        if (sessionTypeCount == 10) {
            weatherIcon7.imageProperty().set(null);
            weatherOffset7.setText("");
            rain7.setText("");
            airTemperature7.setText("");
            trackTemperature7.setText("");
        }
        sessionTypeCount -= 1;
        if (setUpWeather() == 0 && sessionTypeCount > 4)
            scrollWeatherBackward();
    }

    int setUpWeather() {
        ArrayList<SessionPacket.WeatherForecast> weatherForecastList = PacketReceiver.getSessionPacket().getWeatherForecastList();
        weatherForecastList = (ArrayList<SessionPacket.WeatherForecast>) weatherForecastList.stream().filter(e -> e.getSessionType() == sessionTypeCount).collect(Collectors.toList());

        int weatherForecastListSize = weatherForecastList.size(); // 6 quali and 7 race

        for (int i = 0; i < weatherForecastListSize; ++i) {

            switch (i) {
                case 0 -> {
                    weatherIcon1.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset1.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature1.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature1.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain1.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
                case 1 -> {
                    weatherIcon2.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset2.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature2.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature2.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain2.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
                case 2 -> {
                    weatherIcon3.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset3.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature3.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature3.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain3.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
                case 3 -> {
                    weatherIcon4.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset4.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature4.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature4.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain4.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
                case 4 -> {
                    weatherIcon5.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset5.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature5.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature5.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain5.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
                case 5 -> {
                    weatherIcon6.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset6.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature6.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature6.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain6.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
                case 6 -> {
                    weatherIcon7.setImage(InfoHandler.getWeatherIcon(weatherForecastList.get(i).getWeather()));
                    weatherOffset7.setText(InfoHandler.processWeatherOffset(weatherForecastList.get(i).getTimeOffset()));
                    airTemperature7.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getAirTemperature()));
                    trackTemperature7.setText(InfoHandler.processTrackWeatherTemperature(weatherForecastList.get(i).getTrackTemperature()));
                    rain7.setText(InfoHandler.processWeatherRain(weatherForecastList.get(i).getRainPercentage()));
                }
            }
        }

        weatherSession.setText(InfoHandler.getSessionType(sessionTypeCount));

        return weatherForecastListSize;
    }

    // Отображение расширенной информации - выделение строки в таблице
    @FXML
    void getExtendedPlayerInformation() {

        RaceTable selectedItem = raceTable.getSelectionModel().getSelectedItem();
        extendedInfoThreadIsRunning = true;
        extendedInfoAnchorPane.setVisible(true);

        extendedInfoThread = new Thread(() -> {

            while (true) {

                System.out.println("СТАРТ РАСШИРЕННОЙ ИНФОРМАЦИИ");
                ArrayList<LapDataPacket.LapData> lapDataList = PacketReceiver.getLapDataPacket().getLapDataList();
                ArrayList<CarSetupsPacket.CarSetupsData> carSetupsDataList = PacketReceiver.getCarSetupsPacket().getCarSetupsDataList();
                ArrayList<CarTelemetryPacket.CarTelemetryData> carTelemetryDataList = PacketReceiver.getCarTelemetryPacket().getCarTelemetryDataList();
                SessionHistoryPacket sessionHistoryPacket;
                int size = lapDataList.size();

                int playerCarIndex = PacketReceiver.getSessionPacket().getPlayerCarIndex();
                int rivalPosition = selectedItem.carPosition;
                int rivalCarIndex = 0;

                for (int i = 0; i < size; ++i) {

                    if (lapDataList.get(i).getCarPosition() == rivalPosition) {
                        rivalCarIndex = i;
                        break;
                    }
                }

                playerCarPosition.setText(String.valueOf(lapDataList.get(playerCarIndex).getCarPosition()));
                rivalCarPosition.setText(String.valueOf(rivalPosition));
                name.setText(selectedItem.name);
                delta.setText(InfoHandler.processRaceDelta(lapDataList.get(playerCarIndex).getTotalDistance(),
                        carTelemetryDataList.get(playerCarIndex).getSpeed(),
                        lapDataList.get(playerCarIndex).getCarPosition(),
                        rivalPosition));

                int[] tyres = carTelemetryDataList.get(rivalCarIndex).getTyresInnerTemperature();
                leftFrontTyreTemperature.setText(String.valueOf(tyres[2]));
                leftRearTyreTemperature.setText(String.valueOf(tyres[0]));
                rightFrontTyreTemperature.setText(String.valueOf(tyres[3]));
                rightRearTyreTemperature.setText(String.valueOf(tyres[1]));

                frontWing.setText(String.valueOf(carSetupsDataList.get(rivalCarIndex).getFrontWing()));
                rearWing.setText(String.valueOf(carSetupsDataList.get(rivalCarIndex).getRearWing()));

                sessionHistoryPacket = PacketReceiver.getSessionHistoryPacket();

                //TODO: Синхронизация (как в квале)
                while (sessionHistoryPacket.getCarID() != rivalCarIndex) {
                    sessionHistoryPacket = PacketReceiver.getSessionHistoryPacket();
                }

                int numberOfLaps = sessionHistoryPacket.getNumberLaps();
                if (numberOfLaps > 1) {

                    ArrayList<SessionHistoryPacket.LapHistoryData> lapHistoryDataList = sessionHistoryPacket.getLapHistoryDataList();
                    long fastestLap = lapHistoryDataList.get(0).getLapTime();

                    for (int i = 1; i < numberOfLaps - 1; ++i) {

                        if (lapHistoryDataList.get(i).getLapTime() < fastestLap)
                            fastestLap = lapHistoryDataList.get(i).getLapTime();
                    }

                    bestLap.setText(InfoHandler.processLapSectorTime(fastestLap));
                }
                if (numberOfLaps == 2) {
                    lastLapNumber1.setText(String.valueOf(numberOfLaps - 1));

                    lastLap1.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(0).getLapTime()));
                }
                if (numberOfLaps == 3) {
                    lastLapNumber1.setText(String.valueOf(numberOfLaps - 1));
                    lastLapNumber2.setText(String.valueOf(numberOfLaps - 2));

                    lastLap1.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 2).getLapTime()));
                    lastLap2.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(0).getLapTime()));
                }
                if (numberOfLaps == 4) {
                    lastLapNumber1.setText(String.valueOf(numberOfLaps - 1));
                    lastLapNumber2.setText(String.valueOf(numberOfLaps - 2));
                    lastLapNumber3.setText(String.valueOf(numberOfLaps - 3));

                    lastLap1.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 2).getLapTime()));
                    lastLap2.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 3).getLapTime()));
                    lastLap3.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(0).getLapTime()));
                }
                if (numberOfLaps == 5) {
                    lastLapNumber1.setText(String.valueOf(numberOfLaps - 1));
                    lastLapNumber2.setText(String.valueOf(numberOfLaps - 2));
                    lastLapNumber3.setText(String.valueOf(numberOfLaps - 3));
                    lastLapNumber4.setText(String.valueOf(numberOfLaps - 4));

                    lastLap1.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 2).getLapTime()));
                    lastLap2.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 3).getLapTime()));
                    lastLap3.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 4).getLapTime()));
                    lastLap4.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(0).getLapTime()));
                }
                if (numberOfLaps > 5) {
                    lastLapNumber1.setText(String.valueOf(numberOfLaps - 1));
                    lastLapNumber2.setText(String.valueOf(numberOfLaps - 2));
                    lastLapNumber3.setText(String.valueOf(numberOfLaps - 3));
                    lastLapNumber4.setText(String.valueOf(numberOfLaps - 4));
                    lastLapNumber5.setText(String.valueOf(numberOfLaps - 5));

                    lastLap1.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 2).getLapTime()));
                    lastLap2.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 3).getLapTime()));
                    lastLap3.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 4).getLapTime()));
                    lastLap4.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 5).getLapTime()));
                    lastLap5.setText(InfoHandler.processLapSectorTime(sessionHistoryPacket.getLapHistoryDataList().get(numberOfLaps - 6).getLapTime()));
                }

                try {
                    if (!extendedInfoThreadIsRunning) {
                        System.out.println("STOP");
                        break;
                    }
                    Thread.sleep(2500);
                } catch (InterruptedException ignored) {

                }
            }
        });

        extendedInfoThread.start();

    }
    @FXML
    void stopExtendedPlayerInformation() {

        extendedInfoThreadIsRunning = false;
        extendedInfoAnchorPane.setVisible(false);
    }
}
