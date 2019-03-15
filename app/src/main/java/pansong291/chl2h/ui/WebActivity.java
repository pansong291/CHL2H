package pansong291.chl2h.ui;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends Zactivity
{
 WebView webView;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  // TODO: Implement this method
  super.onCreate(savedInstanceState);
  webView = new WebView(this);
  setContentView(webView);
  webView.loadDataWithBaseURL(null,getIntent().getStringExtra("data"),"text/html","utf-8",null);
 }
 
}
