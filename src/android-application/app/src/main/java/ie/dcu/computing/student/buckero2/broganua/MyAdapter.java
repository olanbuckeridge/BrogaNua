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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable, Serializable {

    Context context;
    ArrayList<Products> products;
    private ArrayList<Products> productsFull;
    public MyAdapter(Context c, ArrayList<Products> p)
    {
        context = c;
        products = p;
        productsFull = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.catalogue_card_view, viewGroup, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.progressBar.setVisibility(View.INVISIBLE);
        viewHolder.image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("productsList",products);
                i.putExtra("brand",products.get(viewHolder.getAdapterPosition()).getBrand());
                i.putExtra("model",products.get(viewHolder.getAdapterPosition()).getModel());
                i.putExtra("price",products.get(viewHolder.getAdapterPosition()).getPrice());
                i.putExtra("image",products.get(viewHolder.getAdapterPosition()).getImage());
                i.putExtra("link",products.get(viewHolder.getAdapterPosition()).getLink());

                context.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.brand.setText(products.get(i).getBrand());
        myViewHolder.model.setText(products.get(i).getModel());
        myViewHolder.price.setText("€"+products.get(i).getPrice());
        myViewHolder.onClick(i);
        Picasso.get().load(products.get(i).getImage()).into(myViewHolder.image);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }


    @Override
    public Filter getFilter() {
        return testFilter;
    }
    private Filter testFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Products> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(productsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Products item: productsFull) {
                    if ((item.getModel().toLowerCase().contains(filterPattern)) || (item.getBrand().toLowerCase().contains(filterPattern)|| (item.getRetailer().toLowerCase().contains(filterPattern)))) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };



    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView brand, model, price;
        ImageView image;
        Button shop;
        ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            brand = (TextView) itemView.findViewById(R.id.brand);
            model = (TextView) itemView.findViewById(R.id.model);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.productImage);
            shop = (Button) itemView.findViewById(R.id.shop);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }

        public void onClick(final int position) {
            shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(products.get(position).getLink()));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }
            });
        }

    }
}
