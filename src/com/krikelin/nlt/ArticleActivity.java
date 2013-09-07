package com.krikelin.nlt;

import nl.matshofman.saxrssreader.RssItem;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class ArticleActivity extends Activity {

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		RssItem data = (RssItem)getIntent().getParcelableExtra("data");
		WebView wv = (WebView)findViewById(R.id.webView);
		setTitle(data.getTitle());
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebChromeClient(new WebChromeClient() {
			
		});
		wv.setWebViewClient(new WebViewClient() {


		    @Override
		    public void onPageFinished(WebView view, String url) 
		    {       
		        // Obvious next step is: document.forms[0].submit()
		        view.loadUrl("javascript:document.body.innerHTML = document.body.innerHTML.substring(document.body.innerHTML.indexOf('<div class=\"toolbar top\">'));");
		     
		    }

			
		});
		wv.loadUrl(data.getLink() + "?m=print");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article, menu);
		return true;
	}

}
