package com.karon.sqlitedatabaseexampple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.karon.sqlitedatabaseexampple.helper.DatabaseHelper;

public class UpdateProductActivity extends AppCompatActivity {

    Button btnUpdate;
    EditText edtProductName,edtProductQty,edtProductPrice,edtProductDescription;
    String id,name,qty,price,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        qty = getIntent().getStringExtra("qty");
        price = getIntent().getStringExtra("price");
        description = getIntent().getStringExtra("description");

        // Enable back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        edtProductName = (EditText) findViewById(R.id.edtProductName);
        edtProductQty = (EditText) findViewById(R.id.edtProductQty);
        edtProductPrice = (EditText) findViewById(R.id.edtProductPrice);
        edtProductDescription = (EditText) findViewById(R.id.edtProductDescription);

        edtProductName.setText(name);
        edtProductQty.setText(qty);
        edtProductPrice.setText(price);
        edtProductDescription.setText(description);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(UpdateProductActivity.this);
                String pname = edtProductName.getText().toString();
                String qty = edtProductQty.getText().toString();
                String price = edtProductPrice.getText().toString();
                String description = edtProductDescription.getText().toString();

                if(pname.isEmpty() || qty.isEmpty() || price.isEmpty() || description.isEmpty())
                {
                    Toast.makeText(UpdateProductActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean result = db.updateProduct(pname,qty,price,description,id);
                if(result)
                {
                    finish();
                    finish();
                    Intent intent = new Intent(UpdateProductActivity.this,ProductListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}