package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Utils.APIs;
import bgv.fit.bstu.eday.Utils.TaskService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    Task t;
    EditText name, description, date, time;
    TaskService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle arguments = getIntent().getExtras();
        t = (Task) arguments.getSerializable("task");
        init();
    }

    public void EditTask(View view){
        if (name.getText().toString().equals("") || description.getText().toString().equals("") || date.getText().toString().equals("") || time.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                Task task = new Task();
                task.setId(t.getId());
                task.setName(name.getText().toString());
                task.setDescription(description.getText().toString());
                task.setDate(date.getText().toString());
                task.setTime(time.getText().toString());
                sendEditedTask(task);
                Log.d("Task add", "Success");
            } catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Не получилось добавить", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendEditedTask(Task task){
        service= APIs.getTaskService();
        Call<Task> call=service.updateTask(task, t.getId());
        Toast.makeText(getApplicationContext(),t.getId().toString(),Toast.LENGTH_LONG).show();
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
        name = findViewById(R.id.etnameet);
        description = findViewById(R.id.edescret);
        date = findViewById(R.id.edateet);
        time = findViewById(R.id.etimeet);
        name.setText(t.getName());
        description.setText(t.getDescription());
        date.setText(t.getDate());
        time.setText(t.getTime());
    }
}