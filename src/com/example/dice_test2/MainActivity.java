package com.example.dice_test2;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import us.dicepl.android.sdk.*;

public class MainActivity extends Activity {

    public static final int[] developerKey = new int[] {0x83, 0xed, 0x60, 0x0e, 0x5d, 0x31, 0x8f, 0xe7};
    public Die dicePlus;
    
    public ListenerContainer listenerContainer = new ListenerContainer(this);
        
    public static final String TAG = "lofasz";
    
    public TextView rollFace;
    public TextView orientationData;
    public TextView accelerometerData;
    public TextView temperatureData;
    public TextView batteryState;
    public TextView tapData;
    public TextView proximityData;
    public TextView magnetometerData;
    public TextView touchData;
    public TextView ledState;
    public TextView powerMode;
    public TextView subscriptionChange;
    
    public Button blinkAnimation;
    public Button fadeAnimation;
    
    public CheckBox side1Mask;
    public CheckBox side2Mask;
    public CheckBox side3Mask;
    public CheckBox side4Mask;
    public CheckBox side5Mask;
    public CheckBox side6Mask;
    
    public EditText redColor;
    public EditText greenColor;
    public EditText blueColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        rollFace = (TextView) findViewById(R.id.roll_face);
        orientationData = (TextView) findViewById(R.id.orientation_data);
        accelerometerData = (TextView) findViewById(R.id.accelerometer_data);
        temperatureData = (TextView) findViewById(R.id.temperature_data);
        batteryState = (TextView) findViewById(R.id.battery_state);
        tapData = (TextView) findViewById(R.id.tap_data);
        proximityData = (TextView) findViewById(R.id.proximity_data);
        magnetometerData = (TextView) findViewById(R.id.magnetometer_data);
        touchData = (TextView) findViewById(R.id.touch_data);
        ledState = (TextView) findViewById(R.id.led_state);
        powerMode = (TextView) findViewById(R.id.power_mode);
        subscriptionChange = (TextView) findViewById(R.id.subscription_change);
        
        blinkAnimation = (Button) findViewById(R.id.blink_animation);
        fadeAnimation = (Button) findViewById(R.id.fade_animation);
        
        side1Mask = (CheckBox) findViewById(R.id.side_1_mask);
        side2Mask = (CheckBox) findViewById(R.id.side_2_mask);
        side3Mask = (CheckBox) findViewById(R.id.side_3_mask);
        side4Mask = (CheckBox) findViewById(R.id.side_4_mask);
        side5Mask = (CheckBox) findViewById(R.id.side_5_mask);
        side6Mask = (CheckBox) findViewById(R.id.side_6_mask);
        
        redColor = (EditText) findViewById(R.id.red_color);
        greenColor = (EditText) findViewById(R.id.green_color);
        blueColor = (EditText) findViewById(R.id.blue_color);

        addBlinkAnimationClickListener(blinkAnimation);
        addFadeAnimationClickListener(fadeAnimation);
    }


    @Override
    protected void onResume() {
    	super.onResume();

        Log.d(TAG, "onResume");

        // Initiating
        BluetoothManipulator.initiate(this);
        DiceController.initiate(developerKey);

        // Listen to all the state occurring during the discovering process of DICE+
        BluetoothManipulator.registerDiceScanningListener(listenerContainer.scanningListener);

        // When connecting to DICE+ you get two responses: a good one and a bad one ;)
        DiceController.registerDiceConnectionListener(listenerContainer.connectionListener);
        
        // Attaching to DICE+ events that we subscribed to.
        DiceController.registerDiceResponseListener(listenerContainer.responseListener);

        // Scan for a DICE+
        BluetoothManipulator.startScan();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");

        DiceController.unregisterDiceConnectionListener(listenerContainer.connectionListener);
        BluetoothManipulator.unregisterDiceScanningListener(listenerContainer.scanningListener);
        DiceController.unregisterDiceResponseListener(listenerContainer.responseListener);

        DiceController.disconnectDie(dicePlus);
        dicePlus = null;
    }
    
    public int getSideMask() {
    	String result = "";
    	
    	result = ( side1Mask.isChecked() ? "1" : "0" ) + result;
    	result = ( side2Mask.isChecked() ? "1" : "0" ) + result;
    	result = ( side3Mask.isChecked() ? "1" : "0" ) + result;
    	result = ( side4Mask.isChecked() ? "1" : "0" ) + result;
    	result = ( side5Mask.isChecked() ? "1" : "0" ) + result;
    	result = ( side6Mask.isChecked() ? "1" : "0" ) + result;
    	
    	return Integer.parseInt(result, 2);
    }
    
    public int[] getRGB() {
    	int[] rgb = new int[3];
    	
    	String red = ( redColor.getText().toString().length() > 0 ) ? redColor.getText().toString() : "0";
    	String green = ( greenColor.getText().toString().length() > 0 ) ? greenColor.getText().toString() : "0";
    	String blue = ( blueColor.getText().toString().length() > 0 ) ? blueColor.getText().toString() : "0";
    	
    	rgb[0] = Integer.parseInt(red) % 256;
    	rgb[1] = Integer.parseInt(green) % 256;
    	rgb[2] = Integer.parseInt(blue) % 256;
    	
    	return rgb;
    }
    
    public void addBlinkAnimationClickListener(Button blinkAnimation) {
        blinkAnimation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int[] rgb = MainActivity.this.getRGB();				

				DiceController.runBlinkAnimation(dicePlus, MainActivity.this.getSideMask(), 1, rgb[0], rgb[1], rgb[2], 500, 1000, 3);
			}
		});
    }
    
    public void addFadeAnimationClickListener(Button fadeAnimation) {
        fadeAnimation.setOnClickListener(new OnClickListener() {

        	@Override
			public void onClick(View v) {
				int[] rgb = MainActivity.this.getRGB();				

		        DiceController.runFadeAnimation(dicePlus, MainActivity.this.getSideMask(), 1, rgb[0], rgb[1], rgb[2], 3000, 3000);
			}
		});
    }
}

