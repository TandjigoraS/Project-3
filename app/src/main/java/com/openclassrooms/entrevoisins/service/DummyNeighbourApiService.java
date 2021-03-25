package com.openclassrooms.entrevoisins.service;

import androidx.annotation.NonNull;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final List<Neighbour> neighboursFav = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @NonNull
    @Override
    public List<Neighbour> getNeighboursFav() {
        return neighboursFav;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }
    @Override
    public void deleteNeighbourFav(Neighbour neighbour) { neighboursFav.remove(neighbour); }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void addNeighbourFav(Neighbour neighbour) {  neighboursFav.add(neighbour); }



}

