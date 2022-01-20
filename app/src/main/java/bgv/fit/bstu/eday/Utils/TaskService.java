package bgv.fit.bstu.eday.Utils;

import java.util.List;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskService {
    @GET("list")
    Call<List<Task>> getTasks();

    @POST("adding")
    Call<Task>addTask(@Body Task task);

    @POST("editing/{id}")
    Call<Task>updateTask(@Body Task task,@Path("id") int id);

    @POST("deleting/{id}")
    Call<Task>deleteTask(@Path("id")int id);

}
