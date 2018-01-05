package itk.myoganugraha.soalgits.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by M. Yoga Nugraha on 11/27/2017.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("api/post/user/login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("api/post/user/account")
    Call<ResponseBody> registerRequest(@Field("first_name") String first_name,
                                       @Field("last_name") String last_name,
                                       @Field("username") String username,
                                       @Field("password") String password,
                                       @Field("bdate") String bdate,
                                       @Field("gender") String gender,
                                       @Field("phone") String phone);

}
