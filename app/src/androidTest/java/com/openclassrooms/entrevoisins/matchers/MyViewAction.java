package com.openclassrooms.entrevoisins.matchers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class MyViewAction {

    @NonNull
    public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {

                @Nullable
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @NonNull
                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, @NonNull View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }
    @NonNull
    public static ViewAction DeleteNeighbour(final int id) {
        return new ViewAction() {
            @Nullable
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @NonNull
            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, @NonNull View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    }

