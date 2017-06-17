package com.venkyapps.airquality.features.airquality;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.venkyapps.airquality.R;
import com.venkyapps.airquality.features.airquality.model.AirQualityResponse;
import com.venkyapps.airquality.features.airquality.model.Co;
import com.venkyapps.airquality.features.airquality.model.Nh3;
import com.venkyapps.airquality.features.airquality.model.No2;
import com.venkyapps.airquality.features.airquality.model.O3;
import com.venkyapps.airquality.features.airquality.model.OtherPollutants;
import com.venkyapps.airquality.features.airquality.model.Pm10;
import com.venkyapps.airquality.features.airquality.model.Pm25;
import com.venkyapps.airquality.features.airquality.model.Pollutants;
import com.venkyapps.airquality.features.airquality.model.RandomRecommendations;
import com.venkyapps.airquality.features.airquality.model.Recommendation;
import com.venkyapps.airquality.features.airquality.model.So2;
import com.venkyapps.airquality.helpers.ApiProgressDialog;
import com.venkyapps.airquality.helpers.MyConstants;
import com.venkyapps.airquality.helpers.MyDateUtils;
import com.venkyapps.airquality.helpers.MyUtils;
import com.venkyapps.airquality.network.ApiClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Context context;

    @BindView(R.id.imv_toolbar_image)
    ImageView imvToolbarImage;
    @BindView(R.id.rv_recommendation)
    RecyclerView rvRecommendation;
    ApiProgressDialog apiProgressDialog;

    @BindView(R.id.coordinator_main)
    CoordinatorLayout coordinatorLayoutMain;
    @BindView(R.id.tv_aqi_number)
    TextView tvAqiNumber;
    @BindView(R.id.tv_aqi_description)
    TextView tvAqiDescription;
    @BindView(R.id.tv_aqi_time)
    TextView tvAqiTime;
    @BindView(R.id.tv_country_aqi_description)
    TextView tvCountryAqiDescription;
    @BindView(R.id.tv_country_name)
    TextView tvCountryName;
    @BindView(R.id.tv_country_aqi)
    TextView tvCountryAqi;
    @BindView(R.id.tv_lat_long)
    TextView tvLatLong;
    @BindView(R.id.tv_dominant_pollutant_details)
    TextView tvDominantPollutantDetails;

    @BindView(R.id.tv_dominant_pollutant_main)
    TextView tvDominantPollutantMain;
    @BindView(R.id.tv_dominant_pollutant_causes)
    TextView tvDominantPollutantCauses;
    @BindView(R.id.tv_dominant_pollutant_effects)
    TextView tvDominantPollutantEffects;
    @BindView(R.id.ll_other_pollutants)
    LinearLayout llOtherPollutants;
    @BindView(R.id.rv_other_pollutant)
    RecyclerView rvOtherPollutants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiProgressDialog = new ApiProgressDialog(context);

        rvRecommendation.setLayoutManager(new LinearLayoutManager(context));
        rvRecommendation.setNestedScrollingEnabled(false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvOtherPollutants.getContext(),
                DividerItemDecoration.HORIZONTAL);
        rvOtherPollutants.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvOtherPollutants.addItemDecoration(dividerItemDecoration);
        rvOtherPollutants.setNestedScrollingEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Double lat = 12.985888;
        Double lon = 80.246176;
        loadAirQualityApi(lat, lon);
    }

    private void loadAirQualityApi(final Double lat, final Double lon) {
        apiProgressDialog.show(null, "Getting air quality..");
        final Retrofit retrofit = new ApiClient().initializeRetrofit();
        new ApiClient().getClient(retrofit).getAirQualityValues(lat.toString(), lon.toString(), MyConstants.API_KEY).enqueue(new Callback<AirQualityResponse>() {
            @Override
            public void onResponse(Call<AirQualityResponse> call, Response<AirQualityResponse> response) {
                apiProgressDialog.dismiss();
                if (response.isSuccessful()) {

                    loadAirQualityResponse(response.body(), lat, lon);
                } else {
                    MyUtils.handleResponseCode(coordinatorLayoutMain, retrofit, response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<AirQualityResponse> call, Throwable t) {
                apiProgressDialog.dismiss();
                MyUtils.handleRetrofitFailure(coordinatorLayoutMain, t);
            }
        });

    }

    private void loadAirQualityResponse(AirQualityResponse response, Double lat, Double lon) {

        Integer aqiLevel = response.getBreezometerAqi();

        if (aqiLevel < 40) {
            Glide.with(context).load(R.drawable.aqi_fair).into(imvToolbarImage);
        } else if (aqiLevel < 70) {
            Glide.with(context).load(R.drawable.aqi_moderate).into(imvToolbarImage);
        } else {
            Glide.with(context).load(R.drawable.aqi_good).into(imvToolbarImage);
        }
        tvAqiNumber.setText("" + aqiLevel);

        tvAqiNumber.setTextColor(Color.parseColor(response.getBreezometerColor()));

        tvAqiDescription.setText(response.getBreezometerDescription());
        tvAqiTime.setText(MyDateUtils.getBrezometerAqiTime(response.getDatetime()));

        tvCountryAqi.setText("AQI: " + response.getCountryAqi());
        tvCountryAqi.setTextColor(Color.parseColor(response.getCountryColor()));
        tvCountryAqiDescription.setText(response.getCountryDescription());
        tvCountryName.setText(response.getCountryName());

        tvLatLong.setText("Lat: " + lat + "\n Long: " + lon);

        loadRandomRecommendations(response.getRandomRecommendations());

        if (response.getDominantPollutantCanonicalName() != null) {
            tvDominantPollutantDetails.setText(response.getDominantPollutantCanonicalName() + " " + response.getDominantPollutantDescription());
            tvDominantPollutantMain.setText("Main: " + response.getDominantPollutantText().getMain());
            tvDominantPollutantCauses.setText("Causes: " + response.getDominantPollutantText().getCauses());
            tvDominantPollutantEffects.setText("Effects: " + response.getDominantPollutantText().getEffects());
        }
        if (response.getPollutants() != null) {
            llOtherPollutants.setVisibility(View.VISIBLE);
            loadOtherPollutants(response.getPollutants());
        } else {
            llOtherPollutants.setVisibility(View.GONE);
        }

    }

    private void loadOtherPollutants(Pollutants otherPollutants) {
        ArrayList<OtherPollutants> listOfOtherPollutants = new ArrayList<>();
        Co co = otherPollutants.getCo();
        Nh3 nh3 = otherPollutants.getNh3();
        O3 o3 = otherPollutants.getO3();
        No2 no2 = otherPollutants.getNo2();
        Pm10 pm10 = otherPollutants.getPm10();
        Pm25 pm25 = otherPollutants.getPm25();
        So2 so2 = otherPollutants.getSo2();

        if (co != null) {
            listOfOtherPollutants.add(new OtherPollutants("Co", getConcentrationWithUnits(co.getConcentration(), co.getUnits())));
        }

        if (nh3 != null) {
            listOfOtherPollutants.add(new OtherPollutants("Nh3", getConcentrationWithUnits(nh3.getConcentration(), nh3.getUnits())));
        }

        if (o3 != null) {
            listOfOtherPollutants.add(new OtherPollutants("O3", getConcentrationWithUnits(o3.getConcentration(), o3.getUnits())));
        }

        if (no2 != null) {
            listOfOtherPollutants.add(new OtherPollutants("No2", getConcentrationWithUnits(no2.getConcentration(), no2.getUnits())));
        }

        if (pm10 != null) {
            listOfOtherPollutants.add(new OtherPollutants("Pm10", getConcentrationWithUnits(pm10.getConcentration(), pm10.getUnits())));
        }

        if (pm25 != null) {
            listOfOtherPollutants.add(new OtherPollutants("Pm25", getConcentrationWithUnits(pm25.getConcentration(), pm25.getUnits())));
        }

        if (so2 != null) {
            listOfOtherPollutants.add(new OtherPollutants("So2", getConcentrationWithUnits(so2.getConcentration(), so2.getUnits())));
        }

        rvOtherPollutants.setAdapter(new OtherPollutantAdapter(listOfOtherPollutants));

    }


    private void loadRandomRecommendations(RandomRecommendations randomRecommendations) {
        ArrayList<Recommendation> listOfRecommendation = new ArrayList<>();
        if (randomRecommendations.getChildren() != null) {
            listOfRecommendation.add(new Recommendation("Children", randomRecommendations.getChildren()));
        }
        if (randomRecommendations.getSport() != null) {
            listOfRecommendation.add(new Recommendation("Sport", randomRecommendations.getSport()));
        }
        if (randomRecommendations.getHealth() != null) {
            listOfRecommendation.add(new Recommendation("Health", randomRecommendations.getHealth()));
        }
        if (randomRecommendations.getInside() != null) {
            listOfRecommendation.add(new Recommendation("Inside", randomRecommendations.getInside()));
        }
        if (randomRecommendations.getOutside() != null) {
            listOfRecommendation.add(new Recommendation("Outside", randomRecommendations.getOutside()));
        }

        rvRecommendation.setAdapter(new RecommendationAdapter(context, listOfRecommendation));

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

    public String getConcentrationWithUnits(Float concentration, String units) {
        return Math.round(concentration) + " " + units;
    }
}
