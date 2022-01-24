package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Main Activity";
    private static final int PRODUCT_REQUEST = 1;
    private int productCount = 0;

    //Define all the view
    private TextView mHeader;
    private Button mAddProductButton;

    //define the list with the product's views
    private ArrayList<TextView> products = new ArrayList<TextView>(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log message
        Log.d(LOG_TAG, "onCreate() executed");


        //Initialize all de View
        mHeader = findViewById(R.id.header_main);
        mAddProductButton = findViewById(R.id.button_add_product);

        products.add((TextView) findViewById(R.id.product1));
        products.add((TextView) findViewById(R.id.product2));
        products.add((TextView) findViewById(R.id.product3));
        products.add((TextView) findViewById(R.id.product4));
        products.add((TextView) findViewById(R.id.product5));
        products.add((TextView) findViewById(R.id.product6));
        products.add((TextView) findViewById(R.id.product7));
        products.add((TextView) findViewById(R.id.product8));
        products.add((TextView) findViewById(R.id.product9));
        products.add((TextView) findViewById(R.id.product10));

        if (savedInstanceState != null) {
            productCount = savedInstanceState.getInt("count_saved");
            for (int i = 0; i < productCount; i++) {
                products.get(i).setText(savedInstanceState.getString("product" + i));
            }
            if (productCount == 10)
                mAddProductButton.setBackgroundColor(0xFFA9ACAB);
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < productCount; i++) {
            outState.putString("product" + i, products.get(i).getText().toString());
        }
        outState.putInt("count_saved", productCount);


    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (PRODUCT_REQUEST == requestCode) {
            if (RESULT_OK == resultCode) {

                products.get(productCount).setText(data.getStringExtra(SecondActivity.EXTRA_PRODUCT));
                productCount++;

            }
        }
    }

    public void addProduct(View view) {
        //Log message
        Log.d(LOG_TAG, "Button ADD PRODUCT clicked");

        if (productCount < 10) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivityForResult(intent, PRODUCT_REQUEST);
        } else {
            Toast toast = Toast.makeText(this, "There is no more space in your basket!", Toast.LENGTH_SHORT);
            toast.show();
            view.setBackgroundColor(0xFFA9ACAB);
        }
    }
}
