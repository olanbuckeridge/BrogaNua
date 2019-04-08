package ie.dcu.computing.student.buckero2.broganua;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // hide the default actionbar
        getSupportActionBar().hide();

        // Receive data

        String brand  = getIntent().getExtras().getString("brand");
        String model = getIntent().getExtras().getString("model");
        String price = getIntent().getExtras().getString("price") ;
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



    }
}
