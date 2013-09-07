package com.krikelin.nlt;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import android.os.AsyncTask;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

public class NewsFeed {
	public interface NewsLoadedEventHandler {
		public void newsLoaded(List<RssItem> items);
	}
	public void loadFeed(URL url, final NewsLoadedEventHandler handler) {
		AsyncTask<String, String, ArrayList<RssItem>> at = new AsyncTask<String, String, ArrayList<RssItem>>() {

			@Override
			protected ArrayList<RssItem> doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				try {
					URL url = new URL(arg0[0]);
					RssFeed feed = RssReader.read(url);
					ArrayList<RssItem> rssItems = feed.getRssItems();
					return rssItems;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			} 
  
			@Override
			protected void onPostExecute(ArrayList<RssItem> result) {
				// TODO Auto-generated method stub
				if(handler != null) {
					handler.newsLoaded(result);
				}
				super.onPostExecute(result);
			}
			
		};
		at.execute(url.toString());
	}
}
