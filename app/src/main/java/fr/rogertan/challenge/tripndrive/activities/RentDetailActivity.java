package fr.rogertan.challenge.tripndrive.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.rogertan.challenge.tripndrive.R;
import fr.rogertan.challenge.tripndrive.models.Image;
import fr.rogertan.challenge.tripndrive.models.Rent;

public class RentDetailActivity extends AppCompatActivity {
    public static final String EXTRA_RENT = "Rent";

    private ImageView carImageView;
    private TextView modelNameTextView;

    private Rent mRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewsById();
        mRent = (Rent)getIntent().getSerializableExtra(EXTRA_RENT);
        setViews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void findViewsById() {
        carImageView = (ImageView)findViewById(R.id.rent_detail_image_view);
        modelNameTextView = (TextView)findViewById(R.id.rent_detail_model_name);
    }

    private void setViews() {
        Image img = mRent.getImages().get(0);
        Picasso.with(this).load(img.getLargeUrl()).into(carImageView);
        modelNameTextView.setText(mRent.getModelBrand() + " " + mRent.getModelName());
    }
}
