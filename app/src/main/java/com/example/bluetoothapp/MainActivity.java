package com.example.bluetoothapp;

import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    Button btnTurnOn, btnTurnOff;
    private BluetoothAdapter bluetoothAdapter;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTurnOn = (Button) findViewById(R.id.btnTurnOn);
        btnTurnOff = (Button) findViewById(R.id.btnTurnOff);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivityForResult(turnOn, 0);
                    tvMessage.setText("Turned on");
                } else {
                    tvMessage.setText("Already on");
                }
            }
        });
        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    bluetoothAdapter.disable();
                    tvMessage.setText("Turned off");
                }

            }
        });
    }
}

