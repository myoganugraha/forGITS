package itk.myoganugraha.soalgits.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by M. Yoga Nugraha on 1/8/2018.
 */

public class MainMenuDataAlam {
    @SerializedName("title")
    private String title;

    @SerializedName("url_image")
    private String url_image;





    public MainMenuDataAlam(String title, String url_image){
        this.url_image = url_image;
        this.title = title;
    }



    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getUrl_image(){
        return url_image;
    }

    public void setUrl_image(String url_image){
        this.url_image = url_image;
    }
}
