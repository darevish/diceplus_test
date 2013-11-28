package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.TouchData;
import android.util.Log;

public class TouchReadoutHandler extends DiceEventHandler {

    public static void onTouchReadout(Die die, TouchData readout, Exception exception, MainActivity _mainActivity) {
    	final MainActivity mainActivity = _mainActivity;

    	final int currentStateMask = readout.current_state_mask;
    	final int changeMask = readout.change_mask;
    	final long ts = readout.timestamp;

        Log.d(TAG, "touch readout");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.touchData.setText("Touch: ts: " + ts + ", State mask: " + Integer.toBinaryString(currentStateMask) + ", Change mask: " + Integer.toBinaryString(changeMask));
            }
        });
    }

}
