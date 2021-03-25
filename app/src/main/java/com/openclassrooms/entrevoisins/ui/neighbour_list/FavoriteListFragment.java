package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddNeighbourFavEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoriteListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NeighbourApiService mApiService;



    @NonNull
    public static FavoriteListFragment newInstance() {
        return new FavoriteListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        if(getContext() != null) {
            Context context = view.getContext();

            mRecyclerView = (RecyclerView) view;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        }
        return view;


    }


    private void initList() {
        List<Neighbour> mNeighbours = mApiService.getNeighboursFav();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours,1));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();

    }


    @Override
    public void onStart(){
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void AddNeighbourFav(AddNeighbourFavEvent event){

        mApiService.addNeighbourFav(event.mNeighbour);

    }

    @Subscribe
    public void DeleteNeighbourEvent(DeleteNeighbourEvent event){

        mApiService.deleteNeighbourFav(event.neighbour);
        initList();

    }

    @Subscribe
    public void DeleteNeighbourFavEvent(DeleteNeighbourFavEvent event){

        mApiService.deleteNeighbourFav(event.mNeighbour);
        initList();

    }

}
