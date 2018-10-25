package com.example.jor.memory;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SCAN_QR_CODE_REQUEST_CODE = 0;

    private String $LoesungWort1= "";
    private String $LoesungWort2= "";
    private String $LoesungWort3= "";
    private String $LoesungWort4= "";
    private String $LoesungWort5= "";
    private String $LoesungWort6= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialiseButtons();

    }

    private void InitialiseButtons() {
        Button btnTakePictureOne;
        btnTakePictureOne = findViewById(R.id.btnTakePictureSix);

        Button btnTakePictureTwo;
        btnTakePictureTwo = findViewById(R.id.btnTakePictureSix);

        Button btnTakePictureThree;
        btnTakePictureThree = findViewById(R.id.btnTakePictureSix);

        Button btnTakePictureFour;
        btnTakePictureFour = findViewById(R.id.btnTakePictureSix);

        Button btnTakePictureFive;
        btnTakePictureFive = findViewById(R.id.btnTakePictureSix);

        Button btnTakePictureSix;
        btnTakePictureSix = findViewById(R.id.btnTakePictureSix);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SCAN_QR_CODE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String logMsg = intent.getStringExtra("SCAN_RESULT");
                log(logMsg);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("Log");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, SCAN_QR_CODE_REQUEST_CODE);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void log(String qrCode) {
        Intent intent = new Intent("ch.appquest.intent.LOG");

        if (getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isEmpty()) {
            Toast.makeText(this, "Logbook App not Installed", Toast.LENGTH_LONG).show();
            return;
        }


        // Achtung, je nach App wird etwas anderes eingetragen
        String logmessage = "{\n" +
                "  \"task\": \"Memory\",\n" +
                "  \"solution\": [["+ $LoesungWort1 + ","+ $LoesungWort2 + "], ["+ $LoesungWort3 + ","+ $LoesungWort4 + "], ["+ $LoesungWort5 + ","+ $LoesungWort6 + "], ]\n" +"}";
        intent.putExtra("ch.appquest.logmessage", logmessage);

        startActivity(intent);
    }






}
