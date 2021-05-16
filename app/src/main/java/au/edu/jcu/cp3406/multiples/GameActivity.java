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

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private TextView numberView;
    private TextView instructions;
    private TableLayout tableLayout;
    private TableRow row;
    private Button button;
    private int[] options;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(5); // temporarily hardcoded
        numberView = findViewById(R.id.numberTextView);
        instructions = findViewById(R.id.instructions);
        tableLayout = findViewById(R.id.table_layout);
        score = 0;

        updateView();
    }

    public void updateView() {
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
            score++;
        } else {
            button.setTextColor(Color.RED);
            // score no points
        }

        Log.i("GameActivity", button.getText() + " pressed");
        Log.i("GameActivity", "Score is: " + score);
    }
}