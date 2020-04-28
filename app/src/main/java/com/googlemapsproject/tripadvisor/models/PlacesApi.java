package com.googlemapsproject.tripadvisor.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class PlacesApi {
    public ArrayList<String > autoComplete(String input){
        ArrayList<String> description = new ArrayList<>();
        ArrayList<String> placeId = new ArrayList<>();
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();
        try{
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input=").append(input);
            sb.append("&key=AIzaSyAR9MeqlO757IUXzS6aBvhUfMAQd_ixDiU");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection)url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];
            while((read = inputStreamReader.read(buff)) != -1){
                jsonResult.append(buff,0,read);
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        try{
            JSONObject jsonObject = new JSONObject(jsonResult.toString());
            JSONArray predictions = jsonObject.getJSONArray("predictions");
            for (int i=0;i<predictions.length();i++){
                description.add(predictions.getJSONObject(i).getString("description"));
                placeId.add(predictions.getJSONObject(i).getString("place_id"));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return description;
    }
}
