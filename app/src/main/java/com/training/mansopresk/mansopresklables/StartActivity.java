package com.training.mansopresk.mansopresklables;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity implements View.OnClickListener {
    Button lablebtn, urlbtn, emailbtn, callbtn;
    TextView mailtv, calltv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        lablebtn = findViewById(R.id.lablebtn);
        urlbtn = findViewById(R.id.urlbtn);
        emailbtn = findViewById(R.id.mailbtn);
        callbtn = findViewById(R.id.callbtn);
        mailtv = findViewById(R.id.mailtv);
        calltv = findViewById(R.id.calltv);

        lablebtn.setOnClickListener(this);
        urlbtn.setOnClickListener(this);
        emailbtn.setOnClickListener(this);
        callbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lablebtn:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.urlbtn:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.javatpoint.com/android-tutorial"));
                startActivity(i);
                break;
            case R.id.mailbtn:
                String mailid = mailtv.getText().toString();
                Intent emailintent = new Intent(Intent.ACTION_SEND);
                emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailid});
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Test Mail");
                emailintent.putExtra(Intent.EXTRA_TEXT, "Hello Hw R u?");
                emailintent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailintent, "Choose an Email: "));
                break;
            case R.id.callbtn:
                if (isPermissionGranted()) {
                    String callno = calltv.getText().toString();
                    Intent callintent = new Intent(Intent.ACTION_CALL);//Intent callintent=new Intent(Intent.DAIL);
                    callintent.setData(Uri.parse("tel:" + callno));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callintent);
             }



     }

    }
    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }
}
