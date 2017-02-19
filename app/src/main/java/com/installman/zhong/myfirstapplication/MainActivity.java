package com.installman.zhong.myfirstapplication;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button startService;
    private Button stopService;
    private TextView gpsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        gpsText = (TextView)findViewById(R.id.textView);

        //启动定位服务
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);

        //显示是否启动了服务
        boolean serviceRunning = ServiceUtils.isServiceRunning(this, "com.installman.zhong.myfirstapplication.MyService");
        if(serviceRunning){
            gpsText.setText("service running");
        }else{

            gpsText.setText("service not running");
        }
    }

    @Override
    public void onClick(View v) {
        boolean serviceRunning = ServiceUtils.isServiceRunning(this, "com.installman.zhong.myfirstapplication.MyService");
        switch (v.getId()) {
            case R.id.start_service:
                if(true == serviceRunning){
                    gpsText.setText("service already running");
                }else{
                    Intent startIntent = new Intent(this, MyService.class);
                    startService(startIntent);
                    gpsText.setText("service started");
                }
                break;
            case R.id.stop_service:
                if(false == serviceRunning) {
                    gpsText.setText("service did not running");
                }else{
                    Intent stopIntent = new Intent(this, MyService.class);
                    stopService(stopIntent);
                    gpsText.setText("service stopped");
                }
                break;
            default:
                break;
        }
    }
}
