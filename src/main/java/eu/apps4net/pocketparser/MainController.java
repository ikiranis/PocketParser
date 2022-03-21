package eu.apps4net.pocketparser;

import eu.apps4net.pocketparser.model.Bookmark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

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
            bookmarksTextArea.appendText(String.valueOf(bookmark.getUrl()) + "\n");
        }

    }
}
