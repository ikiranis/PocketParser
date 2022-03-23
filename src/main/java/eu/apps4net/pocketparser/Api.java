package eu.apps4net.pocketparser;

import com.google.gson.*;
import eu.apps4net.pocketparser.model.Bookmark;
import eu.apps4net.pocketparser.service.PropertiesService;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Api {
    private static final String URL = "https://getpocket.com/";
    private String json;
    private String jsonQuery;
    private static String consumerKey;
    private static String accessCode;
    private static String accessToken;
    Dotenv dotenv = Dotenv.load();

    public Api() {
        this.consumerKey = dotenv.get("CONSUMER_KEY");
    }

    public void setJsonQuery(String jsonQuery) {
        this.jsonQuery = jsonQuery;
    }

    public String getJson() {
        return json;
    }

    // Get the json string after calling the api
    private void getJsonString() throws Exception {
        String jsonUrl = URL + jsonQuery;
        System.out.println(jsonUrl);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("X-Accept", "application/json")
                .url(jsonUrl).build();

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

    // Get the Api code for consumer_key
    public String requestApiCode() {
        try {
            // Api call
            this.setJsonQuery("v3/oauth/request" + "?consumer_key=" + consumerKey + "&redirect_uri=https://apps4net.eu");
            this.getJsonString();

            // Convert string to json objects
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            accessCode = json.get("code").getAsString();

            PropertiesService properties = new PropertiesService();
            properties.setProperty("access_code", accessCode);
            properties.save();
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "https://getpocket.com/auth/authorize?request_token=" + accessCode + "&redirect_uri=https://apps4net.eu";
    }

    public void getAccessToken() {
        try {
            PropertiesService properties = new PropertiesService();

            System.out.println("v3/oauth/authorize" + "?consumer_key=" + consumerKey + "&code=" + accessCode);
            // Api call
            this.setJsonQuery("v3/oauth/authorize" + "?consumer_key=" + consumerKey + "&code=" + accessCode);
            this.getJsonString();

            // Convert string to json objects
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(this.getJson(), JsonObject.class);

            System.out.println(json);
            accessToken = json.get("access_token").getAsString();

            System.out.println(accessToken);
            properties.setProperty("access_token", accessToken);
            properties.save();

        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Call the api and get the bookmarks
    public List<Bookmark> getBookmarks() {
        try {
            PropertiesService properties = new PropertiesService();
            properties.load();

            List<Bookmark> bookmarks = new ArrayList<>();

            // Api call
            this.setJsonQuery("v3/get" + "?consumer_key=" + consumerKey + "&access_token=" + properties.getProperty("access_token"));
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
