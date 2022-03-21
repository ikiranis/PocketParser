package eu.apps4net.pocketparser;

import eu.apps4net.pocketparser.model.Bookmark;
import javafx.event.ActionEvent;

import java.util.List;

/**
 * Created by Yiannis Kiranis <yiannis.kiranis@gmail.com>
 * https://apps4net.eu
 * Date: 21/3/22
 * Time: 11:47 μ.μ.
 */

public class MainController {

    public void fetchBookmarks(ActionEvent actionEvent) {
        Api api = new Api();

        List<Bookmark> bookmarks = api.getBookmarks();

        System.out.println(bookmarks);
    }
}
