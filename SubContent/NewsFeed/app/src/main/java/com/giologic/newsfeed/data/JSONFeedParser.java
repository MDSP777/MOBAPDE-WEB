package com.giologic.newsfeed.data;

import android.widget.BaseAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by MSI LEOPARD on 3/12/2016.
 */
public class JSONFeedParser {

    public JSONFeedParser() {}

    public Collection<FeedItem> parseJSON(JSONObject response,BaseAdapter adapter)
    {
         ArrayList<FeedItem> feedItems = new ArrayList<FeedItem>();
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }
            // notify data changes to list adapater
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return feedItems;
    }

}
