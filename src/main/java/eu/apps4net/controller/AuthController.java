package eu.apps4net.controller;

import eu.apps4net.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class AuthController {

    @FXML
    private Hyperlink urlText;

    @FXML
    private WebView web;

    public void authenticate(ActionEvent actionEvent) {
        Api api = new Api();

        String url = api.requestApiCode();

        urlText.setText("Request authentication!");

        // Open url on browser
        urlText.setOnAction(t -> {

            WebEngine webEngine = web.getEngine();
            webEngine.load(url);


//            Desktop desktop = Desktop.getDesktop();
//            System.out.println(desktop);
//            if (desktop.isSupported(Desktop.Action.BROWSE)){
//                try {
//                    desktop.browse(new URI(url));
//                } catch (IOException e) {
//                    System.out.println(e);
//                } catch (URISyntaxException e) {
//                    System.out.println(e + "url: " + url);
//                }
//            }
        });
    }

    public void getAccessToken(ActionEvent actionEvent) {
        Api api = new Api();

        api.getAccessToken();
    }
}