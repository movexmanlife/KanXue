package com.pediy.kanxue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.pediy.kanxue.injector.component.DaggerActivityComponent;
import com.pediy.kanxue.injector.module.ActivityModule;
import com.pediy.kanxue.ui.login.LoginActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startActivity(MainActivity.this);
            }
        });
    }

    /**
     * 注入Injector
     */
    @Override
    public void initInjector() {
    }

}
