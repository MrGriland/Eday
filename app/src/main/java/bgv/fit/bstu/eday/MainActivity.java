package bgv.fit.bstu.eday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bgv.fit.bstu.eday.Models.User;
import bgv.fit.bstu.eday.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    SQLiteDatabase db;
    WorkWithDB workWithDB;
    TextView weatherinfo;
    public static Integer UserId = 0;
    public static String UserName = "";
    public static String UserSurname = "";
    public static byte [] UserPhoto=null;
    public static String UserLogin = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getWritableDatabase();
        workWithDB = new WorkWithDB(db);
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.page_1:
                            selectedFragment = new FirstFragment();
                            break;
                        case R.id.page_2:
                            selectedFragment = new SecondFragment();
                            break;
                        case R.id.page_3:
                            selectedFragment = new ThirdFragment();
                            break;
                    }
                    if (selectedFragment!=null)
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                    return true;
                }
            };

    public void CreateTaskPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewTaskActivity.class);
        startActivity(intent);
    }


}