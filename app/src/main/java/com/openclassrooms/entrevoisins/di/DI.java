package com.openclassrooms.entrevoisins.di;

import androidx.annotation.NonNull;

import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final NeighbourApiService service = new DummyNeighbourApiService();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return service
     */
    @NonNull
    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return service
     */
    @NonNull
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}
