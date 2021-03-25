package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddNeighbourFavEvent {

    public final Neighbour mNeighbour;


    public AddNeighbourFavEvent(Neighbour neighbour) {
         this.mNeighbour = neighbour;

    }

}
