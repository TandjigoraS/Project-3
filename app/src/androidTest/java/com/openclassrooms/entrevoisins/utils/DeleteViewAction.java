package com.openclassrooms.entrevoisins.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;
import com.openclassrooms.entrevoisins.R;
import org.hamcrest.Matcher;

public class DeleteViewAction implements ViewAction {
    @Nullable
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, @NonNull View view) {
        View button = view.findViewById(R.id.item_list_delete_button);
        // Maybe check for null
        button.performClick();
    }
}