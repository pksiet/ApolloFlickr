package in.apollo.view.main;

import android.app.Application;
import android.content.Context;
import android.view.View;

public class ApolloApplication extends Application {
    private static ApolloApplication sApolloApplication;
    public static Context getContext() {
        return sApolloApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApolloApplication = this;
    }
}
