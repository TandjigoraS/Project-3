package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteNeighbourFavEvent {

    public final Neighbour mNeighbour;

    public DeleteNeighbourFavEvent(Neighbour neighbour) {

        this.mNeighbour = neighbour;

    }
}
