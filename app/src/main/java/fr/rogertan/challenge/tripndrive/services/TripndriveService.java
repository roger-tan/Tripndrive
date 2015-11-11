package fr.rogertan.challenge.tripndrive.services;

import java.util.List;

import fr.rogertan.challenge.tripndrive.models.Rent;
import fr.rogertan.challenge.tripndrive.models.Site;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by rogertan on 10/11/15.
 */

public final class TripndriveService {
    public static final String API_URL = "https://api.tripndrive.com/";

    public interface Tripndrive {
        @GET("sites")
        Call<List<Site>> sites();

        @GET("rent-results")
        Call<List<Rent>> searchRents(
                @Query("startDate") String startDate,
                @Query("startTime") String startTime,
                @Query("endDate") String endDate,
                @Query("endTime") String endTime,
                @Query("siteCode") String siteCode);

    }

}
