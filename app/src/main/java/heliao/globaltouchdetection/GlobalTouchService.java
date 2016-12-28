package heliao.globaltouchdetection;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by HeLiao on 12/27/2016.
 */
public class GlobalTouchService extends Service implements View.OnTouchListener{

    private String TAG = this.getClass().getSimpleName();
    // window manager
    private WindowManager mWindowManager;
    // linear layout will use to detect touch event
    private LinearLayout touchLayout;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
        // create linear layout
        touchLayout = new LinearLayout(this);
        // set layout width 30 px and height is equal to full screen
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, LinearLayout.LayoutParams.MATCH_PARENT);
        touchLayout.setLayoutParams(lp);
        // set color if you want layout visible on screen
          touchLayout.setBackgroundColor(Color.CYAN);
        // set on touch listener
        touchLayout.setOnTouchListener(this);

        // fetch window manager object
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // set layout parameter of window manager
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                1,
                1,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                         |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH ,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT | Gravity.TOP;
        Log.i(TAG, "add View");

        mWindowManager.addView(touchLayout, params);

    }


    @Override
    public void onDestroy() {
        if(mWindowManager != null) {
            if(touchLayout != null) mWindowManager.removeView(touchLayout);
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_OUTSIDE
//                || event.getActionMasked() == MotionEvent.ACTION_DOWN
//                || event.getActionMasked() == MotionEvent.ACTION_UP
//                || event.getActionMasked() == MotionEvent.ACTION_OUTSIDE){
//            Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :"+ event.getRawY());
//
//        }
        Log.i(TAG, "Action :" + event.getAction() + "\t X :" + event.getRawX() + "\t Y :"+ event.getRawY());

        return true;
    }



}
