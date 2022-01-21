package bgv.fit.bstu.eday.Utils;

import java.util.List;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TaskService {
    @GET("list")
    Call<List<Task>> getTasks();

    @GET("listforuser")
    Call<List<Task>> getTasksForUser(@Query("id") int id);


    @POST("adding/{id}")
    Call<Task>addTask(@Body Task task,@Path("id") int id);

    @POST("editing/{id}")
    Call<Task>updateTask(@Body Task task,@Path("id") int id);

    @POST("deleting/{id}")
    Call<Task>deleteTask(@Path("id")int id);

}
