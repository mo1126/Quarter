package com.mo.quarter.utils;

/**
 * Created by 莫迎华 on 2017/11/14.14:39.
 */
import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
public class DemoIntentService extends GTIntentService {

    @Override
    public void onReceiveServicePid(Context context, int i) {

    }

    @Override
    public void onReceiveClientId(Context context, String s) {
        System.out.println("onReceiveClientId--------------"+s);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
        String messageId = gtTransmitMessage.getMessageId();
        String taskId = gtTransmitMessage.getTaskId();
        System.out.println("onReceiveMessageData--------------"+messageId+taskId);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {
        System.out.println("onReceiveOnlineState--------------"+b);
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {
        System.out.println("onReceiveCommandResult--------------");
    }
}
