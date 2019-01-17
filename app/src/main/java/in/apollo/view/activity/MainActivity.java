package in.apollo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import in.apollo.view.fragment.FlickerGalleryFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        addFragment();
    }

    private void addFragment() {
        FlickerGalleryFragment flickerGalleryFragment = new FlickerGalleryFragment();
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, flickerGalleryFragment).commit();
    }
}
