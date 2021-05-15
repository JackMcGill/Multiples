package au.edu.jcu.cp3406.multiples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private TextView numberView;
    private TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(5); // temporarily hardcoded
        numberView = findViewById(R.id.numberTextView);
        instructions = findViewById(R.id.instructions);

        updateView();

    }

    public void updateView() {
        if (game.getRoundType() == 0) {
            instructions.setText(R.string.multiples_instructions);
        } else instructions.setText(R.string.factors_instructions);

        numberView.setText(String.valueOf(game.getNumber()));

    }


}