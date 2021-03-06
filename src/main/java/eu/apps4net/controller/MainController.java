package eu.apps4net.controller;

import eu.apps4net.Api;
import eu.apps4net.Main;
import eu.apps4net.model.Bookmark;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiannis Kiranis <yiannis.kiranis@gmail.com>
 * https://apps4net.eu
 * Date: 21/3/22
 * Time: 11:47 μ.μ.
 */

public class MainController {
    private List<Bookmark> bookmarks = new ArrayList<>();
    private Api api = new Api();

    @FXML
    private TextArea bookmarksTextArea;

    @FXML
    private Button accessButton, deleteButton, fetchButton;

    @FXML
    protected void initialize() {
        deleteButton.setVisible(false);
        fetchButton.setVisible(false);

        if (api.testAuthenticated()) {
            accessButton.setVisible(false);
            fetchButton.setVisible(true);
        }
    }

    // Get the bookmarks from Pocket when button is pressed
    public void fetchBookmarks(ActionEvent event) {
        Scene scene = fetchButton.getScene();
        scene.setCursor(Cursor.WAIT);

        // Run service in thread
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                bookmarks = api.getBookmarks();

                return null ;
            }
        };

        task.setOnSucceeded(e -> {
            scene.setCursor(Cursor.DEFAULT);

            if(!bookmarks.isEmpty()) {
                deleteButton.setVisible(true);

                bookmarksTextArea.clear();

                for(Bookmark bookmark : bookmarks) {
                    bookmarksTextArea.appendText(bookmark.getUrl() + "\n");
                }
            } else {
                bookmarksTextArea.clear();

                Alert alert = new Alert(Alert.AlertType.NONE, "Bookmarks not found", ButtonType.APPLY);
                alert.show();
            }
        });

        new Thread(task).start();
    }

    // Initiate the authentication sequence when button is pressed
    // Opening new window
    public void getAccess(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("Auth.fxml"));

        Scene scene = new Scene(root, 1148, 592);

        AuthController.mainController = this;

        stage.setTitle("Grant Access");
        stage.setScene(scene);
        stage.show();
    }

    // Delete the bookmarks when button is pressed
    public void deleteBookmarks(ActionEvent event) {
        Scene scene = fetchButton.getScene();
        scene.setCursor(Cursor.WAIT);

        // Run service in thread
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                api.deleteBookmarks(bookmarks);

                return null;
            }
        };

        task.setOnSucceeded(e -> {
            scene.setCursor(Cursor.DEFAULT);

            deleteButton.setVisible(false);
            bookmarksTextArea.clear();
            Alert alert = new Alert(Alert.AlertType.NONE, "Bookmarks deleted!", ButtonType.APPLY);
            alert.show();
        });

        new Thread(task).start();
    }

    // Trigger when authenticated is completed
    public void authenticatedIsOk() {
        fetchButton.setVisible(true);
        accessButton.setVisible(false);
    }
}
