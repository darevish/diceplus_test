package com.example.dice_test2;

import android.util.Log;
import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.AccelerometerData;

public class AccelerometerReadoutHandler extends DiceEventHandler {

	public static void onAccelerometerReadout(Die die, AccelerometerData readout, Exception exception, MainActivity _mainActivity) {
		final MainActivity mainActivity = _mainActivity;

    	final long timestamp = readout.timestamp;
    	final int x = readout.x;
    	final int y = readout.y;
    	final int z = readout.z;

        Log.d(TAG, "accelerometerstuff");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.accelerometerData.setText("Accelerometer: ts: " + timestamp + ", x: " + x + ", y: " + y + ", z: " + z);
            }
        });
	}
}
