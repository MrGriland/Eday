package bgv.fit.bstu.eday;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Utils.APIs;
import bgv.fit.bstu.eday.Utils.TaskService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment {
    RecyclerView recyclerView;
    Context context=getContext();
    TaskService taskService;
    ArrayList<Task>taskList=new ArrayList<>();
    ArrayList<Task>todayTaskList=new ArrayList<>();

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.second_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.todaylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listTasks();
    }

    public void listTasks(){
        taskService= APIs.getTaskService();
        Call<List<Task>> call=taskService.getTasksForUser(MainActivity.UserId);
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if(response.isSuccessful()) {
                    taskList = (ArrayList<Task>) response.body();
                    Date d = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    try {
                        for (Task t : taskList) {
                            if (t.getDate().equals(dateFormat.format(cal.getTime())))
                                todayTaskList.add(t);
                        }
                    }
                    catch (Exception e) {}
                    Adapter();
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.d("Error:",t.getMessage());
            }
        });
    }

    public void Adapter() {
        TaskAdapter.OnStateClickListener stateClickListener = new TaskAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Task task, int position) {

            }
            // создаем адаптер
        };
        TaskAdapter taskAdapter = new TaskAdapter(this, todayTaskList, stateClickListener);
        recyclerView.setAdapter(taskAdapter);
    }
}