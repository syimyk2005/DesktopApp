module sample.finalappjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens sample.finalappjfx to javafx.fxml;
    exports sample.finalappjfx;
}