package ashrut.saxena.destrution.shayar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
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
    Boolean verificationinprogress = false;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        phonenumber = findViewById(R.id.phone);
        codeenter = findViewById(R.id.codeEnter);
        nextbtn = findViewById(R.id.nextBtn);
        progressBar = findViewById(R.id.progressBar);
        state = findViewById(R.id.state);
        codePicker = findViewById(R.id.ccp);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificationinprogress) {
                    if (!phonenumber.getText().toString().isEmpty() && phonenumber.getText().toString().length() == 10) {
                        String phonenum = "+" + codePicker.getSelectedCountryCode() + phonenumber.getText().toString();
                        Log.d("TAG", "ON CLICK: PHONE NO -> " + phonenum);
                        progressBar.setVisibility(View.VISIBLE);
                        state.setText("SENDING OTP..");
                        state.setVisibility(View.VISIBLE);
                        requestOTP(phonenum);

                    } else {
                        phonenumber.setError("Phone Number is not Valid..");
                    }
                } else {
                    String userotp = codeenter.getText().toString();
                    if (!userotp.isEmpty() && userotp.length() == 6) {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, userotp);
                        verifyauth(credential);
                    } else {
                        codeenter.setError("Valid OTP is Required..");
                    }
                }
            }
        });
    }

    private void verifyauth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(security.this, "Authentication is Successful.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(security.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
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
                nextbtn.setText("Verify");
                verificationinprogress = true;


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
