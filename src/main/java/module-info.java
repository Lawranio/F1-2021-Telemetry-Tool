module telemetry.f12021telemetrytool {
    requires javafx.controls;
    requires javafx.fxml;

    opens telemetry.f12021telemetrytool to javafx.fxml;
    exports telemetry.f12021telemetrytool;
    exports telemetry.f12021telemetrytool.view;
    opens telemetry.f12021telemetrytool.view to javafx.fxml;
}