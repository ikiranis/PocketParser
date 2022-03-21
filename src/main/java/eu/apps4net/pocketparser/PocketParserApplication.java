package eu.apps4net.pocketparser;

import eu.apps4net.pocketparser.model.Bookmark;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PocketParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocketParserApplication.class, args);

        List<Bookmark> bookmarks = new ArrayList<>();
        Api api = new Api();

        bookmarks = api.getBookmarks();

        System.out.println(bookmarks);
    }



}
