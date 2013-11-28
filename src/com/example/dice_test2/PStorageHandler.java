package com.example.dice_test2;

import android.util.Log;
import us.dicepl.android.sdk.DiceController;
import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.protocol.payload.storage.record.Vector3;

public class PStorageHandler extends DiceEventHandler {

	public static void onPStorageCommunicationInitialized(Die die, MainActivity _mainActivity) {
		Log.d(TAG, "PStorage initialize");

		DiceController.writePStorageValue(die, 0, "my dice");
    }
	
	public static void onPStorageOperationFailed(Die die, Exception exception) {
		Log.d(TAG, "Pstorage failure: " + exception.getMessage());
	}

	public static void onPStorageValueWrite(Die die, int handle) {
		Log.d(TAG, "PStorage write: handle: " + handle);
	}

	public static void onPStorageReset(Die die, Exception exception) {
		Log.d(TAG, "PStorage reset");
	}
	
    public static void onPStorageValueRead(Die die, int handle, String str) {
		Log.d(TAG, "PStorage read: handle: " + handle + ", str value: " + str);
    }
    
    public static void onPStorageValueRead(Die die, int handle, Vector3 vector) {
		Log.d(TAG, "PStorage read: handle: " + handle + ", vector value: " + vector.toString());
    }
    
    public static void onPStorageValueRead(Die die, int handle, int value) {
		Log.d(TAG, "PStorage read: handle: " + handle + ", int value: " + value);
    }
    
}
