package eu.apps4net.pocketparser;

import com.google.gson.*;
import eu.apps4net.pocketparser.model.Bookmark;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Api {
    private static final String URL = "https://getpocket.com/";
    private String json;
    private String jsonQuery;
    private String consumerKey = "101349-9a368bc1aed578ac661a380";
    private String accessToken = "7b610ecf-5004-469a-4798-95a275";

    public void setJsonQuery(String jsonQuery) {
        this.jsonQuery = jsonQuery;
    }

    public String getJson() {
        return json;
    }

    // Παίρνει το json string μετά την κλήση του κατάλληλου api
    public void getJsonString() throws Exception {
        String jsonUrl = URL + jsonQuery + "?consumer_key=" + consumerKey + "&access_token=" + accessToken;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(jsonUrl).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                json = response.body().string();
            } else {
                throw new Exception("Υπάρχει πρόβλημα στην άντληση των δεδομένων");
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public List<Bookmark> getBookmarks() {
        try {
            List<Bookmark> bookmarks = new ArrayList<>();

            this.setJsonQuery("v3/get");
            // Κάνει την κλήση στο api
            this.getJsonString();

            // Μετατρέπει το json string σε object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            // Διαβάζει το array του json
            JsonObject list = json.getAsJsonObject("list");

            JsonArray jsonArray = new JsonArray();

            list.keySet().stream().forEach(key -> {
                jsonArray.add(list.get(key));
            });

            for (JsonElement jsonBookmark : jsonArray) {
                bookmarks.add(getBookmarkObject(jsonBookmark.getAsJsonObject()));
            }

            return bookmarks;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }


    public Bookmark getBookmarkObject(JsonObject json) {
        Bookmark bookmark = new Bookmark();

        bookmark.setId(json.get("item_id").getAsLong());
        bookmark.setUrl(json.get("given_url").getAsString());
        bookmark.setTittle(json.get("given_title").getAsString());
        bookmark.setDescription(json.get("excerpt").getAsString());

        return bookmark;
    }


}
