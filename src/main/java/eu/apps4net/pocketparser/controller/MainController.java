package eu.apps4net.pocketparser.controller;

import eu.apps4net.pocketparser.Api;
import eu.apps4net.pocketparser.model.Bookmark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Yiannis Kiranis <yiannis.kiranis@gmail.com>
 * https://apps4net.eu
 * Date: 21/3/22
 * Time: 11:47 μ.μ.
 */

public class MainController {

    @FXML
    private TextArea bookmarksTextArea;

    public void fetchBookmarks(ActionEvent actionEvent) {
        Api api = new Api();
        List<Bookmark> bookmarks = api.getBookmarks();

        for(Bookmark bookmark : bookmarks) {
            bookmarksTextArea.appendText(bookmark.getUrl() + "\n");
        }
    }

    public void getAccess(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Auth.fxml"));

        Scene scene = new Scene(root, 508, 491);

        stage.setTitle("Grant Access");
        stage.setScene(scene);
        stage.show();
    }

}
