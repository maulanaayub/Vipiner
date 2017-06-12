package vision.google.com.vipiner;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Asus on 5/29/2017.
 */

public class MainActivity extends Activity implements ZXingScannerView.ResultHandler {

    String var_link;
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick (View v){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        //turn on flash dg kode ini
        mScannerView.setFlash(!mScannerView.getFlash());
        //turn of flash dg kode ini
        //mScannerView.setFlash(false);


    }

    @Override
    protected void onPause (){
        super.onPause();
        mScannerView.stopCamera();
        mScannerView.setFlash(false);
    }



    @Override
    public void onBackPressed(){

        if (mScannerView.hasWindowFocus()){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else{
           getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           finish();

        }

    }

    @Override
    public void handleResult(Result result) {

        Log.v("handleresult", result.getText());
        var_link = result.getText();
        Intent i = null;
        i = new Intent(MainActivity.this , VideoActivity.class);
        Bundle b = new Bundle();
        b.putString("parse_link", var_link);
        i.putExtras(b);
        startActivity(i);
    }


}

