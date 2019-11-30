package io.thor.stca.app;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.KeyGenerator;


public class KeyManager {
    private static final String BIO_KEY = "7c16d015074026d2d05233aa";
    private static final String SEED = "ORSXG5A=";

    private static KeyManager mInstance;

    public static KeyManager get(Context context) {
        if (mInstance == null) {
            mInstance = new KeyManager();
            mInstance.context = context;
        }

        return mInstance;
    }

    private Context context;

    public String getDeviceKey() {
        return BIO_KEY;

        /* return UUID.randomUUID().toString().substring(0, 8)
            + Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID); */
    }

    public String getOneTimeKey() {
        try {
            return Integer.toString(TimeBasedOneTimePasswordUtil.generateCurrentNumber(SEED));
        }
        catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }
}
