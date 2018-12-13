package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgBack;
    private WebView webView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);

        Toolbar tToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(tToolBar);

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);

        webView = findViewById(R.id.webView);

        webViewSettingSInit();

        webView.loadUrl(getIntent().getStringExtra("URL"));

    }

    //得到webviewSettings对webview进行初始化设置
    private void webViewSettingSInit() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);//设置可以支持缩放
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
