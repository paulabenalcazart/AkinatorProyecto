module ec.edu.uees.akinatorproyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens ec.edu.uees.akinatorproyecto to javafx.fxml;
    exports ec.edu.uees.akinatorproyecto;
}
