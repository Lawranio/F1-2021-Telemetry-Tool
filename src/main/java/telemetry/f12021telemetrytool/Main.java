package telemetry.f12021telemetrytool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import telemetry.f12021telemetrytool.packetHandler.PacketReceiver;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Interface.fxml"));
        File file = new File("assets\\icon.png");
        Image icon = new Image(file.getAbsolutePath());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("F1 2021 Telemetry Tool");
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) throws SocketException, InterruptedException {

        PacketReceiver packetReceiver = new PacketReceiver();
        packetReceiver.start();

        Thread.sleep(4500);
        launch();
    }
}
