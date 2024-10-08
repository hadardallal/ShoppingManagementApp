package com.example.shoppingmanagementapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private EditText productNameEditText, quantityEditText;
    private Button addProductButton, removeProductButton;
    private ListView productListView;
    private ArrayList<String> productList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        usernameTextView = findViewById(R.id.usernameTextView);
        productNameEditText = findViewById(R.id.productName);
        quantityEditText = findViewById(R.id.quantity);
        addProductButton = findViewById(R.id.addProductButton);
        removeProductButton = findViewById(R.id.removeProductButton);
        productListView = findViewById(R.id.productListView);

        String username = getIntent().getStringExtra("username");
        usernameTextView.setText("Welcome " + username);

        productList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);

        addProductButton.setOnClickListener(v -> addProduct());
        removeProductButton.setOnClickListener(v -> removeProduct());
    }

    private void addProduct() {
        String product = productNameEditText.getText().toString();
        String quantity = quantityEditText.getText().toString();
        if (!product.isEmpty() && !quantity.isEmpty()) {
            productList.add(product + " - " + quantity);
            adapter.notifyDataSetChanged();
        }
    }

    private void removeProduct() {
        String product = productNameEditText.getText().toString();
        productList.removeIf(p -> p.contains(product));
        adapter.notifyDataSetChanged();
    }
}
