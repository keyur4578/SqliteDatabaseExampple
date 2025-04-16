package com.karon.sqlitedatabaseexampple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.karon.sqlitedatabaseexampple.helper.DatabaseHelper;

public class AddProductActivity extends AppCompatActivity {


    Button btnAdd;
    EditText edtProductName,edtProductQty,edtProductPrice,edtProductDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);
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

        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtProductName = (EditText) findViewById(R.id.edtProductName);
        edtProductQty = (EditText) findViewById(R.id.edtProductQty);
        edtProductPrice = (EditText) findViewById(R.id.edtProductPrice);
        edtProductDescription = (EditText) findViewById(R.id.edtProductDescription);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AddProductActivity.this);
            }
        });
    }
}