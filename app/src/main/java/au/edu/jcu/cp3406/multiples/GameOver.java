package au.edu.jcu.cp3406.multiples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GameOver extends AppCompatActivity {

    private String name;
    private int score;
    private boolean isHardMode;
    private Twitter twitter;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = getIntent().getIntExtra("score", 0);
        message = findViewById(R.id.game_over_message);
        loadSettings();
        String generatedMessage = generateMessage();
        message.setText(generatedMessage);
    }

    public void loadSettings() {
        SharedPreferences settingsData = getSharedPreferences("settings", Context.MODE_PRIVATE);

        name = settingsData.getString("name", "Player"); // default name is Player
        Log.i("settings", "name (loadSettings): " + name);
        isHardMode = settingsData.getBoolean("hardMode", false); // default difficulty is easy
    }


    // Generates a message to be displayed to the player
    private String generateMessage() {
        String mode;
        if (isHardMode) {
            mode = "hard mode";
        } else {
            mode = "easy mode";
        }

        String formattedMessage = String.format(getString(R.string.end_of_game_message), score, mode);
        return formattedMessage + "!";
    }


    public void shareButtonPressed(View view) {
        final String consumerKey = "COJmbfyLrsQtN3zmESIzXjZQf";
        final String consumerKeySecret = "Yd4Sz5d3GPCEZNdujnIIxHJaiwIVpUZWlKDCOkJg9QF0MpNOo6";
        final String accessToken = "1250966422458527745-XBMhOAaSDDUHggUfWwNxVpzm31znBd";
        final String accessTokenSecret = "Z8SM49sY8fFdHu6flZT5GTzzxFrLlAQHHYbx6pwrJtz37";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerKeySecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        AsyncTask.execute(() -> {
            try {
                twitter.updateStatus(generateTweet());
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        });
    }

    // Generates a message to be tweeted
    private String generateTweet() {
        String mode;
        if (isHardMode) {
            mode = "hard mode";
        } else {
            mode = "easy mode";
        }

        String formattedMessage = String.format(getString(R.string.twitter_message), name, score, mode);
        return formattedMessage + "!";
    }

    public void mainMenuButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void newGameButtonPressed(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        AsyncTask.execute(() -> startActivity(intent));
    }
}