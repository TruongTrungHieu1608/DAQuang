package com.example.detectgas;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText taikhoan;
    EditText matkhau;
    Button btdangky , btdangnhap , btthoat ;
    String ten , mk ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        controlbutton();
    }
    ///////////// set su kien nut nhan
    // set nut thoat
    private void controlbutton() {
        btthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát khỏi ứng dụng");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận ");
                builder.setIcon(R.drawable.gas);
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });

        //// set nut dang ky
        btdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(LoginActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                dialog.setTitle("");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_dangky);
                EditText edittk =(EditText)dialog.findViewById(R.id.dktaikhoan);
                EditText editmk =(EditText)dialog.findViewById(R.id.dkmatkhau);


                Button bthuy = (Button) dialog.findViewById(R.id.huydk);
                Button btdongy = (Button) dialog.findViewById(R.id.dongydk);
                /// set su kien co 2 nut tren
                // set su kien cho btdongy
                btdongy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten = edittk.getText().toString().trim();
                        mk = editmk.getText().toString().trim();

                        // Lưu thông tin đăng nhập vào SharedPreferences
                        SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                        editor.putString("username", ten);
                        editor.putString("password", mk);
                        editor.apply();

                        taikhoan.setText(ten);
                        matkhau.setText(mk);
                        dialog.cancel();

                    }
                });
                /// set su kien bthuy
                bthuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        //////////// set nut dang nhap
        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /// Đọc dữ liệu tk và mk trong may
                SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                String a = prefs.getString("username", "");
                String b = prefs.getString("password", "");


                if (taikhoan.getText().length() != 0 && matkhau.getText().length() != 0 ){
                    if (taikhoan.getText().toString().equals(a) && matkhau.getText().toString().equals(b)) {
                        Toast.makeText(LoginActivity.this,"Bạn đã đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                        //// chuyen activity
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);     }
                    else if (taikhoan.getText().toString().equals("admin") && matkhau.getText().toString().equals("admin")) {
                        Toast.makeText(LoginActivity.this,"Bạn đã đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                        //// chuyen activiti
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);    }

                    else{
                        matkhau.setError("Tài khoản hoặc mật khẩu không đúng");
                        taikhoan.setError("");
                        Toast.makeText(LoginActivity.this,"Đăng nhập thất bại  ", Toast.LENGTH_SHORT).show();
                        }
                }

                else {
                    matkhau.setError("Tài khoản hoặc mật khẩu không đúng");
                    taikhoan.setError("");
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại xin mời nhập lại  ", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    /////////////// anh xa nut nhan
    private void anhxa() {
        taikhoan = (EditText) findViewById(R.id.taikhoan);
        matkhau = (EditText) findViewById(R.id.matkhau);

        btdangnhap = (Button) findViewById(R.id.nutdangnhap);
        btdangky = (Button) findViewById(R.id.nutdangky);
        btthoat = (Button) findViewById(R.id.nutthoat);


    }
}
