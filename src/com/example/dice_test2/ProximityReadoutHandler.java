package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.ProximityData;
import android.util.Log;

public class ProximityReadoutHandler extends DiceEventHandler {
	
    public static void onProximityReadout(Die die, ProximityData readout, Exception exception, MainActivity _mainActivity) {

    	final MainActivity mainActivity = _mainActivity;
    	
    	String _readouts = "";
    	
    	for (int i = 0; i < readout.readouts.length; i++) {
    		if (i > 0) {
    			_readouts = _readouts + ", ";
    		}
    		_readouts = _readouts + readout.readouts[i];
    	}
    	
    	final String readouts = _readouts;
    	final long ts = readout.timestamp;
    	
        Log.d(TAG, "proximity readout");

        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.proximityData.setText("Proximity: ts: " + ts + ", readouts: " + readouts);
            }
        });
    }
}
