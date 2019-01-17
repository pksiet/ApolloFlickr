package in.apollo.model.Network;


import in.apollo.model.pojo.FlickerPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static in.apollo.model.Network.WebUrls.URL_FLICKER_IMAGES;

public interface ApiInterface {
    @GET(URL_FLICKER_IMAGES)
    Call<FlickerPojo> getFlickrFeeds(@Query("format") String format, @Query("nojsoncallback") String nojsoncallback);
}
