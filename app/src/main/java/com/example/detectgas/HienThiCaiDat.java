package com.example.detectgas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HienThiCaiDat extends AppCompatActivity {


    private List<objectCaiDat> danhSachMuc;
    private TextView textViewChonMuc;
    sqlCaiDat sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cai_dat);
        sql = new sqlCaiDat(HienThiCaiDat.this);
        danhSachMuc = sql.getEveryone();

        Button button = findViewById(R.id.buttonHienThiDanhSach);
        textViewChonMuc = findViewById(R.id.textViewChonMuc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiDanhSach();
            }
        });
    }

    public void hienThiDanhSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn một mục");

        // Tạo mảng String chứa tên các mục để hiển thị trong AlertDialog
        String[] tenMuc = new String[danhSachMuc.size()];
        for (int i = 0; i < danhSachMuc.size(); i++) {
            tenMuc[i] = danhSachMuc.get(i).getTenRau();
        }

        builder.setItems(tenMuc, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                objectCaiDat thongTinMuc = danhSachMuc.get(which);
                String chonMuc = thongTinMuc.getTenRau();

                // Hiển thị thông tin về mục được chọn trong TextView
                textViewChonMuc.setText("Mục đã chọn: " + chonMuc + ", Độ ẩm: " + thongTinMuc.getDoAm() + ", Nhiệt độ: " + thongTinMuc.getNhietDo());
                textViewChonMuc.setVisibility(View.VISIBLE);
                // Thực hiện công việc cần thiết với mục đã chọn
                Toast.makeText(getApplicationContext(), "Bạn đã chọn: " + chonMuc, Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}