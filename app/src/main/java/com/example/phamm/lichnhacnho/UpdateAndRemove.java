package com.example.phamm.lichnhacnho;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class UpdateAndRemove extends AppCompatActivity {
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
        setContentView(R.layout.activity_update_and_remove);
        addControls();
        addEvent();
        mdatabasereference = FirebaseDatabase.getInstance().getReference().child("CongViec");
        mUpdate = getIntent().getExtras().getString("congviec");
        mdatabasereference.child(mUpdate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String txttieude = (String) dataSnapshot.child("tieuDe").getValue();
                String txtnoidung = (String) dataSnapshot.child("noidung").getValue();
                String txtngay = (String) dataSnapshot.child("ngayThongBao").getValue();
                String txtgio = (String) dataSnapshot.child("gioThongBao").getValue();

                txtTieude.setText(txttieude);
                txtNoidung.setText(txtnoidung);
                txtDay.setText(txtngay);
                txtTime.setText(txtgio);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

                DatePickerDialog DPD = new DatePickerDialog(UpdateAndRemove.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mdatasetlistener,year,month,day);
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
                TimePickerDialog tmp = new TimePickerDialog(UpdateAndRemove.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mtimesetlistener,hour,minutes,true);
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
}
