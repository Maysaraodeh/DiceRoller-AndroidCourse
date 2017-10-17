package com.masotech.diceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> mDice1;
    private ArrayList<Integer> mDice2;
    private int mNumber1;
    private int mNumber2;
    private Button mRoll;
    private ImageView mDice1Image;
    private ImageView mDice2Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDice1Image = (ImageView) findViewById(R.id.dice1_image);
        mDice2Image = (ImageView) findViewById(R.id.dice2_image);
        mRoll = (Button) findViewById(R.id.roll_btn);

        //add drawable images to the Arraylist 1.
        fillDice1Array();

        //add drawable images to the Arraylist 1.
        fillDice2Array();

        //check for savedInstanceState when rotation.
        if(savedInstanceState !=null){
            mDice1Image.setImageResource(mDice1.get(savedInstanceState.getInt("dice1ImageId")));
            mDice2Image.setImageResource(mDice2.get(savedInstanceState.getInt("dice2ImageId")));
        }

        //this is a listener called when the button clicked
        mRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll();
            }
        });

    }


    public void roll(){
        // add some rotation animation to the images
        RotateAnimation anim = new RotateAnimation(0, 360, 180, 180);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(200);
        mDice1Image.startAnimation(anim);
        mDice2Image.startAnimation(anim);

        // generate random numbers and apply them to the images.
        Random r = new Random();

        mNumber1 = r.nextInt(6);
        mNumber2 = r.nextInt(6);
        setDiceResourses(mNumber1 , mNumber2);
    }


    public void setDiceResourses(int number1 , int number2){

        //set the image resources from the Arraylists.
        mDice1Image.setImageResource(mDice1.get(number1));
        mDice2Image.setImageResource(mDice2.get(number2));
    }


    public void fillDice1Array(){

        mDice1 = new ArrayList<>();
        mDice1.add(R.drawable.dice1_1);
        mDice1.add(R.drawable.dice1_2);
        mDice1.add(R.drawable.dice1_3);
        mDice1.add(R.drawable.dice1_4);
        mDice1.add(R.drawable.dice1_5);
        mDice1.add(R.drawable.dice1_6);
    }

    public void fillDice2Array(){
        mDice2 = new ArrayList<>();
        mDice2.add(R.drawable.dice2_1);
        mDice2.add(R.drawable.dice2_2);
        mDice2.add(R.drawable.dice2_3);
        mDice2.add(R.drawable.dice2_4);
        mDice2.add(R.drawable.dice2_5);
        mDice2.add(R.drawable.dice2_6);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the state when we rotate the device.
        outState.putInt("dice1ImageId" , mNumber1);
        outState.putInt("dice2ImageId" , mNumber2);
    }
}
