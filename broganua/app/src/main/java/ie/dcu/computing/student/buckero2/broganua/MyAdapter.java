package ie.dcu.computing.student.buckero2.broganua;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Products> products;
    Uri uri;
    public MyAdapter(Context c, ArrayList<Products> p)
    {
        context = c;
        products = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.catalog_card_view, viewGroup ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.brand.setText(products.get(i).getBrand());
        myViewHolder.model.setText(products.get(i).getModel());
        myViewHolder.price.setText(products.get(i).getPrice());
        myViewHolder.onClick(i);
        Picasso.get().load(products.get(i).getImage()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView brand, model, price;
        ImageView image;
        Button shop;
        public MyViewHolder(View itemView) {
            super(itemView);
            brand = (TextView) itemView.findViewById(R.id.brand);
            model = (TextView) itemView.findViewById(R.id.model);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.productImage);
            shop = (Button) itemView.findViewById(R.id.shop);

        }

        public void onClick(final int position) {
            shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(products.get(position).getLink()));
                    context.startActivity(browserIntent);
                }
            });
        }

    }
}
