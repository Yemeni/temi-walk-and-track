package com.yousefh.temi_walk_and_track;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.robotemi.sdk.Robot;



import com.robotemi.sdk.listeners.OnRobotReadyListener;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnRobotReadyListener {

   /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    */

    Robot mRobot;

    boolean isFollowing = false;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize robot instance
        mRobot = Robot.getInstance();

    }

    @Override

    protected void onStart() {
        super.onStart();
        // Add robot event listeners
        mRobot.addOnRobotReadyListener(this);
    }


    @Override

    protected void onStop() {
        super.onStop();
        // Remove robot event listeners
        mRobot.removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            mRobot.hideTopBar(); // hide temi's top action bar when skill is active
        }
    }





    private Timer timer;

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Perform timer task here
                Log.d("MyApp", "timer works");
            }
        }, 0, 1000); // Change 1000 to the desired interval in milliseconds (1 second in this example)
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void BeWithMeButton(View view) {

        if(!isFollowing){
            mRobot.beWithMe();
            startTimer();
            isFollowing = true;
            Log.d("MyApp", "I follow you");
        }else {
            mRobot.stopMovement();
            stopTimer();
            isFollowing = false;
            Log.d("MyApp", "stopping with you");
        }
    }
    //this for testing push
}