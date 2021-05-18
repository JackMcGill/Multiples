package au.edu.jcu.cp3406.multiples;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GameOver extends AppCompatActivity {

    private Twitter twitter;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

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


        int score = getIntent().getIntExtra("score", 0);
        message = findViewById(R.id.game_over_message);

        message.setText("You scored " + score + "! Thanks for playing!");
    }

    public void shareButtonPressed(View view) {
        AsyncTask.execute(() -> {
            try {
                twitter.updateStatus(String.valueOf(message));
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        });
    }

    public void mainMenuButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}