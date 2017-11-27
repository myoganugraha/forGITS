package itk.myoganugraha.soalgits.apihelper;

/**
 * Created by M. Yoga Nugraha on 11/27/2017.
 */

public class UtlisApi {
    public static final String BASE_URL_API = "http://entry.sandbox.gits.id/api/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
