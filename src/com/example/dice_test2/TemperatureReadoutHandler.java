package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.TemperatureData;
import android.util.Log;

public class TemperatureReadoutHandler extends DiceEventHandler {

	public static void onTemperatureReadout(Die die, TemperatureData readout, Exception exception, MainActivity _maiActivity) {
		final MainActivity mainActivity = _maiActivity;
		
    	final float temperature = readout.temperature;

        Log.d(TAG, "temperature readout");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.temperatureData.setText("Temperature: " + temperature);
            }
        });
    }
}
