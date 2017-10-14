package com.example.phamm.lichnhacnho;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddCongViecActivity extends AppCompatActivity {
    private TextView txtDay, txtTime, txtMussic,txtTieude,txtNoidung;
    private DatePickerDialog.OnDateSetListener mdatasetlistener;
    private TimePickerDialog.OnTimeSetListener mtimesetlistener;
    DatabaseReference mdatabasereference;
    Calendar cal;
    NhacNhoCongViec nncv = new NhacNhoCongViec();
    String mUpdate =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cong_viec);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addControls();
        addEvent();
    }
    private void addControls() {
        txtDay = (TextView) findViewById(R.id.datePick);
        txtTime = (TextView) findViewById(R.id.TimePick);
        txtMussic = (TextView) findViewById(R.id.Mussic);
        txtTieude = (TextView) findViewById(R.id.txtTitle);
        txtNoidung = (TextView) findViewById(R.id.txtNoiDung);
    }

    private void addEvent() {
        txtDay.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog DPD = new DatePickerDialog(AddCongViecActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mdatasetlistener,year,month,day);
                DPD.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                DPD.show();
            }
        });
        mdatasetlistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = month + "/" + dayOfMonth +"/"+year;
                txtDay.setText(date);
            }
        };
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minutes = cal.get(Calendar.MINUTE);
                TimePickerDialog tmp = new TimePickerDialog(AddCongViecActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mtimesetlistener,hour,minutes,true);
                tmp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                tmp.show();
            }
        });
        mtimesetlistener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay +" : "+ minute;
                txtTime.setText(time);
            }
        };

        txtMussic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(AddCongViecActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.accept_CV:
                mdatabasereference = FirebaseDatabase.getInstance().getReference();
                String title = txtTieude.getText().toString();
                String Day  =  txtDay.getText().toString();
                String NoiDung = txtNoidung.getText().toString();
                String Gio = txtTime.getText().toString();
                NhacNhoCongViec nhacnho_CV = new NhacNhoCongViec(Day,title,NoiDung,Gio);
                mdatabasereference.child("CongViec").push().setValue(nhacnho_CV, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError==null){
                            Toast.makeText(AddCongViecActivity.this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AddCongViecActivity.this, "Lưu Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Toast.makeText(this, "Accept", Toast.LENGTH_SHORT).show();
                Intent mintent = new Intent(AddCongViecActivity.this,MainActivity.class);
                startActivity(mintent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
