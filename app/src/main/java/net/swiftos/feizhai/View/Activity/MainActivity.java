package net.swiftos.feizhai.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import net.swiftos.eventposter.Core.EventPoster;
import net.swiftos.eventposter.Impls.ViewEvent.Handler.ViewEventHandler;
import net.swiftos.feizhai.Protocol.MainProtocol;
import net.swiftos.feizhai.R;

import static net.swiftos.feizhai.Protocol.MainProtocol.MainContext;

public class MainActivity extends BaseActivity implements MainProtocol.View{

    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventPoster.RegistDeep(this);
        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        button = (Button) findViewById(R.id.button);
        EventPoster.With(ViewEventHandler.class).addView(MainContext,button);
    }

    @Override
    public void setText(String txt) {
        tv.setText(txt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPoster.With(ViewEventHandler.class).removeView(MainContext,button);
        EventPoster.UnRegistDeep(this);
    }
}
