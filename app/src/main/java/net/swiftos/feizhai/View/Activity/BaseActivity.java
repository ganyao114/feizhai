package net.swiftos.feizhai.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import net.swiftos.eventposter.Impls.CustomEvent.Annotation.InjectEvent;
import net.swiftos.eventposter.Impls.CustomEvent.Entity.RunContextType;
import net.swiftos.feizhai.Model.Event.LoginedEvent;

/**
 * Created by ganyao on 2016/10/22.
 */

public class BaseActivity extends AppCompatActivity {

    //登陆成功，通知刷新界面
    @InjectEvent(runType = RunContextType.MainThread)
    public void onLoginedEvent(LoginedEvent event){
        Toast.makeText(this,"Logined",Toast.LENGTH_LONG).show();
        Log.e("gy","logined");
    }

}
