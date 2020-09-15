package ashrut.saxena.destrution.shayar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class security extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText phonenumber, codeenter;
    Button nextbtn;
    ProgressBar progressBar;
    TextView state;
    CountryCodePicker codePicker;
    String verificationid;
    PhoneAuthProvider.ForceResendingToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        fAuth = FirebaseAuth.getInstance();
        phonenumber = findViewById(R.id.phone);
        codeenter = findViewById(R.id.codeEnter);
        nextbtn = findViewById(R.id.nextBtn);
        progressBar = findViewById(R.id.progressBar);
        state = findViewById(R.id.state);
        codePicker = findViewById(R.id.ccp);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phonenumber.getText().toString().isEmpty() && phonenumber.getText().toString().length() == 10) {
                    String phonenum = "+" + codePicker.getSelectedCountryCode() + phonenumber.getText().toString();
                    Log.d("TAG", "ON CLICK: PHONE NO -> " + phonenum);
                    requestOTP(phonenum);
                    progressBar.setVisibility(View.VISIBLE);
                    state.setText("SENDING OTP..");
                    state.setVisibility(View.VISIBLE);
                } else {
                    phonenumber.setError("Phone Number is not Valid..");
                }
            }
        });
    }

    private void requestOTP(String phonenum) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenum, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                codeenter.setVisibility(View.VISIBLE);
                verificationid = s;
                token = forceResendingToken;

            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(security.this, "Cannot Create Account.." + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
