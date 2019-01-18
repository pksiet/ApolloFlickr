package in.apollo.model.Network;


import in.apollo.model.pojo.FlickerPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static in.apollo.model.Network.WebUrls.URL_FLICKER_IMAGES;

/**
 * To make an API call using retrofit we need a java interface
 * where we define all the URLs with the http request type and parameters
 */

public interface ApiInterface {
    @GET(URL_FLICKER_IMAGES)
    Call<FlickerPojo> getFlickrFeeds(@Query("format") String format, @Query("nojsoncallback") String nojsoncallback);
}
