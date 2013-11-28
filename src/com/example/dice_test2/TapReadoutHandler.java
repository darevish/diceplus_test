package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.TapData;
import android.util.Log;

public class TapReadoutHandler extends DiceEventHandler {

	public static final String USER_AGENT = "useragent_javaclient_dice_lofasz";
	
	public static void onTapReadout(Die die, TapData readout, Exception exception, MainActivity _mainActivity) {
		final MainActivity mainActivity = _mainActivity;

    	final long ts = readout.timestamp;
    	final int x = readout.x;
    	final int y = readout.y;
    	final int z = readout.z;

        Log.d(TAG, "tap readout");
        
        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.tapData.setText("Tap: ts: " + ts + ", x: " + x + ", y: " + y + ", z: " + z);
            }
        });
    }

}
