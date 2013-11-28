package com.example.dice_test2;

import android.util.Log;
import us.dicepl.android.sdk.BluetoothManipulator;
import us.dicepl.android.sdk.DiceConnectionListener;
import us.dicepl.android.sdk.DiceController;
import us.dicepl.android.sdk.DiceResponseAdapter;
import us.dicepl.android.sdk.DiceResponseListener;
import us.dicepl.android.sdk.DiceScanningListener;
import us.dicepl.android.sdk.Die;
import us.dicepl.android.sdk.protocol.constants.Constants.BatteryState;
import us.dicepl.android.sdk.protocol.constants.Constants.MagnetometerReadoutType;
import us.dicepl.android.sdk.responsedata.LedStateData;
import us.dicepl.android.sdk.responsedata.OrientationData;
import us.dicepl.android.sdk.responsedata.PowerModeData;
import us.dicepl.android.sdk.responsedata.ProximityData;
import us.dicepl.android.sdk.responsedata.RollData;
import us.dicepl.android.sdk.responsedata.TapData;
import us.dicepl.android.sdk.responsedata.TemperatureData;
import us.dicepl.android.sdk.responsedata.TouchData;
import us.dicepl.android.sdk.responsedata.AccelerometerData;
import us.dicepl.android.sdk.protocol.constants.Constants.DataSource;
import us.dicepl.android.sdk.responsedata.MagnetometerData;
import us.dicepl.android.sdk.protocol.payload.storage.record.Vector3;

public class ListenerContainer {
    public DiceResponseListener responseListener;
    public DiceConnectionListener connectionListener;
    public DiceScanningListener scanningListener;
    
    private MainActivity mainActivity;
    
    private String TAG = "ListenerContainer";

    public ListenerContainer(MainActivity _mainActivity) {
    	mainActivity = _mainActivity;
    	initListeners();
    }
    
    public void initListeners() {
        initScanningListener();
    	initConnectionListener();
    	initResponseListener();
	}

    public void initScanningListener() {
    	Log.d(TAG, "initScanningListener");
    	
        scanningListener = new DiceScanningListener() {
            @Override
            public void onNewDie(Die die) {
            	Log.d(TAG, "onNewDie");
            	mainActivity.dicePlus = die;
                DiceController.connect(mainActivity.dicePlus);
            }

            @Override
            public void onScanStarted() {
            	Log.d(TAG, "onScanStarted");
            }

            @Override
            public void onScanFailed() {
            	Log.d(TAG, "onScanFailed");
                BluetoothManipulator.startScan();
            }

            @Override
            public void onScanFinished() {
            	Log.d(TAG, "onScanFinished");
                if(mainActivity.dicePlus == null) {
                    BluetoothManipulator.startScan();
                }
            }
        };    	
    }
    
    public void initConnectionListener() {
    	Log.d(TAG, "initConnectionListener");

    	connectionListener = new DiceConnectionListener() {
            @Override
            public void onConnectionEstablished(Die die) {
                Log.d(TAG, "DICE+ Connected");
                

                // Signing up for roll events
                DiceController.subscribeRolls(mainActivity.dicePlus);
                DiceController.subscribeOrientationReadouts(mainActivity.dicePlus, 1);
                DiceController.subscribeAccelerometerReadouts(mainActivity.dicePlus);
                DiceController.subscribeTemperatureReadouts(mainActivity.dicePlus);
                DiceController.subscribeBatteryState(mainActivity.dicePlus);
                DiceController.subscribeTapReadouts(mainActivity.dicePlus);
                DiceController.subscribeProximityReadouts(mainActivity.dicePlus, 1);
                DiceController.subscribeMagnetometerReadouts(mainActivity.dicePlus, 1, MagnetometerReadoutType.MAGNETOMETER_READOUT_RAW);
                DiceController.subscribeTouchReadouts(mainActivity.dicePlus);
                DiceController.subscribeLedState(mainActivity.dicePlus);
                DiceController.subscribePowerMode(mainActivity.dicePlus);
                DiceController.initializePStorageCommunication(mainActivity.dicePlus);
            }

            @Override
            public void onConnectionFailed(Die die, Exception e) {
                Log.d(TAG, "Connection failed", e);

                mainActivity.dicePlus = null;

                BluetoothManipulator.startScan();
            }

            @Override
            public void onConnectionLost(Die die) {
                Log.d(TAG, "Connection lost");

                mainActivity.dicePlus = null;

                BluetoothManipulator.startScan();
            }
        };   	
    }
    
