package ie.dcu.computing.student.buckero2.broganua;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView productsRecyclerView;
    ArrayList<Products> productsList;
    ArrayList<Products> productsListFull;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference productsReference = firebaseDatabase.getReference().child("products");

        // hide the default actionbar
        getSupportActionBar().hide();

        // Receive data

        String brand  = getIntent().getExtras().getString("brand");
        final String model = getIntent().getExtras().getString("model");
        String price = "€" + getIntent().getExtras().getDouble("price") ;
        String image_url = getIntent().getExtras().getString("image") ;

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_product);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_brand = findViewById(R.id.aaBrand);
        TextView tv_model = findViewById(R.id.aaModel);
        TextView tv_price = findViewById(R.id.aaPrice) ;
        Button shop = findViewById(R.id.aaShop) ;
        ImageView img = findViewById(R.id.aaThumbnail);

        // setting values to each view

        tv_brand.setText(brand);
        tv_model.setText(model);
        tv_price.setText(price);
        shop.setText("BUY");

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getExtras().getString("link")));
                startActivity(browserIntent);
            }
        });
        collapsingToolbarLayout.setTitle(model);

        Picasso.get().load(image_url).into(img);


        productsRecyclerView = findViewById(R.id.productsRecycler);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this));
        productsList = new ArrayList<Products>();
        reference = FirebaseDatabase.getInstance().getReference().child("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Products p = dataSnapshot1.getValue(Products.class);
                    productsList.add(p);
                }
                Collections.sort(productsList, new PriceSorter());
                adapter = new MyAdapter(ProductActivity.this, productsList);
                adapter.getFilter().filter(model);
                productsRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProductActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
