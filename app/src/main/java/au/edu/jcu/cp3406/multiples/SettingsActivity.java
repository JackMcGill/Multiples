package au.edu.jcu.cp3406.multiples;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences settingsData;

    private String name;
    private EditText nameEditText;
    private int numberOfRounds;
    private EditText roundsEditText;
    private boolean isHardMode;
    private RadioButton difficultyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.setTitle("Settings");

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        settingsData = getSharedPreferences("settings", Context.MODE_PRIVATE);
        nameEditText = findViewById(R.id.name);
        roundsEditText = findViewById(R.id.numberOfRounds);

        loadSettings();
        updateView();
    }

    public void difficultySet(View view) {
        difficultyButton = (RadioButton) view;
        String buttonText = difficultyButton.getText().toString().toLowerCase();

        switch (buttonText) {
            case "easy":
                Log.i("SettingsActivity", "Difficulty set to: easy");
                isHardMode = false;
                break;
            case "hard":
                Log.i("SettingsActivity", "Difficulty set to: hard");
                isHardMode = true;
                break;
            default:
                isHardMode = false;
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveSettings();
        }
        Log.i("settings", "upPressed");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Settings Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    public void saveSettings() {
        SharedPreferences.Editor editor = settingsData.edit();

        name = nameEditText.getText().toString();
        numberOfRounds = Integer.parseInt(roundsEditText.getText().toString());

        editor.putString("name", name);
        editor.putInt("rounds", numberOfRounds);
        editor.putBoolean("hardMode", isHardMode);

        editor.apply();

        Toast.makeText(this, "Preferences Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadSettings() {
        name = settingsData.getString("name", "Player"); // default name is Player
        numberOfRounds = settingsData.getInt("rounds", 5); // default number of rounds is 5
        isHardMode = settingsData.getBoolean("hardMode", false); // default difficulty is easy
    }

    public void updateView() {
        nameEditText.setText(name);
        roundsEditText.setText(String.valueOf(numberOfRounds));

        if (isHardMode) {
            difficultyButton = findViewById(R.id.hardDifficulty);
        } else {
            difficultyButton = findViewById(R.id.easyDifficulty);
        }
        difficultyButton.setChecked(true);
    }
}