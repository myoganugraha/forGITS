package itk.myoganugraha.soalgits.apihelper;

import itk.myoganugraha.soalgits.JSONResponse.JSONResponseDatRendah;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseDatTinggi;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseDetail;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseMainMenu;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponsePantai;
import itk.myoganugraha.soalgits.Model.DetailWisata;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    @FormUrlEncoded
    @POST("api/post/data/upload")
    Call<ResponseBody> posting     (@Field("judul") String Judul,
                                    @Field("location") String location,
                                    @Field("kategori") String kategori,
                                    @Field("deskripsi") String deskripsi,
                                    //@Field("id_user") String id_user,
                                    @Field("image") String image);



    @GET("api/get/dataalam")
    Call<JSONResponseMainMenu> getJSONDataAlam();

    @GET("api/get/filter/dataalam?kategori=1")
    Call<JSONResponseDatTinggi> getJSONDatTinggi();

    @GET("api/get/filter/dataalam?kategori=2")
    Call<JSONResponseDatRendah> getJSONDatRendah();

    @GET("api/get/filter/dataalam?kategori=3")
    Call<JSONResponsePantai> getJSONPantai();

    @GET("api/get/detil/dataalam?itemid=1")
    Call<JSONResponseDetail> getJSONDetail();



}
