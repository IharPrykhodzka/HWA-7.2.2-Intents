package com.example.hwa722intents;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 11;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    EditText editTextNumberPhone = findViewById(R.id.editPhone);
                    String number = editTextNumberPhone.getText().toString();
                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));

                    startActivity(dialIntent);
                }

            }
        });

        Button btnSendSMS = findViewById(R.id.btnSendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                } else {

                    EditText editTextNumberPhone = findViewById(R.id.editPhone);
                    EditText editTextSMS = findViewById(R.id.editSMS);

                    String sms = editTextSMS.getText().toString().trim();
                    String number = editTextNumberPhone.getText().toString().trim();


                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(number, null, sms, null, null);

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                }
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                }
        }
    }

}
