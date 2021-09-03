package app.example.ongakutaskapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private int playTime;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.playMusicButton);
        Button debugButton = (Button)findViewById(R.id.debugButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.game_maoudamashii_1_battle36);
        debugButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Spinner spinner = (Spinner) findViewById(R.id.timeSpinner);
                mediaPlayer.setLooping(true);
                int index = spinner.getSelectedItemPosition();
                switch(index){
                    case 0:
                        playTime = 60000;
                        break;
                    case 1:
                        playTime = 1800 * 1000;
                        break;
                    case 2:
                        playTime = 3600 * 1000;
                        break;
                }
                mediaPlayer.start();
                countDown();
            }
        });
    }

    private void countDown(){
        new CountDownTimer(playTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("", "CountDown: " + playTime);
                playTime--;
            }

            @Override
            public void onFinish() {
                mediaPlayer.stop();
            }
        }.start();
    }
}