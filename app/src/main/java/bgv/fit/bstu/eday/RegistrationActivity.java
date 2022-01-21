package bgv.fit.bstu.eday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import bgv.fit.bstu.eday.Models.User;
import bgv.fit.bstu.eday.Utils.APIs;
import bgv.fit.bstu.eday.Utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageView;
    EditText name, surname, login, password;
    UserService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Register(View view) {
        if (name.getText().toString().equals("") || surname.getText().toString().equals("") || login.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                User user = new User();
                user.setName(name.getText().toString());
                user.setSurname(surname.getText().toString());
                user.setLogin(login.getText().toString());
                user.setPassword(password.getText().toString());
                addUser(user);
                //workWithDB.InsertUser(user);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                Log.d("Registration open", "Success");
            } catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Не получилось зарегистрироваться", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addUser(User u){
        service= APIs.getUserService();
        Call<User> call=service.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Успешная регистрация", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void init(){
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        login=(EditText) findViewById(R.id.login);
        password=(EditText) findViewById(R.id.password);
    }
}