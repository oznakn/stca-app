package io.thor.stca.app;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class NetworkManager {
    private final static String SERVER_URL = "http://172.104.242.180";

    public static void sendData(Context context, String bioId, String loginUri, String pairKey, String tpass, FutureCallback<JsonObject> callback) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("bio_id", bioId);
        jsonObject.addProperty("loginUri", loginUri);
        jsonObject.addProperty("pair_key", pairKey);
        jsonObject.addProperty("tpass", tpass);

        Ion.with(context)
                .load("POST",SERVER_URL + "/stca/permit/")
                .setJsonObjectBody(jsonObject)
                .asJsonObject()
                .setCallback(callback);
    }
}