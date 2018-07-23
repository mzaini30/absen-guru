package com.mzaini30.absenguru;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
//import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
 
/*
 * Demo of creating an application to open any URL inside the application and clicking on any link from that URl
should not open Native browser but  that URL should open in the same screen.
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
 
    WebView web;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        web = (WebView) findViewById(R.id.webview01);
        web.setWebViewClient(new myWebClient());
        web.setBackgroundColor(0x00ffffff);  
        // webview untuk youtube?
        // webView.setWebChromeClient(new WebChromeClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDatabaseEnabled(true); 
        String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath(); 
        web.getSettings().setDatabasePath(databasePath);
        web.getSettings().setDomStorageEnabled(true);
        web.loadUrl("file:///android_asset/index.html");
    }
 
    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }
 
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
 
        	if (url.contains("http://") || url.contains("https://")){
        		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        		startActivity(i);
        	} else {
        		view.loadUrl(url);
        	}
            return true;
 
        }
    }
 
    // To handle "Back" key press event for WebView to go back to previous screen.
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
        web.goBack();
        return true;
    }
    return super.onKeyDown(keyCode, event);
   }
}
