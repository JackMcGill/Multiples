package au.edu.jcu.cp3406.multiples;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private TextView numberView;
    private TextView instructions;
    private TableLayout tableLayout;
    private TableRow row;
    private Button button;
    private Button nextRound;
    private int[] options;
    private int totalScore;
    private int guessedCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = new Game(5); // temporarily hardcoded
        numberView = findViewById(R.id.numberTextView);
        instructions = findViewById(R.id.instructions);
        tableLayout = findViewById(R.id.table_layout);
        nextRound = findViewById(R.id.next_round);
        totalScore = 0;
        guessedCorrect = 0;

        updateView();
    }

    public void updateView() {
        this.setTitle("Round " + game.getRound() + 1);
        if (game.getRoundType() == 0) {
            instructions.setText(R.string.multiples_instructions);
        } else instructions.setText(R.string.factors_instructions);

        numberView.setText(String.valueOf(game.getNumber()));
        populateButtons();
    }

    private void populateButtons() {
        options = game.getAllOptions();
        int index = 0;

        for (int i = 0; i < 5; ++i) {
            row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 2; ++j) {
                Log.i("GameActivity", "Number: " + String.valueOf(options[index]));
                button = (Button) row.getChildAt(j);
                String numberAsString = String.valueOf(options[index]);
                button.setText(numberAsString);
                button.isEnabled();
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
            totalScore++;
            guessedCorrect++;
        } else {
            button.setTextColor(Color.RED);
            // score no points
        }

        if (guessedCorrect == game.getNumberOfAnswers()) {
            endOfRound();
        }

        Log.i("GameActivity", button.getText() + " pressed");
        Log.i("GameActivity", "Score is: " + totalScore);
        Log.i("GameActivity", "guessedCorrect: " + guessedCorrect);
    }

    private void endOfRound() {
        // disable and hide buttons
        int index = 0;

        for (int i = 0; i < 5; ++i) {
            row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 2; ++j) {
                Log.i("GameActivity", "Number: " + String.valueOf(options[index]) + " disabled");
                button = (Button) row.getChildAt(j);
                button.setEnabled(false);
                button.setVisibility(View.GONE);
                index++;
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
        nextRound.setVisibility(View.VISIBLE);
        nextRound.isEnabled();

        instructions.setText(endOfRoundMessage);
        guessedCorrect = 0;
    }

    public void nextRound(View view) {
        nextRound.setVisibility(View.GONE);
        nextRound.setEnabled(false);
        updateView();
    }
}