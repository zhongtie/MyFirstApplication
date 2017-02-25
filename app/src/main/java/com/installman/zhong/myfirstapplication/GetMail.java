package com.installman.zhong.myfirstapplication;

import android.util.Log;

/**
 * Created by zhong on 17-2-25.
 */

public class GetMail implements Runnable{
    private String mTitle;
    private String TAG = "GetMail";

    public GetMail() {
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public void run() {
        MailUtils getter = new MailUtils();
        try {
            //
            mTitle = getter.getTitle();
            Log.v(TAG, mTitle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
