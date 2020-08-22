package com.example.ktgiuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ktgiuaky.Database.Database;

public class ConfirmActivity extends AppCompatActivity {
    EditText edtTenSP,edtCostSP;
    Button btnSave,btnXoa;



    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        edtTenSP=findViewById(R.id.edtName);
        edtCostSP=findViewById(R.id.edtCost);
        btnSave=findViewById(R.id.btnSave);
        btnXoa=findViewById(R.id.btnXoa);
        database=new Database(this,"product.sqlite",null,3);

        getData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp=edtTenSP.getText().toString();
              //  String cost=edtCostSP.getText().toString();
                Double costSP=Double.parseDouble(edtCostSP.getText().toString());
                Toast.makeText(ConfirmActivity.this,tensp+""+costSP,Toast.LENGTH_LONG).show();
                try {
                    database.QueryData("INSERT INTO Product VALUES(null,'"+tensp+"'"+","+costSP+")");
                    Intent intent=new Intent(ConfirmActivity.this,MainActivity.class);

                    startActivity(intent);
                    finish();

                }catch (Exception e){

                    Log.d("BBB",e.toString());
                }

            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ConfirmActivity.this,MainActivity.class);

                startActivity(intent);
                finish();
            }
        });


    }

    private void getData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("product");
        String tenSP=bundle.getString("namesp");
        String costSP=bundle.getString("costsp");

        edtTenSP.setText(tenSP);
        edtCostSP.setText(costSP);

    }


}