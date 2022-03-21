package eu.apps4net.pocketparser;

import com.google.gson.*;
import eu.apps4net.pocketparser.model.Bookmark;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    // Get the json string after calling the api
    public void getJsonString() throws Exception {
        String jsonUrl = URL + jsonQuery + "?consumer_key=" + consumerKey + "&access_token=" + accessToken;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(jsonUrl).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                json = response.body().string();
            } else {
                throw new Exception("Problem with api call");
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    // Call the api and get the bookmarks
    public List<Bookmark> getBookmarks() {
        try {
            List<Bookmark> bookmarks = new ArrayList<>();

            // Api call
            this.setJsonQuery("v3/get");
            this.getJsonString();

            // Convert string to json objects
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            // Get the json array
            JsonObject list = json.getAsJsonObject("list");

            // Convert jsonObject to jsonArray
            JsonArray jsonArray = new JsonArray();
            list.keySet().stream().forEach(key -> {
                jsonArray.add(list.get(key));
            });

            // Parse the bookmarks objects to Bookmark Entities
            for (JsonElement jsonBookmark : jsonArray) {
                bookmarks.add(getBookmarkObject(jsonBookmark.getAsJsonObject()));
            }

            return bookmarks;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }


    // Convert json object to Bookmark Entity
    public Bookmark getBookmarkObject(JsonObject json) {
        Bookmark bookmark = new Bookmark();

        bookmark.setId(json.get("item_id").getAsLong());
        bookmark.setUrl(json.get("given_url").getAsString());
        bookmark.setTittle(json.get("given_title").getAsString());
        bookmark.setDescription(json.get("excerpt").getAsString());

        return bookmark;
    }


}
