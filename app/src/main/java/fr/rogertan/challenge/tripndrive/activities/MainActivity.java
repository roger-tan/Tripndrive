package fr.rogertan.challenge.tripndrive.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.rogertan.challenge.tripndrive.R;
import fr.rogertan.challenge.tripndrive.models.Site;
import fr.rogertan.challenge.tripndrive.services.TripndriveService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<List<Site>>, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner  locationSpinner;
//    private EditText locationEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private Button  searchButton;

    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private List<Site> mSites;

    private Date mStartDate;
    private Date mEndDate;
    private Site mSite;
    private ArrayAdapter<Site> mSitesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSites();
        findViewsById();
        setFields();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViewsById() {
        locationSpinner = (Spinner)findViewById(R.id.location_spinner);
//        locationEditText = (EditText)findViewById(R.id.search_site);
        startDateEditText = (EditText)findViewById(R.id.search_start_date);
        endDateEditText = (EditText)findViewById(R.id.search_end_date);
        searchButton = (Button)findViewById(R.id.search_rent_button);
    }

    private void setFields() {
        // Listeners
        locationSpinner.setOnItemSelectedListener(this);
        startDateEditText.setOnClickListener(this);
        endDateEditText.setOnClickListener(this);


        // Never show keyboards
//        locationEditText.setInputType(InputType.TYPE_NULL);
        startDateEditText.setInputType(InputType.TYPE_NULL);
        endDateEditText.setInputType(InputType.TYPE_NULL);

        startDateEditText.setInputType(InputType.TYPE_NULL);
        Calendar newCalendar = Calendar.getInstance();
        startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDateEditText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDateEditText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        startDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    startDatePickerDialog.show();
                } else {
                    startDatePickerDialog.hide();
                }
            }
        });

        endDateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    endDatePickerDialog.show();
                } else {
                    endDatePickerDialog.hide();
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RentsActivity.class);
                i.putExtra(RentsActivity.EXTRA_START_DATE, startDateEditText.getText().toString());
                i.putExtra(RentsActivity.EXTRA_END_DATE, endDateEditText.getText().toString());
                i.putExtra(RentsActivity.EXTRA_SITE, mSite);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == startDateEditText) {
            startDatePickerDialog.show();
        } else if (v == endDateEditText) {
            endDatePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == locationSpinner) {
            mSite = (Site)parent.getItemAtPosition(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadSites() {
        setProgressBarIndeterminateVisibility(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TripndriveService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TripndriveService.Tripndrive tripndrive = retrofit.create(TripndriveService.Tripndrive.class);

        Call<List<Site>> call = tripndrive.sites();

        call.enqueue(this);

    }


    @Override
    public void onResponse(Response<List<Site>> response, Retrofit retrofit) {
        setProgressBarIndeterminateVisibility(false);
        mSites = response.body();
        // Set Location Spinner
        mSitesAdapter = new ArrayAdapter<Site>(this, android.R.layout.simple_spinner_item, mSites);
        mSitesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(mSitesAdapter);

    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
