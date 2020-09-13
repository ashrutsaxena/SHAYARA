package ashrut.saxena.destrution.shayar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class SECURITY extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText phonenumber, codeenter;
    Button nextbtn;
    ProgressBar progressBar;
    TextView state;
    CountryCodePicker codePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        fAuth = FirebaseAuth.getInstance();
        phonenumber = findViewById(R.id.phone);
        codeenter = findViewById(R.id.codeEnter);
        progressBar = findViewById(R.id.progressBar);
        nextbtn = findViewById(R.id.nextBtn);
        state = findViewById(R.id.state);
        codePicker = findViewById(R.id.ccp);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phonenumber.getText().toString().isEmpty() && phonenumber.getText().toString().length() == 10) {


                } else {
                    phonenumber.setError("Phone Number is not Valid");
                }
            }
        });
    }
}
