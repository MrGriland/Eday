package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bgv.fit.bstu.eday.Models.Task;
import bgv.fit.bstu.eday.Models.User;
import bgv.fit.bstu.eday.Utils.APIs;
import bgv.fit.bstu.eday.Utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText loginField,passwordField;
    UserService userService;
    ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        loginField = findViewById(R.id.et_login);
        passwordField = findViewById(R.id.et_password);
    }

    public void Authorization(View view){
        String log="";
        String pass="";
        log=loginField.getText().toString();
        pass=passwordField.getText().toString();
        if(log.equals("")||pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Пустые поля недопустимы", Toast.LENGTH_SHORT).show();
        }
        else {
            if(checkUser(log, pass)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Log.d("User login", "Success");
            }
            else
                Toast.makeText(getApplicationContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registration (View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        Log.d("Registration open","Success");
    }

    public void getUser(String login, String password){
        userService= APIs.getUserService();
        Call<List<User>> call=userService.getUser(login, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    userList = (ArrayList<User>) response.body();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkUser(String login, String password) {
        getUser(login, password);
        if(!userList.isEmpty()&&login.equals(userList.get(0).getLogin())&&password.equals(userList.get(0).getPassword())) {
            Toast.makeText(getApplicationContext(), userList.get(0).getName(),Toast.LENGTH_SHORT).show();
            MainActivity.UserId = userList.get(0).getId();
            MainActivity.UserName = userList.get(0).getName();
            MainActivity.UserSurname = userList.get(0).getSurname();
            MainActivity.UserPhoto = userList.get(0).getPhoto();
            MainActivity.UserLogin = userList.get(0).getLogin();
            userList.clear();
            return true;
        }
        else {
            userList.clear();
            return false;
        }
    }
}