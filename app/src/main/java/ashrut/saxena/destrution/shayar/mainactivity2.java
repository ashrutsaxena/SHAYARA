package ashrut.saxena.destrution.shayar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class mainactivity2 extends AppCompatActivity {

    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    TextView pName, pEmail, pPhone;
    Toolbar toolbar;
    Button quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pName = findViewById(R.id.profileFullName);
        pEmail = findViewById(R.id.profileEmail);
        pPhone = findViewById(R.id.profilePhone);
        toolbar = findViewById(R.id.toolbar);
        quote = findViewById(R.id.button);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainactivity2.this, quotes.class));
            }
        });

        DocumentReference DOCREF = fstore.collection("users").document(fauth.getCurrentUser().getUid());
        DOCREF.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String jos = documentSnapshot.getString("firstName") + " " + documentSnapshot.getString("lastName");
                    pName.setText(jos);
                    pEmail.setText(documentSnapshot.getString("email"));
                    pPhone.setText(fauth.getCurrentUser().getPhoneNumber());
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) ;
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), security.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}




