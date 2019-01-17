package in.apollo.viewmodel.gallery;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;


import in.apollo.model.Network.RetrofitClient;
import in.apollo.model.pojo.FlickerPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryViewModel extends AndroidViewModel {
    private MutableLiveData<FlickerPojo> flickrImg = new MutableLiveData<>();

    public GalleryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<FlickerPojo> getFlickrImageObservable() {
        RetrofitClient.instance().getFlickrFeeds("json", "1").enqueue(new Callback<FlickerPojo>() {
            @Override
            public void onResponse(Call<FlickerPojo> call, Response<FlickerPojo> response) {
                FlickerPojo flickerPojo = response.body();
                flickrImg.setValue(flickerPojo);
            }

            @Override
            public void onFailure(Call<FlickerPojo> call, Throwable t) {
                flickrImg.setValue(null);
            }
        });
        return flickrImg;
    }
}
