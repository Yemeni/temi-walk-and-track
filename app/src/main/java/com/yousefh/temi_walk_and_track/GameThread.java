package com.yousefh.temi_walk_and_track;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.listeners.OnRobotReadyListener;
import com.robotemi.sdk.listeners.OnLocationsUpdatedListener;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

public class GameThread extends Thread implements OnRobotReadyListener,
        OnCurrentPositionChangedListener, OnGoToLocationStatusChangedListener {
    private final SurfaceHolder surfaceHolder;
    private boolean running;
    private long targetFPS = 30; // Target frames per second
    private Canvas canvas;
    private Robot robot;

    // Constructor
    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        robot = Robot.getInstance();
        robot.addOnRobotReadyListener(this);
    }

    // Set the running state of the game loop
    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long targetTime = 1000 / targetFPS; // Target time per frame

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // Update game state and render the graphics
                    update();
                    draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // Calculate wait time to achieve the target FPS
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // Update game state (e.g., move game objects, handle input)
    }

    private void draw(Canvas canvas) {
        // Render the graphics on the canvas
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            // Register listeners and perform any required setup
            robot.addOnCurrentPositionChangedListener(this);
            robot.addOnGoToLocationStatusChangedListener(this);
            // Additional setup and initialization if needed
        }
    }

    @Override
    public void onCurrentPositionChanged(Position currentPosition) {
        // Handle current position updates
    }

    @Override
    public void onGoToLocationStatusChanged(String location, String status, int descriptionId, String description) {
        // Handle GoTo location status changes
    }
}
