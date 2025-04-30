package com.karon.sqlitedatabaseexampple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.karon.sqlitedatabaseexampple.adapters.ProductAdapter;
import com.karon.sqlitedatabaseexampple.classfiles.Product;
import com.karon.sqlitedatabaseexampple.helper.DatabaseHelper;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements ProductAdapter.OnUserClickListner {

    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mylistview = (ListView) findViewById(R.id.mylistview);
        loadata();

       // ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1,productList);
       // mylistview.setAdapter(adapter);
    }

    public void loadata()
    {
        DatabaseHelper db = new DatabaseHelper(ProductListActivity.this);
        ArrayList<Product> productList = db.viewProduct();

        ProductAdapter adapter = new ProductAdapter(ProductListActivity.this,productList,this);
        mylistview.setAdapter(adapter);
    }

    @Override
    public void onDeleteButtonClick(Product obj) {
        DatabaseHelper db = new DatabaseHelper(ProductListActivity.this);
        boolean result = db.deleteProduct(String.valueOf(obj.getProduct_id()));
        if(result)
        {
            Toast.makeText(this, "Record Deleted : "+obj.getProduct_name(), Toast.LENGTH_SHORT).show();
            loadata();
        }
    }

    @Override
    public void onEditButtonClick(Product obj) {
        Intent intent = new Intent(ProductListActivity.this,UpdateProductActivity.class);
        intent.putExtra("id",String.valueOf(obj.getProduct_id()));
        intent.putExtra("name",String.valueOf(obj.getProduct_name()));
        intent.putExtra("qty",String.valueOf(obj.getProduct_qty()));
        intent.putExtra("price",String.valueOf(obj.getProduct_price()));
        intent.putExtra("description",String.valueOf(obj.getProduct_description()));
        startActivity(intent);
    }
}