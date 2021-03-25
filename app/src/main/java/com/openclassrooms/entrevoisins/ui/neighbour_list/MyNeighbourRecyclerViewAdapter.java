package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private Intent mIntent;
    private final int mTab;


    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, int tab) {
        mNeighbours = items;
        mTab = tab;




    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        assert holder.mNeighbourName != null;
        holder.mNeighbourName.setText(neighbour.getName());
        assert holder.mNeighbourAvatar != null;
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mNeighbourName.setOnClickListener(view -> {
            mIntent = new Intent(view.getContext(), NeighbourInformationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("EXTRA_NEIGHBOUR", neighbour);
            mIntent.putExtras(bundle);
            view.getContext().startActivity(mIntent);

        });


        assert holder.mDeleteButton != null;
        holder.mDeleteButton.setOnClickListener(v -> {
                if (mTab == 0) {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                } else {
                    EventBus.getDefault().post(new DeleteNeighbourFavEvent(neighbour));
                }

            });


    }


    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @Nullable
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @Nullable
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }

}
