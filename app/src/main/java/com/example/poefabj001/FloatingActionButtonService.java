package com.example.poefabj001;

////import android.app.Service;
////import android.content.Intent;
import android.os.IBinder;
// FloatingActionButtonService.java
////package com.example.floatingactionbuttonservice;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class FloatingActionButtonService extends Service {

    private WindowManager windowManager;
    private View floatingButton;
    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        addFloatingButton();
    }

    private void addFloatingButton() {
        floatingButton = LayoutInflater.from(this).inflate(R.layout.layout_floating_button, null);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 100;

        windowManager.addView(floatingButton, params);

        floatingButton.setOnTouchListener(new View.OnTouchListener() {
            int initialX;
            int initialY;
            float initialTouchX;
            float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(floatingButton, params);
                        return true;
                }
                return false;
            }
        });

        floatingButton.setOnClickListener(v -> {
            handler.postDelayed(() -> {
                Toast.makeText(getApplicationContext(), "FAB Clicked!", Toast.LENGTH_SHORT).show();
                Log.d("FABService", "FAB Clicked!");
            }, 5000);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingButton != null) windowManager.removeView(floatingButton);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}