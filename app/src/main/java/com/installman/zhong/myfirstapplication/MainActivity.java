package com.installman.zhong.myfirstapplication;

import android.content.Intent;
import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button startService;
    private Button stopService;
    private TextView gpsText;
    private NewMail newMail;
    private String TAG = "MainActivity";

    //thread test
    public Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:
                    gpsText.setText("thread test:" + msg.arg1);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

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
        Intent startIntent = new Intent(this, GetLbsService.class);
        startService(startIntent);

        //显示是否启动了服务
        boolean serviceRunning = ServiceUtils.isServiceRunning(this, "com.installman.zhong.myfirstapplication.GetLbsService");
        if(serviceRunning){
            gpsText.setText("service running");
        }else{

            gpsText.setText("service not running");
        }
    }

    private void sendMail(String title, String msg){
        newMail = new NewMail(title, msg);
        newMail.run();

        Thread thread=new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int j = 0;
                while(true) {
                    Log.e(TAG, "thread test in run()");
                    // TODO Auto-generated method stub
                    Message message = new Message();
                    message.what = 1;
                    message.arg1 = j;
                    mHandler.sendMessage(message);
                    j ++;
                    try{
                        Thread.sleep(10*1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View v) {
        boolean serviceRunning = ServiceUtils.isServiceRunning(this, "com.installman.zhong.myfirstapplication.GetLbsService");
        switch (v.getId()) {
            case R.id.start_service:

                //sendMail test
                sendMail("hello","hello,world.");
/*
                if(serviceRunning){
                    gpsText.setText("service already running");
                }else{
                    Intent startIntent = new Intent(this, GetLbsService.class);
                    startService(startIntent);
                    gpsText.setText("service started");
                }
*/
                break;
            case R.id.stop_service:
                if(! serviceRunning) {
                    gpsText.setText("service did not running");
                }else{
                    Intent stopIntent = new Intent(this, GetLbsService.class);
                    stopService(stopIntent);
                    gpsText.setText("service stopped");
                }
                break;
            default:
                break;
        }
    }
}
