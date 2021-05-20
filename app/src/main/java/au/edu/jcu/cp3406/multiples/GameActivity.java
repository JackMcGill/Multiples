package au.edu.jcu.cp3406.multiples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private Timer timer;
    private TextView timerView;
    private boolean timeIsUp;
    private Handler handler;
    private int numberOfRounds;
    private int timePerRound;
    private boolean isHardMode;
    private Game game;
    private TextView numberView;
    private TextView instructions;
    private TableLayout tableLayout;
    private TableRow row;
    private Button button;
    private Button nextRoundButton;
    private int totalScore;
    private int guessedCorrect;
    private SensorManager sensorManager;
    private Sensor sensor;
    private long mLastShakeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        mLastShakeTime = System.currentTimeMillis();

        loadSettings();

        // game creation is done on a separate thread, otherwise the app crashes occasionally when run on the main thread
        Thread createGame = new Thread(() -> game = new Game(numberOfRounds, isHardMode));
        createGame.start();
        try {
            createGame.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        numberView = findViewById(R.id.numberTextView);
        instructions = findViewById(R.id.instructions);
        tableLayout = findViewById(R.id.table_layout);
        nextRoundButton = findViewById(R.id.next_round);

        totalScore = 0;
        guessedCorrect = 0;

        updateView();
        enableTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();

            // check if a minimum of 1 second has elapsed since last "shake" of the device
            if ((currentTime - mLastShakeTime) > 1000) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;

                if (acceleration > 1f) {
                    mLastShakeTime = currentTime;
                    Log.i("GameActivity", "shake it");
                    endOfRound();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // this method is not used.
    }

    public void loadSettings() {
        SharedPreferences settingsData = getSharedPreferences("settings", Context.MODE_PRIVATE);
        numberOfRounds = settingsData.getInt("rounds", 5);
        timePerRound = settingsData.getInt("timePerRound", 10);
        isHardMode = settingsData.getBoolean("hardMode", false);
    }

    public void updateView() {
        this.setTitle("Round " + (game.getCurrentRound() + 1));
        if (game.getRoundType() == 0) {
            instructions.setText(R.string.multiples_instructions);
        } else instructions.setText(R.string.factors_instructions);

        numberView.setText(String.valueOf(game.getNumber()));
        populateButtons();
        timer = new Timer(timePerRound);
        timerView = findViewById(R.id.timerView);
        timeIsUp = false;

        enableTimer();
    }

    private void enableTimer() {
        int time = 1000;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!timeIsUp) {
                    timer.tick();
                    handler.postDelayed(this, time);
                    timerView.setText(String.format("Time left: %s%s", timer.toString(), getString(R.string.time_left)));
                    if (timer.getTimeLeft() == 0) {
                        timeIsUp = true;
                        endOfRound();
                    }
                }
            }
        });

    }

    private void populateButtons() {
        int[] options = game.getAllOptions();
        int index = 0;

        for (int i = 0; i < 5; ++i) {
            row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 2; ++j) {
                Log.i("GameActivity", "Number: " + options[index]);
                button = (Button) row.getChildAt(j);
                String numberAsString = String.valueOf(options[index]);
                button.setText(numberAsString);
                button.setEnabled(true);
                button.setTextColor(Color.WHITE);
                button.setVisibility(View.VISIBLE);
                index++;
            }
        }
    }

    public void buttonPressed(View view) {
        Button button = (Button) view;
        button.setEnabled(false);

        String buttonNumber = button.getText().toString();
        int playerAns = Integer.parseInt(buttonNumber);

        if (game.checkAnswer(playerAns)) {
            button.setTextColor(0xff669900);
            // score 1 point
            guessedCorrect++;
        } else {
            button.setTextColor(Color.RED);
            // score no points
        }

        if (guessedCorrect == game.getNumberOfAnswers()) {
            totalScore++;
            endOfRound();
        }

        Log.i("GameActivity", button.getText() + " pressed");
        Log.i("GameActivity", "Score is: " + totalScore);
        Log.i("GameActivity", "guessedCorrect: " + guessedCorrect);
    }

    private void endOfRound() {
        // disable and hide buttons
        for (int i = 0; i < 5; ++i) {
            row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 2; ++j) {
                button = (Button) row.getChildAt(j);
                button.setEnabled(false);
                button.setVisibility(View.GONE);
            }
        }

        // display a message to the player
        String endOfRoundMessage;
        if (guessedCorrect == game.getNumberOfAnswers()) {
            endOfRoundMessage = "Congratulations! You got all " + game.getNumberOfAnswers() + " correct!";
        } else {
            endOfRoundMessage = "You got " + guessedCorrect + " correct out of " + game.getNumberOfAnswers();
        }

        // enable and show next_round button
        nextRoundButton.setVisibility(View.VISIBLE);
        nextRoundButton.setEnabled(true);

        instructions.setText(endOfRoundMessage);
        guessedCorrect = 0;

        timeIsUp = true;
    }

    public void nextRound(View view) {
        nextRoundButton.setVisibility(View.GONE);
        nextRoundButton.setEnabled(false);
        timeIsUp = false;
        timer.reset();

        boolean gameIsOver = game.nextRound();

        if (gameIsOver) {
            gameOver();
            Log.i("GameActivity", "Game is over");
        } else {
            updateView();
        }
    }

    private void gameOver() {
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("score", totalScore);
        intent.putExtra("numberOfRounds", totalScore);
        startActivity(intent);
    }
}