package co.dakuji.technical_test.interf;

import co.dakuji.technical_test.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Server {

    public static String BASE_URL = "http://itotalcolombia.com:3700";

    @FormUrlEncoded
    @POST("/login")
    Call<User> postAuthentication(@Field("nickname") String user,
                                  @Field("password") String password);
}
