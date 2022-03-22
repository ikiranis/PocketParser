package eu.apps4net.pocketparser;

import eu.apps4net.pocketparser.model.Bookmark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;

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

    @FXML
    private Hyperlink urlText;

    public void fetchBookmarks(ActionEvent actionEvent) {
        Api api = new Api();
        List<Bookmark> bookmarks = api.getBookmarks();

        for(Bookmark bookmark : bookmarks) {
            bookmarksTextArea.appendText(String.valueOf(bookmark.getUrl()) + "\n");
        }

    }

    public void authenticate(ActionEvent actionEvent) {
        Api api = new Api();

        String url = api.requestApiCode();

        urlText.setText("Press Here!");

        // Open url on browser
        urlText.setOnAction(t -> {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)){
                try {
                    desktop.browse(new URI(url));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getAccessToken(ActionEvent actionEvent) {
        Api api = new Api();

        api.getAccessToken();
    }
}
