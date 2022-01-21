package bgv.fit.bstu.eday.Utils;

import java.util.List;

import bgv.fit.bstu.eday.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("list")
    Call<List<User>> getUsers();

    @GET("user")
    Call<List<User>> getUser(@Query("login") String login, @Query("password") String password);

    @Headers("Content-Type: application/json")
    @POST("adding")
    Call<User>addUser(@Body User user);

    @POST("editing/{id}")
    Call<User>updateUser(@Body User user,@Path("id") int id);

    @POST("deleting/{id}")
    Call<User>deleteUser(@Path("id")int id);

}
