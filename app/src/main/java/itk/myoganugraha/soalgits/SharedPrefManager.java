package itk.myoganugraha.soalgits;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by M. Yoga Nugraha on 1/6/2018.
 */

public class SharedPrefManager {

    public static final String SP_GITS_APP = "spGits";

    public static final String SP_USERNAME = "spUsername";

    public static final String SP_SUDAH_LOGIN = "Is Logged";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_GITS_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_USERNAME, "");
    }


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
