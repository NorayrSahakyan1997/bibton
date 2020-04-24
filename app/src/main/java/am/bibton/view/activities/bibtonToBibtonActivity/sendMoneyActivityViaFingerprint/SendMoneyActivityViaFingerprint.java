package am.bibton.view.activities.bibtonToBibtonActivity.sendMoneyActivityViaFingerprint;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.model.transferMoneyModel.TransferMoneyModel;
import am.bibton.presenter.SendMoneyViaFingerptintPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.bibtonToBibtonActivity.FingerprintHandler;
import am.bibton.view.activities.bibtonToBibtonActivity.transferWasDoneActivity.TransferWasDoneActivity;
import am.bibton.view.activities.bibtonToBibtonActivity.writeCodeActivityForMoneyTranfer.WritePassCodeActivity;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.inject.Inject;


@TargetApi(Build.VERSION_CODES.P)
public class SendMoneyActivityViaFingerprint extends BaseActivity implements ISendMoneyActivityViaFingerprint {
    private static final String KEY_NAME = "yourKey";
    private Cipher cipher;
    private KeyStore keyStore;
    private int fromCurrencyPosition;
    private int toCurrencyId;
    private float amount;
    private String receivedUnique;
    private String toUserUniqueId;
    TransferMoneyModel transferMoneyModel;


    @Inject
    SendMoneyViaFingerptintPresenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getUniqueIdForIngerPrint();
        setAnimation(getResources().getString(R.string.fingerPrintScreenAnim));
        TextView textView = findViewById(R.id.textview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Get an instance of KeyguardManager and FingerprintManager//
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);


            //Check whether the device has a fingerprint sensor//
            if (!fingerprintManager.isHardwareDetected()) {
                Intent writePasscodeActivity = new Intent(this, WritePassCodeActivity.class);
                startActivity(writePasscodeActivity);
                textView.setText("Your device doesn't support fingerprint authentication");
            }
            //Check whether the user has granted your app the USE_FINGERPRINT permission//
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // If your app doesn't have this permission, then display the following text//
                textView.setText("Please enable the fingerprint permission");
            }

            //Check that the user has registered at least one fingerprint//
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                // If the user hasn’t configured any fingerprints, then display the following message//
                textView.setText("No fingerprint configured. Please register at least one fingerprint in your device's Settings");
            }

            //Check that the lockscreen is secured//
            if (!keyguardManager.isKeyguardSecure()) {
                // If the user hasn’t secured their lockscreen with a PIN password or pattern, then display the following text//
            } else {
                try {
                    generateKey();
                } catch (FingerprintException e) {
                    e.printStackTrace();
                }

                if (initCipher()) {
                    //If the cipher is initialized successfully, then create a CryptoObject instance//
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

                    FingerprintHandler helper = new FingerprintHandler(this, isSuccess -> {
                        mPresenter.sendMoneyViaFringerprint(transferMoneyModel);
                        setAnimation(getResources().getString(R.string.fingerPrinDoneAnim));


                    });
                    helper.startAuth(fingerprintManager, cryptoObject);

                }
            }
        }
    }

    private void setAnimation(String animName) {
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_animation_splash);
        lottieAnimationView.setAnimation(animName);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animName.equals(getResources().getString(R.string.fingerPrinDoneAnim))) {
//                    Intent writePasscodeActivity = new Intent(getApplicationContext(), WritePassCodeActivity.class);
//                    startActivity(writePasscodeActivity);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        lottieAnimationView.playAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generateKey() throws FingerprintException {
        try {
            // Obtain a reference to the Keystore using the standard Android keystore container identifier (“AndroidKeystore”)//
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            //Generate the key//
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            //Initialize an empty KeyStore//
            keyStore.load(null);
            //Initialize the KeyGenerator//
            keyGenerator.init(new
                    //Specify the operation(s) this key can be used for//
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            //Generate the key//
            keyGenerator.generateKey();
        } catch (KeyStoreException
                | NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }

    //Create a new method that we’ll use to initialize our cipher//
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean initCipher() {
        try {
            //Obtain a cipher instance and configure it with the properties required for fingerprint authentication//
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {

            //Return false if cipher initialization failed//
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    @Override
    public void getMessage(boolean message) {
        if (message) {
            Intent intent = new Intent(this, TransferWasDoneActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getUniqueId(String uniqueId) {
        receivedUnique = uniqueId;
        receiveIntentValues();
    }

    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }

    public void receiveIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra("fromCurrencyPosition")) {
            transferMoneyModel = new TransferMoneyModel();
            fromCurrencyPosition = intent.getIntExtra("fromCurrencyPosition", 0);
            toCurrencyId = intent.getIntExtra("toCurrencyId", 0);
            amount = intent.getFloatExtra("amount", 0);
            toUserUniqueId = intent.getStringExtra("uniqueId");
            transferMoneyModel.setAmount(amount);
            transferMoneyModel.setTo_user_unique("+37491106116");
            transferMoneyModel.setUnique_id(receivedUnique);
            transferMoneyModel.setFrom_wallet_currency(fromCurrencyPosition);
            transferMoneyModel.setFingerprint(1);
            transferMoneyModel.setPasscode(0);
            transferMoneyModel.setTo_currency(toCurrencyId);
        }
        if (intent.hasExtra("activityChangePhoneNumber")) {
            Toast.makeText(this, "Change", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToPassCodeActivity(View view) {
        Intent goToPassCodeActivity = new Intent(this, WritePassCodeActivity.class);
        goToPassCodeActivity.putExtra("fromCurrencyPosition", Constants.FROM_CURRENCY_LONG_ID);
        goToPassCodeActivity.putExtra("toCurrencyId", toCurrencyId);
        goToPassCodeActivity.putExtra("amount", amount);
        goToPassCodeActivity.putExtra("uniqueId", toUserUniqueId);
        startActivity(goToPassCodeActivity);
    }
}


