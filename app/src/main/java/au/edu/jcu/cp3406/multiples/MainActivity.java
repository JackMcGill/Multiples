package au.edu.jcu.cp3406.multiples;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static au.edu.jcu.cp3406.multiples.R.id.settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startGamePressed(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        // prevents app crashing occaisonally.
        AsyncTask.execute(() -> startActivity(intent));
    }

    public void howToPressed(View view) {
        Intent intent = new Intent(this, HowTo.class);
        startActivity(intent);
    }
}