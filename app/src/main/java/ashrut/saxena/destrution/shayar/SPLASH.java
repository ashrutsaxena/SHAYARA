package ashrut.saxena.destrution.shayar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SPLASH extends AppCompatActivity {
    //  SPLASH SCREEN TIME
    private static int SPLASH_SCREEN = 5000;

    //VARIABLES
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //ANIMATIONS
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //ATTACH
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.txtappname);
        slogan = findViewById(R.id.txtwrite);

        //ANIM
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        //NEXT ACTIVITY
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SPLASH.this, Register.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
