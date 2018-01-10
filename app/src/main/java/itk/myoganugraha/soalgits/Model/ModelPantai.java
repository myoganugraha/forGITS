package itk.myoganugraha.soalgits.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by M. Yoga Nugraha on 1/9/2018.
 */

public class ModelPantai {
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url_image")
    private String url_image;

    @SerializedName("id_data")
    private String id_data;

    public ModelPantai(String title, String url_image, String description){
        this.url_image = url_image;
        this.title = title;
        this.description = description;
    }

    public String getId_data(){ return id_data; }

    public void setId_data(String id_data){ this.id_data = id_data;}

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
}
