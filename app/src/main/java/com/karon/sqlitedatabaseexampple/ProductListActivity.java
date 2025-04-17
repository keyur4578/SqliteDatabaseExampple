package com.karon.sqlitedatabaseexampple;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.karon.sqlitedatabaseexampple.helper.DatabaseHelper;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    ListView mylistview;
    ArrayList<String> productList;
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
        mylistview = (ListView) findViewById(R.id.mylistview);

        DatabaseHelper db = new DatabaseHelper(ProductListActivity.this);
        productList = db.viewProduct();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductListActivity.this, android.R.layout.simple_list_item_1,productList);
        mylistview.setAdapter(adapter);

    }
}