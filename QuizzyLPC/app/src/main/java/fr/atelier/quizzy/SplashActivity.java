package fr.atelier.quizzy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    private final int SPASH_SCREEN_TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);
/*  en multi thread
        // redigige vers la page principale "MainActivity" aprés 3 seconde
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPASH_SCREEN_TIMEOUT);
*/

        ImageView img = findViewById(R.id.logo_id);
        img.setBackgroundResource(R.drawable.animation_quizzy);
        AnimationDrawable anim = (AnimationDrawable) img.getBackground();
        anim.start();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();


    }
}