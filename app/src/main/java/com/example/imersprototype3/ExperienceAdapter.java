package com.example.imersprototype3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ExperienceAdapter extends FirestoreRecyclerAdapter<Experience, ExperienceAdapter.ExperienceHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ExperienceAdapter(@NonNull FirestoreRecyclerOptions<Experience> options) {
        super(options);
    }

    @NonNull
    @Override
    public ExperienceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ExperienceHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExperienceHolder holder, int position, @NonNull Experience model) {
        holder.name.setText(Experience.getTitle());
        //TODO: 5/16/2021 get thumbnail image here somehow, if we decide on thumbnails.

    }

    static class ExperienceHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ExperienceHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail);
            name = itemView.findViewById(R.id.name);
        }
    }
}
