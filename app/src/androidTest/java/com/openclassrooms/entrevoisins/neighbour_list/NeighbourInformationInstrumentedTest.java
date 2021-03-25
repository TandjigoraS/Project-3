package com.openclassrooms.entrevoisins.neighbour_list;


import androidx.annotation.NonNull;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.matchers.ItemCount;
import com.openclassrooms.entrevoisins.matchers.MyViewAction;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NeighbourInformationInstrumentedTest {


    @NonNull
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    private int currentNeighbour;
    private static final int ITEM_LIST_NEIGHBOUR = 0;


    @Before
    public void setup() {

        NeighbourApiService service = DI.getNewInstanceApiService();
        currentNeighbour = service.getNeighbours().size();


    }


    @Test
    public void checkIfClickItemListNeighbourInformationIsDisplayed() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_LIST_NEIGHBOUR, MyViewAction.clickChildViewWithId(R.id.item_list_name)));
        onView(ViewMatchers.withId(R.id.activity_neighbour_information)).check(matches(isDisplayed()));

    }

    @Test
    public void checkNeighbourInformationActivityIsDisplayedNameOfNeighbourIsFilled() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_LIST_NEIGHBOUR, MyViewAction.clickChildViewWithId(R.id.item_list_name)));
        onView(withId(R.id.neighbour_information_textview_avatar_name_title)).check(matches(withText(containsString("Caroline"))));

    }



    @Test
    public void checkIfRemovingNeighbourIsWorking()  {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_LIST_NEIGHBOUR, MyViewAction.DeleteNeighbour(R.id.item_list_delete_button)));
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(new ItemCount(currentNeighbour - 1));

    }

    @Test
    public void checkFavoriteTabContainsOnlyNeighbourMarkedAsFavorite()  {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_LIST_NEIGHBOUR, MyViewAction.clickChildViewWithId(R.id.item_list_name)));
        onView(withId(R.id.neighbour_information_fab)).perform(click());
        onView(withId(R.id.activity_neighbour_information)).perform(pressBack());
        onView(withId(R.id.container)).perform(swipeLeft(), click());
        onView(allOf(ViewMatchers.withId(R.id.item_list_name), isDisplayed()))
                .check(matches(withText(containsString("Jack"))));

    }


}

