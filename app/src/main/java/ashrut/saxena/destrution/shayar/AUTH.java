package ashrut.saxena.destrution.shayar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AUTH extends AppCompatActivity {

    EditText firstname, lastname, email;
    Button SAVE;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);

        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        SAVE = findViewById(R.id.saveBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference docref = fstore.collection("users").document(userid);



        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstname.getText().toString().isEmpty() && !lastname.getText().toString().isEmpty() && !email.getText().toString().isEmpty()) {
                    String first = firstname.getText().toString();
                    String last = lastname.getText().toString();
                    String useremail = email.getText().toString();

                    Map<String, Object> user = new HashMap<>();
                    user.put("first", first);
                    user.put("last", last);
                    user.put("email", useremail);
                    docref.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                                finish();
                            } else {
                                Toast.makeText(AUTH.this, "Data is not Inserted..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(AUTH.this, "ALL Fields are Required..", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}
