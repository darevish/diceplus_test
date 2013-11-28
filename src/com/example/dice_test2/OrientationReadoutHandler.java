package com.example.dice_test2;

import android.util.Log;
import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.OrientationData;

public class OrientationReadoutHandler extends DiceEventHandler {

	public static void onOrientationReadout(Die die, OrientationData readout, Exception exception, MainActivity _mainActivity) {
		final MainActivity mainActivity = _mainActivity;

		final long timestamp = readout.timestamp;
    	final int roll = readout.roll;
    	final int pitch = readout.pitch;
    	final int yaw = readout.yaw;

        Log.d(TAG, "orientationstuff");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.orientationData.setText("Orientation: ts: " + timestamp + ", roll: " + roll + ", pitch: " + pitch + ", yaw: " + yaw);
            }
        });
	}
}
