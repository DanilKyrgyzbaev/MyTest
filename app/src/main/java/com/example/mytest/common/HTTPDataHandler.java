package com.example.mytest.common;


import android.app.ProgressDialog;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytest.rssfeed.Item;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HTTPDataHandler {
    static String stream = null;
    Context context;
    //   String address = "http://www.sciencemag.org/rss/news_current.xml";
    ProgressDialog progressDialog;
    ArrayList<Item> feedItems;
    RecyclerView recyclerView;
    URL url;


    //before fetching of rss statrs show progress to user
    //  @Override
    //    protected void onPreExecute() {
    //        progressDialog.show();
    //        super.onPreExecute();
    //    }


    //This method will execute in background so in this method download rss feeds
    //  @Override
    //    protected Void doInBackground(Void... params) {
    //        //call process xml method to process document we downloaded from getData() method
    //        ProcessXml(Getdata());
    //
    //        return null;
    //    }

//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        progressDialog.dismiss();
//        FeedAdapter adapter = new FeedAdapter(context, feedItems);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.addItemDecoration(new VerticalSpace(20));
//        recyclerView.setAdapter(adapter);
//
//    }

    // In this method we will process Rss feed  document we downloaded to parse useful information from it
//    private void ProcessXml(HTTPDataHandler data) {
//        if (data != null) {
//            feedItems = new ArrayList<>();
//            RangeValueIterator.Element root = data.getDocumentElement();
//            Node channel = root.getChildNodes().item(1);
//            NodeList items = channel.getChildNodes();
//            for (int i = 0; i < items.getLength(); i++) {
//                Node cureentchild = items.item(i);
//                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
//                    Item item = new Item();
//                    NodeList itemchilds = cureentchild.getChildNodes();
//                    for (int j = 0; j < itemchilds.getLength(); j++) {
//                        Node cureent = itemchilds.item(j);
//                        if (cureent.getNodeName().equalsIgnoreCase("title")) {
//                            item.setTitle(cureent.getTextContent());
//                        } else if (cureent.getNodeName().equalsIgnoreCase("description")) {
//                            item.setDescription(cureent.getTextContent());
//                        } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
//                            item.setPubDate(cureent.getTextContent());
//                        } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
//                            item.setLink(cureent.getTextContent());
//                        } else if (cureent.getNodeName().equalsIgnoreCase("media:thumbnail")) {
//                            //this will return us thumbnail url
//                            String url = cureent.getAttributes().item(0).getTextContent();
//                            item.setThumbnailUrl(url);
//                        }
//                    }
//                    feedItems.add(item);
//
//
//                }
//            }
//        }
//    }

    //This method will download rss feed document from specified url
    public String GetHTTPData(String urlS) {
        try {
            url = new URL(urlS);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sd = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) sd.append(line);
                stream = sd.toString();
                urlConnection.disconnect();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

}
