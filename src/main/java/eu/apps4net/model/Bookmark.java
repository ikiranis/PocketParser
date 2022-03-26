package eu.apps4net.model;

/**
 * Created by Yiannis Kiranis <yiannis.kiranis@gmail.com>
 * https://apps4net.eu
 * Date: 21/3/22
 * Time: 8:04 μ.μ.
 */
public class Bookmark {
    private long id;
    private String url;
    private String tittle;
    private String description;

    public Bookmark() {}

    public Bookmark(long id, String url, String tittle, String description) {
        this.id = id;
        this.url = url;
        this.tittle = tittle;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "url='" + url + '\'' +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
