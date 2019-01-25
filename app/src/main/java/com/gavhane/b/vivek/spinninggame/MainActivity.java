package com.gavhane.b.vivek.spinninggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tv_player1, tv_player2;

    Random r;

    int oldDegree = 0, curDegree = 0;
    int player1Points = 0, player2Points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        tv_player1 = (TextView) findViewById(R.id.tv_player1);
        tv_player2 = (TextView) findViewById(R.id.tv_player2);

        r = new Random();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldDegree = curDegree % 360;            //last arrow position
                curDegree = r.nextInt(3600) + 360;  //new arrow position - 10 rotates max, 1 minimum  it means arrow can randomly rotates upto 10 times of 360 degree i.e. 3600 degree as well due to random number generation

                //rotate animation
                RotateAnimation animation = new RotateAnimation(oldDegree, curDegree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);                       //If fillAfter is true, the transformation that this animation performed will persist when it is finished
                animation.setDuration(3600);        // duration of animation
                animation.setInterpolator(new AccelerateDecelerateInterpolator());      // i think for variable acceleration and deacceleration purpose
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                            //display results on Animation End
                        curDegree = curDegree % 360;
                        points(curDegree);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView.startAnimation(animation);            // to start the animation after clicking the image
            }
        });
    }

    private void points(int degree){        // this degree will take value of curDegree   from   points(curDegree)  i.e.  line 58

        if(degree > 270 || degree < 90){                    // it means 0 degree is at vertically top
            player1Points++;
            tv_player1.setText("PLAYER 1 score is : " + player1Points);
            Toast.makeText(this, "Player 1 Wins" , Toast.LENGTH_SHORT).show();
        } else if (degree < 270  && degree > 90){
            player2Points++;
            tv_player2.setText("PLAYER 2 score is: " + player2Points);
            Toast.makeText(this, "Player 2 Wins" , Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Draw" , Toast.LENGTH_SHORT).show();
        }
    }
}
