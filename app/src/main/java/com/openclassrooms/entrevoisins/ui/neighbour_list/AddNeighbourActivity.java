package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNeighbourActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.avatar)
    ImageView avatar;
    @Nullable
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @Nullable
    @BindView(R.id.phoneNumberLyt)
    TextInputLayout phoneInput;
    @Nullable
    @BindView(R.id.addressLyt)
    TextInputLayout addressInput;
    @Nullable
    @BindView(R.id.aboutMeLyt)
    TextInputLayout aboutMeInput;
    @Nullable
    @BindView(R.id.create)
    MaterialButton addButton;

    private NeighbourApiService mApiService;
    private String mNeighbourImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_neighbour);
        ButterKnife.bind(this);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mApiService = DI.getNeighbourApiService();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        assert nameInput != null;
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(@NonNull Editable s) {
                assert addButton != null;
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick(R.id.create)
    void addNeighbour() {

        assert aboutMeInput != null;
        assert phoneInput != null;
        assert addressInput != null;
        assert nameInput != null;
        Neighbour neighbour = new Neighbour(
                System.currentTimeMillis(),
                nameInput.getEditText().getText().toString(),
                mNeighbourImage,
                addressInput.getEditText().getText().toString(),
                phoneInput.getEditText().getText().toString(),
                aboutMeInput.getEditText().getText().toString()
        );
        mApiService.createNeighbour(neighbour);
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    @NonNull
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(@NonNull FragmentActivity activity) {
        Intent intent = new Intent(activity, AddNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
