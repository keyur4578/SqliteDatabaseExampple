package com.karon.sqlitedatabaseexampple;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class ProductListActivity extends AppCompatActivity {

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

        DatabaseHelper db = new DatabaseHelper(ProductListActivity.this);
        ArrayList<Product> productList = db.viewProduct();

        ProductAdapter adapter = new ProductAdapter(ProductListActivity.this,productList);
        mylistview.setAdapter(adapter);







       // ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1,productList);
       // mylistview.setAdapter(adapter);
    }
}