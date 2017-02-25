package com.installman.zhong.myfirstapplication;

/**
 * Created by zhong on 17-2-20.
 */

public class NewMail implements Runnable {
    private String mTitle,mMsg;//邮件的标题和内容
    public NewMail(String title, String msg) {
        mTitle=title;
        mMsg=msg;
    }

    @Override
    public void run() {
        MailUtils sender = new MailUtils(mTitle, mMsg);
        try {
            sender.sendMail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
