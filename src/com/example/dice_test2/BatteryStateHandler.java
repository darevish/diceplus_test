package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.protocol.constants.Constants.BatteryState;
import android.util.Log;

public class BatteryStateHandler extends DiceEventHandler {
    public static void onBatteryState(Die die, BatteryState state, int percentage, boolean low, Exception exception, MainActivity _mainActivity) {
    	final MainActivity mainActivity = _mainActivity;
    	
    	final String status;
    	if (state == BatteryState.BATTERY_STATE_CHARGING) {
    		status = "Charging";
    	} else if (state == BatteryState.BATTERY_STATE_DISCHARGING) {
    		status = "Discharging";
    	} else {
    		status = "Unknown";
    	}
    	
    	final int _percentage = percentage;
    	final String isLow = low ? "Low" : "Not low";

    	Log.d(TAG, "battery state readout");

    	mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.batteryState.setText("Battery: status: " + status + ", %: " + _percentage + ", Is low?: " + isLow);
            }
        });
    }
}
