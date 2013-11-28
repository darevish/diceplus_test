package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.protocol.constants.Constants.PowerMode;
import us.dicepl.android.sdk.responsedata.PowerModeData;
import android.util.Log;

public class PowerModeHandler extends DiceEventHandler {
	
    public static void onPowerMode(Die die, PowerModeData readout, Exception exception, MainActivity _mainActivity) {
    	final MainActivity mainActivity = _mainActivity;
    	
    	final long ts = readout.timestamp;
    	final String _powerMode;
    	
    	if (readout.mode == PowerMode.POWER_MODE_NORMAL) {
    		_powerMode = "Normal";
    	} else if (readout.mode == PowerMode.POWER_MODE_SHUTDOWN) {
    		_powerMode = "Shutdown";
    	} else if (readout.mode == PowerMode.POWER_MODE_SLEEP) {
    		_powerMode = "Sleep";
    	} else {
    		_powerMode = "Unknown";
    	}

        Log.d(TAG, "power mode");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.powerMode.setText("Power mode: ts: " + ts + ", Power mode: " + _powerMode);
            }
        });
    }
}
