package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Models.User;
import bgv.fit.bstu.eday.Utils.APIs;
import bgv.fit.bstu.eday.Utils.TaskService;
import bgv.fit.bstu.eday.Utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTaskActivity extends AppCompatActivity {
    EditText name, description, date, time;
    TaskService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        init();
    }

    public void SaveTask(View view){
        if (name.getText().toString().equals("") || description.getText().toString().equals("") || date.getText().toString().equals("") || time.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                Task task = new Task();
                task.setName(name.getText().toString());
                task.setDescription(description.getText().toString());
                task.setDate(date.getText().toString());
                task.setTime(time.getText().toString());
                addTask(task);
                finishActivity(0);
                Log.d("Task add", "Success");
            } catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Не получилось добавить", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addTask(Task t){
        service= APIs.getTaskService();
        Call<Task> call=service.addTask(t, MainActivity.UserId);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Успешное добавление", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void init(){
        name = findViewById(R.id.tnameet);
        description = findViewById(R.id.descret);
        date = findViewById(R.id.dateet);
        time = findViewById(R.id.timeet);
    }
}