package ie.dcu.computing.student.buckero2.broganua.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import ie.dcu.computing.student.buckero2.broganua.Adapters.CatalogueAdapter;
import ie.dcu.computing.student.buckero2.broganua.Models.Products;
import ie.dcu.computing.student.buckero2.broganua.R;

public class CatalogueFragment extends Fragment{

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Products> productsList;
    CatalogueAdapter adapter;
    ProgressBar progressBar;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogue, container, false);
        setHasOptionsMenu(true);

        // Load and display products
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) view.findViewById(R.id.catalogRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productsList = new ArrayList<Products>();


        // Retrieve products from Firebase.
        reference = FirebaseDatabase.getInstance().getReference().child("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Products p = dataSnapshot1.getValue(Products.class);
                    productsList.add(p);
                }
                //Collections.shuffle(productsList);
                adapter = new CatalogueAdapter(getContext(), productsList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_discovery:
                Collections.shuffle(productsList);
                adapter = new CatalogueAdapter(getContext(), productsList);
                recyclerView.setAdapter(adapter);
        }

        return super.onOptionsItemSelected(item);
    }
}