    public void initResponseListener() {
    	Log.d(TAG, "initResponseListener");
    	
        responseListener = new DiceResponseAdapter() {
            @Override
            public void onRoll(Die die, RollData rollData, Exception e) {
                super.onRoll(die, rollData, e);
                RollHandler.onRoll(die, rollData, e, mainActivity);
            }
            
            @Override
            public void onOrientationReadout(Die die, OrientationData readout, Exception exception) {
            	super.onOrientationReadout(die, readout, exception);
            	OrientationReadoutHandler.onOrientationReadout(die, readout, exception, mainActivity);
            }
            
            @Override
            public void onAccelerometerReadout(Die die, AccelerometerData readout, Exception exception) {
            	super.onAccelerometerReadout(die, readout, exception);
            	AccelerometerReadoutHandler.onAccelerometerReadout(die, readout, exception, mainActivity);
            }
            
            @Override
            public void onTemperatureReadout(Die die, TemperatureData readout, Exception exception) {
            	super.onTemperatureReadout(die, readout, exception);
            	TemperatureReadoutHandler.onTemperatureReadout(die, readout, exception, mainActivity);
            }
  
            @Override
            public void onBatteryState(Die die, BatteryState state, int percentage, boolean low, Exception exception) {
            	super.onBatteryState(die, state, percentage, low, exception);
            	BatteryStateHandler.onBatteryState(die, state, percentage, low, exception, mainActivity);
            }

            @Override
            public void onTapReadout(Die die, TapData readout, Exception exception) {
            	super.onTapReadout(die, readout, exception);
            	TapReadoutHandler.onTapReadout(die, readout, exception, mainActivity);
            }
  
            @Override
            public void onProximityReadout(Die die, ProximityData readout, Exception exception) {
            	super.onProximityReadout(die, readout, exception);
            	ProximityReadoutHandler.onProximityReadout(die, readout, exception, mainActivity);
            }
  
            @Override
            public void onMagnetometerReadout(Die die, MagnetometerData readout, Exception exception) {
            	super.onMagnetometerReadout(die, readout, exception);
            	MagnetometerReadoutHandler.onMagnetometerReadout(die, readout, exception, mainActivity);
            }
  
            @Override
            public void onTouchReadout(Die die, TouchData readout, Exception exception) {
            	super.onTouchReadout(die, readout, exception);
            	TouchReadoutHandler.onTouchReadout(die, readout, exception, mainActivity);
            }
  
            @Override
            public void onLedState(Die die, LedStateData data, Exception exception) {
            	super.onLedState(die, data, exception);
            	LedStateHandler.onLedState(die, data, exception, mainActivity);
            }
  
            @Override
            public void onPowerMode(Die die, PowerModeData readout, Exception exception) {
            	super.onPowerMode(die, readout, exception);
            	PowerModeHandler.onPowerMode(die, readout, exception, mainActivity);
            }
  
            @Override
            public void onSubscriptionChangeStatus(Die die, DataSource dataSourceCode, Exception exception) {
            	super.onSubscriptionChangeStatus(die, dataSourceCode, exception);
            	SubscriptionChangeHandler.onSubscriptionChangeStatus(die, dataSourceCode, exception, mainActivity);
            }
  
            @Override
            public void onPStorageCommunicationInitialized(Die die) {
            	super.onPStorageCommunicationInitialized(die);
            	PStorageHandler.onPStorageCommunicationInitialized(die, mainActivity);
            }
            
            @Override
            public void onPStorageOperationFailed(Die die, Exception exception) {
            	super.onPStorageOperationFailed(die, exception);
            	PStorageHandler.onPStorageOperationFailed(die, exception);
            }
            
            @Override
            public void onPStorageValueWrite(Die die, int handle) {
            	super.onPStorageValueWrite(die, handle);
            	PStorageHandler.onPStorageValueWrite(die, handle);
            }
            
            @Override
            public void onPStorageReset(Die die, Exception exception) {
            	super.onPStorageReset(die, exception);
            	PStorageHandler.onPStorageReset(die, exception);
            }
            
            @Override
            public void onPStorageValueRead(Die die, int handle, String str) {
            	super.onPStorageValueRead(die, handle, str);
            	PStorageHandler.onPStorageValueRead(die, handle, str);
            }
            
            @Override
            public void onPStorageValueRead(Die die, int handle, Vector3 vector) {
            	super.onPStorageValueRead(die, handle, vector);
            	PStorageHandler.onPStorageValueRead(die, handle, vector);
            }
            
            @Override
            public void onPStorageValueRead(Die die, int handle, int value) {
            	super.onPStorageValueRead(die, handle, value);
            	PStorageHandler.onPStorageValueRead(die, handle, value);
            }
            
        };
    }
}
