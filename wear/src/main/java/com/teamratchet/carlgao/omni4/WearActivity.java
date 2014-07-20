package com.teamratchet.carlgao.omni4;

import android.app.Activity;
import android.app.AlertDialog;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Arrays;


public class WearActivity extends Activity {
    private final String DEBUG_TAG = "CARL";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(DEBUG_TAG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wear);
    }

    public void onSensorChanged(SensorEvent event){
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        float alpha = (float)0.8;

        float[] gravity = new float[3];

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        float[] linear_acceleration = new float[3];
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];
        Log.d(DEBUG_TAG, "Action was made");
        Log.d(DEBUG_TAG, Arrays.toString(linear_acceleration));

        if (linear_acceleration[0] > .5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(WearActivity.this);
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("message")
                    .setTitle("title");

            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
        }
    }

}
