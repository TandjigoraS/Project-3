package com.openclassrooms.entrevoisins.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */

public class Neighbour implements Serializable {


    /** Identifier */
    private final long id;

    /** Full name */
    private final String name;

    /** Avatar */
    private final String avatarUrl;

    /** Address */
    private final String address;

    /** Phone number */
    private final String phoneNumber;

    /** About me */
    private final String aboutMe;



    /**
     *
     * {@inheritDoc}
     *
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     */

    public Neighbour(long id, String name, String avatarUrl, String address,
                     String phoneNumber, String aboutMe) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;

    }



    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }



    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
