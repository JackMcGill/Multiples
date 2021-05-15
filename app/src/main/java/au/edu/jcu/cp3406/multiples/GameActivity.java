package au.edu.jcu.cp3406.multiples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private TextView numberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(5); // temporarily hardcoded
        numberView = findViewById(R.id.numberTextView);

        numberView.setText(String.valueOf(game.getNumber()));

    }

    public void updateView() {
        
    }

}