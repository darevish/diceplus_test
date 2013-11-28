package com.example.dice_test2;

import android.util.Log;
import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.protocol.constants.Constants.DataSource;

public class SubscriptionChangeHandler extends DiceEventHandler {

	private static String mapDataSource(DataSource dataSourceCode) {
		
		String result = "";
		
		switch (dataSourceCode) {
			case DATA_SOURCE_ACCELEROMETER:
				result = "Accelerometer";
				break;
			case DATA_SOURCE_BATTERY_LEVEL:
				result = "Battery level";
				break;
			case DATA_SOURCE_CAPACITIVE:
				result = "Capacitive";
				break;
			case DATA_SOURCE_FACE:
				result = "Face";
				break;
			case DATA_SOURCE_LED_STATE:
				result = "Led state";
				break;
			case DATA_SOURCE_MAGNETOMETER:
				result = "Magnetometer";
				break;
			case DATA_SOURCE_ORIENTATION:
				result = "Orientation";
				break;
			case DATA_SOURCE_POWER_MODE:
				result = "Power mode";
				break;
			case DATA_SOURCE_ROLL:
				result = "Roll";
				break;
			case DATA_SOURCE_RSSI:
				result = "RSSI";
				break;
			case DATA_SOURCE_STATISTICS:
				result = "Statistics";
				break;
			case DATA_SOURCE_TAP:
				result = "Tap";
				break;
			case DATA_SOURCE_TEMPERATURE:
				result = "Temperature";
				break;
			case DATA_SOURCE_TOUCH:
				result = "Touch";
				break;
		}
		
		return result;
	}
	
	public static void onSubscriptionChangeStatus(Die die, DataSource dataSourceCode, Exception exception, MainActivity _mainActivity) {
		final MainActivity mainActivity = _mainActivity;
		
		final String source = mapDataSource(dataSourceCode);
		
        Log.d(TAG, "subscription change");
        
        mainActivity.runOnUiThread(new Runnable() {
        	            		
            @Override
            public void run() {
            	mainActivity.subscriptionChange.setText(mainActivity.subscriptionChange.getText().toString() + source + ", ");
            }
        });
	}
}
