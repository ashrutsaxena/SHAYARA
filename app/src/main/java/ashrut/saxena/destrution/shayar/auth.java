package ashrut.saxena.destrution.shayar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class auth extends AppCompatActivity {
    EditText firstname, lastname, email;
    Button Save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        Save = findViewById(R.id.saveBtn);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && !email.getText().toString().isEmpty()) {

                }
            }
        });
    }
}
