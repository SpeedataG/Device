package com.speedata.device;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.speedata.utils.ProgressDialogUtils;

import im.delight.android.webview.AdvancedWebView;

public class ConfigFaqActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    private AdvancedWebView webView;

    private String faqURL = "https://www.showdoc.cc/page/107104";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_faq);

        webView = findViewById(R.id.webview);

        webView.loadUrl(faqURL);
        webView.setListener(this, this);

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        webView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        webView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        ProgressDialogUtils.showProgressDialog(this, "正在请求");
    }

    @Override
    public void onPageFinished(String url) {
        ProgressDialogUtils.dismissProgressDialog();
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        ProgressDialogUtils.dismissProgressDialog();
        Toast.makeText(this, "error" + errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public void onBackPressed() {
        if (!webView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
