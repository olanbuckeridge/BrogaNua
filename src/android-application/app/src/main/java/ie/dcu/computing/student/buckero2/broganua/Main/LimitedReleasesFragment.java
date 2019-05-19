package ie.dcu.computing.student.buckero2.broganua;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReleasesFragment extends Fragment {
    /*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    } */

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Releases> releasesList;
    ReleaseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Load and display products
        recyclerView = (RecyclerView) view.findViewById(R.id.newsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        releasesList = new ArrayList<Releases>();

        // Retrieve products from Firebase.
        reference = FirebaseDatabase.getInstance().getReference().child("limited_releases");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Releases r = dataSnapshot1.getValue(Releases.class);
                    releasesList.add(r);
                }
                adapter = new ReleaseAdapter(getContext(), releasesList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
