package eu.apps4net.pocketparser.controller;

import eu.apps4net.pocketparser.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthController {

    @FXML
    private Hyperlink urlText;

    public void authenticate(ActionEvent actionEvent) {
        Api api = new Api();

        String url = api.requestApiCode();

        urlText.setText("Request authentication!");

        // Open url on browser
        urlText.setOnAction(t -> {
            System.out.println("URL--> " + url);
            Desktop desktop = Desktop.getDesktop();
            System.out.println(desktop);
            if (desktop.isSupported(Desktop.Action.BROWSE)){
                try {
                    desktop.browse(new URI(url));
                } catch (IOException e) {
                    System.out.println(e);
                } catch (URISyntaxException e) {
                    System.out.println(e + "url: " + url);
                }
            }
        });
    }

    public void getAccessToken(ActionEvent actionEvent) {
        Api api = new Api();

        api.getAccessToken();
    }
}
