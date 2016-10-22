package net.swiftos.feizhai.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.swiftos.eventposter.Impls.CustomEvent.Annotation.InjectEvent;
import net.swiftos.eventposter.Impls.CustomEvent.Entity.RunContextType;

/**
 * Created by ganyao on 2016/10/22.
 */

public class BaseActivity extends AppCompatActivity {

    @InjectEvent
    public void onEventStr(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

}
