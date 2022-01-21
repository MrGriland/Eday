package bgv.fit.bstu.eday;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Utils.APIs;
import bgv.fit.bstu.eday.Utils.TaskService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {
    RecyclerView recyclerView;
    Context context=getContext();
    TaskService taskService;
    ArrayList<Task>taskList=new ArrayList<>();

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        Adapter();
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
        TaskAdapter taskAdapter = new TaskAdapter(this, taskList, stateClickListener);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = ((RecyclerContextMenuInfoWrapperView.RecyclerContextMenuInfo)item.getMenuInfo()).position;
        switch (item.getItemId()) {
            case R.id.edit:
                Task task = taskList.get(position);
                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra("task", (Serializable) task);
                startActivity(intent);
                return true;
            case R.id.delete:
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("Вы уверены?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // Кнопка ОК
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Task task = taskList.get(position);
                        deleteTask(task.getId());
                        taskList.remove(position);
                        listTasks();
                        dialog.dismiss(); // Отпускает диалоговое окно
                    }
                });
                builder.setNegativeButton("Oтмена", new DialogInterface.OnClickListener() { // Кнопка ОК
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Отпускает диалоговое окно
                    }
                });
                builder.create();
                builder.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteTask(int id){
        taskService=APIs.getTaskService();
        Call<Task>call=taskService.deleteTask(id);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity().getApplicationContext(),"Удалено",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}