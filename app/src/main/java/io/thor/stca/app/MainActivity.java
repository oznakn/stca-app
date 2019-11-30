package io.thor.stca.app;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

public class MainActivity extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFingerPrint();
            }
        });
    }

    private void startFingerPrint() {
        FingerprintManagerCompat manager = FingerprintManagerCompat.from(this);

        BiometricCallback biometricCallback = new BiometricCallback();

        if (manager.isHardwareDetected() &&
                manager.hasEnrolledFingerprints()) {
            showFingerPrintAuth(biometricCallback);
        } else {
            Toast.makeText(this, "Fingerprint authentication is not supported", Toast.LENGTH_SHORT).show();
        }
    }

    private void showFingerPrintAuth(final BiometricCallback biometricCallback) {
        CancellationSignal mCancellationSignal = new CancellationSignal();

        new BiometricPrompt.Builder(this)
                .setTitle("Biometrics")
                .setSubtitle("")
                .setDescription("")
                .setNegativeButton("Cancel", this.getMainExecutor(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        biometricCallback.onAuthenticationFailed();
                    }
                })
                .build()
                .authenticate(mCancellationSignal, this.getMainExecutor(), biometricCallback);
    }

    public static class BiometricCallback extends BiometricPrompt.AuthenticationCallback {
        public BiometricCallback() {
        }

        @Override
        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
        }

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
        }
    }
}
