package com.example.ktgiuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ktgiuaky.Database.Database;

public class CreateActivity extends AppCompatActivity {

    EditText edtTenSP,edtCost;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edtTenSP=findViewById(R.id.edtName);
        edtCost=findViewById(R.id.edtCost);
        btnAdd=findViewById(R.id.btnCreate);


        CreateData();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(CreateActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void CreateData(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenSP=edtTenSP.getText().toString();
                String cost=edtCost.getText().toString();
                if(tenSP.isEmpty() || cost.isEmpty()){
                    Toast.makeText(CreateActivity.this,"vui long nhap du thong tin",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent=new Intent(CreateActivity.this,ConfirmActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("namesp",tenSP);
                    bundle.putString("costsp",cost);
                    intent.putExtra("product",bundle);

                    startActivity(intent);


                }


            }
        });

    }
}