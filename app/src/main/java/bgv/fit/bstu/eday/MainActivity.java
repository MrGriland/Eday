package bgv.fit.bstu.eday;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import bgv.fit.bstu.eday.Models.User;

public class MainActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    SQLiteDatabase db;
    WorkWithDB workWithDB;
    public static Integer UserId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DBHelper(getApplicationContext());
        db = databaseHelper.getWritableDatabase();
        workWithDB = new WorkWithDB(db);
        TextView textView = findViewById(R.id.rr);
        textView.setText(String.valueOf(UserId));
    }
}