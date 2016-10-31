package net.swiftos.feizhai.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import net.swiftos.eventposter.Core.EventPoster;
import net.swiftos.eventposter.Impls.ViewEvent.Handler.ViewEventHandler;
import net.swiftos.feizhai.Component.AppComponent;
import net.swiftos.feizhai.Presenter.Controller.BasePresenter;
import net.swiftos.feizhai.Protocol.MainProtocol;
import net.swiftos.feizhai.R;

import static net.swiftos.feizhai.Protocol.MainProtocol.MainContext;

public class MainActivity extends BaseActivity implements MainProtocol.View{

    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventPoster.RegistDeep(this);
        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        button = (Button) findViewById(R.id.button);
        EventPoster.With(ViewEventHandler.class).addView(MainContext,button);
        startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    public void setText(String txt) {
        tv.setText(txt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("gy","destroy");
        EventPoster.With(ViewEventHandler.class).removeView(MainContext,button);
        EventPoster.UnRegistDeep(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter setupActivityComponent(AppComponent appComponent) {
        return null;
    }
}
