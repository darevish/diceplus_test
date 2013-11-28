package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.LedStateData;
import android.util.Log;

public class LedStateHandler extends DiceEventHandler {
	
    public static void onLedState(Die die, LedStateData data, Exception exception, MainActivity _mainActivity) {
    	final MainActivity mainActivity = _mainActivity;

    	final long ts = data.timestamp;
    	final long animationId = data.animation_id;

        Log.d(TAG, "led state");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.ledState.setText("Led state: ts: " + ts + ", Animation Mask: " + Long.toBinaryString(animationId));
            }
        });
    }
}
