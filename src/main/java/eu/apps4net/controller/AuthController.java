package eu.apps4net.controller;

import eu.apps4net.Api;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.concurrent.Worker.State;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AuthController {

    @FXML
    private WebView web;

    @FXML
    protected void initialize() {
        authenticate();
    }

    private void authenticate() {
        Api api = new Api();

        String url = api.requestApiCode();

        WebEngine webEngine = web.getEngine();
        webEngine.load(url);

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {
                            System.out.println(webEngine.getLocation());

                            if(webEngine.getLocation().equals("https://apps4net.eu/")) {
                                api.getAccessToken();

                                Stage stage = (Stage) web.getScene().getWindow();
                                stage.close();
                            }

                        }
                    }
                });
    }

}
