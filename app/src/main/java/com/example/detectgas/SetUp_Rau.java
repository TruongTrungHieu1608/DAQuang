package com.example.detectgas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetUp_Rau extends AppCompatActivity {

    EditText etNhapTenRau, etNhapDoAm, etNhapNhietDo;
    Button btnHome, btnAdd, btnShow;
    objectCaiDat obcd;
    sqlCaiDat sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_rau);
        addControls();
        eventBtn();
        addObject();
        btnADD();
    }

    private void btnADD() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = etNhapTenRau.getText().toString();
                String doam = etNhapDoAm.getText().toString();
                String nhietdo = etNhapNhietDo.getText().toString();
                int doamint = Integer.valueOf(doam);
                int nhietdoint = Integer.valueOf(nhietdo);
                obcd = new objectCaiDat(-1, nhietdoint, doamint, ten);
                sql.addOne(obcd);
            }
        });
    }

    private void addObject() {

//        obcd = new objectCaiDat(nhietdoint, doamint, ten);

    }

    private void eventBtn() {
//        btnHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SetUp_Rau.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SetUp_Rau.this, HienThiCaiDat.class);
                startActivity(intent1);
            }
        });
    }

    private void addControls() {
        etNhapTenRau = findViewById(R.id.etTenRau);
        etNhapDoAm = findViewById(R.id.etDoAm);
        etNhapNhietDo = findViewById(R.id.etNhietDo);
        btnHome = findViewById(R.id.button);
        btnAdd = findViewById(R.id.button3);
        sql = new sqlCaiDat(SetUp_Rau.this);
        btnShow = findViewById(R.id.button4);
    }

}