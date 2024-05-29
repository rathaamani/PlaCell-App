package com.example.placell;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        EditText doubtText=(EditText) findViewById(R.id.doubttext);
        Button sendmail = (Button) findViewById(R.id.sendemail);
        sendmail.setOnClickListener(new View.OnClickListener() {
            EditText emailtext=(EditText) findViewById(R.id.emailtext);
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailtext.getText().toString()});
                email.putExtra(Intent.EXTRA_SUBJECT, "Doubt");
                email.putExtra(Intent.EXTRA_TEXT, doubtText.getText().toString());

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        Button sendsms = (Button) findViewById(R.id.sendsms);
        sendsms.setOnClickListener(new View.OnClickListener() {
            EditText phno=(EditText) findViewById(R.id.phnotext);
            public void onClick(View v) {
                String no=phno.getText().toString();
                String msg=doubtText.getText().toString();

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}