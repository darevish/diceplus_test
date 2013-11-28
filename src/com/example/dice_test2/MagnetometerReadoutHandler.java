package com.example.dice_test2;

import android.util.Log;
import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.MagnetometerData;

public class MagnetometerReadoutHandler extends DiceEventHandler {

	public static void onMagnetometerReadout(Die die, MagnetometerData readout, Exception exception, MainActivity _mainActivity) {
		final MainActivity mainActivity = _mainActivity;

    	final long timestamp = readout.timestamp;
    	final int x = readout.x;
    	final int y = readout.y;
    	final int z = readout.z;

        Log.d(TAG, "magnetometerstuff");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.magnetometerData.setText("Magnetometer: ts: " + timestamp + ", x: " + x + ", y: " + y + ", z: " + z);
            }
        });
	}
}
