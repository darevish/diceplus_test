package com.example.dice_test2;

import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.responsedata.RollData;
import android.util.Log;

public class RollHandler extends DiceEventHandler {

	public static void onRoll(Die die, RollData rollData, Exception e, MainActivity _mainActivity) {
        final int face = rollData.face;
        final MainActivity mainActivity = _mainActivity;

        Log.d(TAG, "Roll: " + rollData.face);
        
        mainActivity.runOnUiThread(new Runnable() {
        	
            @Override
            public void run() {
            	mainActivity.rollFace.setText("Roll face: "+face);
            }
        });
	}
}
