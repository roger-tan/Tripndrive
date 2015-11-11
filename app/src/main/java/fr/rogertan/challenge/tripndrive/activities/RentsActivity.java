package fr.rogertan.challenge.tripndrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fr.rogertan.challenge.tripndrive.R;
import fr.rogertan.challenge.tripndrive.models.Rent;
import fr.rogertan.challenge.tripndrive.models.Site;
import fr.rogertan.challenge.tripndrive.services.TripndriveService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RentsActivity extends AppCompatActivity implements Callback<List<Rent>> {

    public static final String EXTRA_SITE = "Site";
    public static final String EXTRA_START_DATE = "StartDate";
    public static final String EXTRA_END_DATE = "EndDate";


    private ListView mListView;

    private List<Rent> mRents;
    private ArrayAdapter<Rent> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewsById();
        setupHandlers();
        loadResults();

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
        mListView = (ListView)findViewById(R.id.list_result_rents);
    }

    private void setupHandlers() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Rent rent = (Rent)parent.getItemAtPosition(position);
                Intent i = new Intent(RentsActivity.this, RentDetailActivity.class);
                i.putExtra(RentDetailActivity.EXTRA_RENT, rent);
                startActivity(i);
            }
        });
    }

    private void loadResults() {
        Site site = (Site)getIntent().getSerializableExtra(EXTRA_SITE);
        String startDate = (String)getIntent().getSerializableExtra(EXTRA_START_DATE);
        String endDate = (String)getIntent().getSerializableExtra(EXTRA_END_DATE);

        setProgressBarIndeterminateVisibility(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TripndriveService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TripndriveService.Tripndrive tripndrive = retrofit.create(TripndriveService.Tripndrive.class);

        Call<List<Rent>> call = tripndrive.searchRents(startDate, "10:00", endDate, "10:30", site.getCode());

        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<List<Rent>> response, Retrofit retrofit) {
        setProgressBarIndeterminateVisibility(false);
        mRents = response.body();
        mArrayAdapter = new ArrayAdapter<Rent>(this, android.R.layout.simple_list_item_1, mRents);
        mListView.setAdapter(mArrayAdapter);
//        mSites = response.body();
//        // Set Location Spinner
//        mSitesAdapter = new ArrayAdapter<Site>(this, android.R.layout.simple_spinner_item, mSites);
//        mSitesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        locationSpinner.setAdapter(mSitesAdapter);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(RentsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }


}
