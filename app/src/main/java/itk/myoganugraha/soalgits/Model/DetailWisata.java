package itk.myoganugraha.soalgits.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by M. Yoga Nugraha on 1/10/2018.
 */

public class DetailWisata {
    @SerializedName("id_data")
    private String id_data;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url_image")
    private String url_image;

    @SerializedName("category")
    private String category;

    @SerializedName("Location")
    private String Location;

    public DetailWisata(String id_data, String title, String description, String url_image, String category, String Location){
        this.id_data = id_data;
        this.description = description;
        this.title = title;
        this.url_image = url_image;
        this.category = category;
        this.Location = Location;
    }

    public String getId_data(){
        return id_data;
    }

    public void setId_data(String id_data){
        this.id_data = id_data;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getUrl_image(){
        return url_image;
    }

    public void setUrl_image(String url_image){
        this.url_image = url_image;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getLocation(){
        return Location;
    }

    public void setLocation(String Location){
        this.Location = Location;
    }
}
