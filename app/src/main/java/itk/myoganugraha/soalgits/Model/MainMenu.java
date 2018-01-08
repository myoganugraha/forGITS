package itk.myoganugraha.soalgits.Model;

import java.util.jar.Manifest;

/**
 * Created by M. Yoga Nugraha on 1/8/2018.
 */

public class MainMenu {
    private int icon;
    private String menu;

    public MainMenu(int icon, String menu){
        this.icon = icon;
        this.menu = menu;
    }



    public int getIcon(){
        return icon;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public String getMenu(){
        return menu;
    }

    public void setMenu(String menu){
        this.menu = menu;
    }

}
