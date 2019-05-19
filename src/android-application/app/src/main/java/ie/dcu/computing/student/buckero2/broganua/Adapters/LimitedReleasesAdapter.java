package ie.dcu.computing.student.buckero2.broganua.Adapters;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ie.dcu.computing.student.buckero2.broganua.R;
import ie.dcu.computing.student.buckero2.broganua.Models.Releases;

public class LimitedReleasesAdapter extends RecyclerView.Adapter<LimitedReleasesAdapter.ReleaseViewHolder>{

    Context relcontext;
    ArrayList<Releases> releases;

    public LimitedReleasesAdapter(Context c, ArrayList<Releases> r)
    {
        relcontext = c;
        releases = r;
    }

    @NonNull
    @Override
    public LimitedReleasesAdapter.ReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LimitedReleasesAdapter.ReleaseViewHolder(LayoutInflater.from(relcontext).inflate(R.layout.release_card_view, viewGroup ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LimitedReleasesAdapter.ReleaseViewHolder myViewHolder, int i) {
        myViewHolder.profile.setText(releases.get(i).getIg_profile());
        myViewHolder.caption.setText(releases.get(i).getIg_caption());
        myViewHolder.onClick(i);
        Picasso.get().load(releases.get(i).getIg_image()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return releases.size();
    }

    class ReleaseViewHolder extends RecyclerView.ViewHolder
    {
        TextView profile, caption;
        ImageView image;
        Button igButton;
        public ReleaseViewHolder(View itemView) {
            super(itemView);
            profile = (TextView) itemView.findViewById(R.id.igProfile);
            caption = (TextView) itemView.findViewById(R.id.igCaption);
            image = (ImageView) itemView.findViewById(R.id.igImage);
            igButton = (Button) itemView.findViewById(R.id.igButton);

        }

        public void onClick(final int position) {
            igButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(releases.get(position).getIg_link()));
                    relcontext.startActivity(browserIntent);
                }
            });
        }

    }
}
