package com.example.ktgiuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ktgiuaky.Adapter.ProductAdapter;
import com.example.ktgiuaky.Database.Database;
import com.example.ktgiuaky.Entity.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;

    Button btnCreateTable,btnDeleteTable,btnAddProduct;
    ListView lvProduct;
    ArrayList<Product> listProduct;
    ProductAdapter adapter;

    TextView txvNull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateTable=(Button)findViewById(R.id.btnCreateTable);
        btnDeleteTable=findViewById(R.id.btnDeleteTable);
        btnAddProduct=findViewById(R.id.btnAddProduct);
        txvNull=findViewById(R.id.txvNull);

        lvProduct=(ListView) findViewById(R.id.lvProduct);
        listProduct=new ArrayList<>();
        adapter=new ProductAdapter(this,R.layout.dong_san_pham,listProduct);
        lvProduct.setAdapter(adapter);


        // tao database
        database=new Database(this,"product.sqlite",null,3);





        CreateTable();



    }

    //=================================================================================================

    private void CreateTable()
    {
        boolean tableExist=tableExists("Product");
        Log.d("AAA", String.valueOf(tableExist));


        if(!tableExist)
        {
            btnAddProduct.setVisibility(View.INVISIBLE);
            txvNull.setVisibility(View.VISIBLE);


            // tao bang
            //database.QueryData("CREATE TABLE IF NOT EXISTS Product(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenSP VARCHAR(100),Gia DOUBLE)");
            btnCreateTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    database.QueryData("CREATE TABLE IF NOT EXISTS Product(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenSP VARCHAR(100),Gia DOUBLE)");
                    Intent intent=new Intent(MainActivity.this,CreateActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

        }
        else {

            txvNull.setVisibility(View.INVISIBLE);
            btnAddProduct.setVisibility(View.VISIBLE);
            deleteTable();
            AddProduct();
            getData();

        }


    }

    //==========================================================

    boolean tableExists( String tableName)
    {
        if (tableName == null )
        {
            return false;
        }
        Cursor cursor = database.GetData("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name ='"+tableName+"'");
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
    //===========================================================

    private void getData(){

        Cursor dataProduct=database.GetData("SELECT * FROM Product");


            while (dataProduct.moveToNext()){

                int id=dataProduct.getInt(0);
                String tensp=dataProduct.getString(1);
                Double cost=dataProduct.getDouble(2);
                Product product=new Product(id,tensp,cost);

                listProduct.add(product);

            }
            adapter.notifyDataSetChanged();


    }


    //==================================
    private void deleteTable(){
        btnDeleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete data
                database.QueryData("DROP TABLE IF EXISTS Product");

                Intent intent = getIntent();
                finish();
                startActivity(intent);


            }
        });

    }
    //========================================
    private void AddProduct(){
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
    }


}