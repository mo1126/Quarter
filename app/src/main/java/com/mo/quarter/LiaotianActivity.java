package com.mo.quarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mo.quarter.adapter.LiaotianAdapter;
import com.mo.quarter.bean.LiaotianBean;

import java.util.ArrayList;
import java.util.List;

public class LiaotianActivity extends AppCompatActivity {

    private XRecyclerView rv;
    private EditText msg;
    private String mobile;
    private String name;
    private LiaotianAdapter myadapter;
    private List<LiaotianBean> list;
    private EMMessageListener msgListener;
    private String head;
    private String myhead1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liaotian);
        initView();
        initData();
        shou();
    }

    private void shou() {
        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                System.out.println(messages.size());
                //收到消息
                for ( final EMMessage message : messages) {
                    if(message.getFrom().equals(mobile)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(message.getBody().toString());
                                EMTextMessageBody body =(EMTextMessageBody) message.getBody();
                                String message1 = body.getMessage();
                                LiaotianBean liaotianBean=new LiaotianBean(message1,false,head);
                                list.add(liaotianBean);
                                myadapter.refresh(list);
                                rv.scrollToPosition(myadapter.getItemCount());
                            }
                        });
                    }


                }

            }
            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }
            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }
            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void initData() {
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        name = intent.getStringExtra("name");
        head = intent.getStringExtra("head");
        SharedPreferences myhead = getSharedPreferences("Myhead", MODE_PRIVATE);
        myhead1 = myhead.getString("myhead", null);
        System.out.println(mobile);
        list = new ArrayList<>();
        myadapter = new LiaotianAdapter(this, list);
        rv.setAdapter(myadapter);

    }


    private void initView() {
        rv = findViewById(R.id.liaotian_rv);
        msg = findViewById(R.id.liaotian_msg);
        LinearLayoutManager lm=new LinearLayoutManager(LiaotianActivity.this);
        rv.setLayoutManager(lm);
        rv.setPullRefreshEnabled(false);
        rv.setLoadingMoreEnabled(false);
    }

    public void sendmessage(View view) {
        String s = msg.getText().toString();
        if(s!=null&&!s.equals("")){
            EMMessage message = EMMessage.createTxtSendMessage(s, mobile);
            EMClient.getInstance().chatManager().sendMessage(message);
            LiaotianBean liaotianBean=new LiaotianBean(s,true,myhead1);
            list.add(liaotianBean);
            myadapter.refresh(list);
            msg.setText("");
            rv.scrollToPosition(myadapter.getItemCount());
            message.setMessageStatusCallback(new EMCallBack(){
                @Override
                public void onSuccess() {
                    System.out.println("发送成功");
                }
                @Override
                public void onError(int code, String error) {
                    System.out.println("发送失败"+error);
                }
                @Override
                public void onProgress(int progress, String status) {
                }
            });
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
