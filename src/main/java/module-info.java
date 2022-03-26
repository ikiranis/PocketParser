module eu.apps4net {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires dotenv.java;
    requires okhttp3;
    requires javafx.web;

    opens eu.apps4net to javafx.fxml;
    opens eu.apps4net.controller to javafx.fxml;
    exports eu.apps4net.controller;
    exports eu.apps4net;
}
