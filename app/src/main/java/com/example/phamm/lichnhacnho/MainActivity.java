package com.example.phamm.lichnhacnho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FB_DATABASE_PATH = "CongViec";
    ListView listView;
    ArrayList<NhacNhoCongViec> list;
    CustomApdapter adapter;
    DatabaseReference databaseReference;
    private String mUserId;
    FirebaseUser  mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NhacNhoCongViec nncv = snapshot.getValue(NhacNhoCongViec.class);
                    list.add(nncv);
                }
                adapter = new CustomApdapter(MainActivity.this, R.layout.item_listview, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,UpdateAndRemove.class);
               // String ref = getRef(position).getKey();
                intent.putExtra("congviec",);
                startActivity(intent);
            }
        });
    }
    private void addView() {
        listView = (ListView) findViewById(R.id.listCongViec);
        list = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_CV:
                Intent intent = new Intent(MainActivity.this, AddCongViecActivity.class);
                startActivity(intent);
                //Toast.makeText(this, "Activity2", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
