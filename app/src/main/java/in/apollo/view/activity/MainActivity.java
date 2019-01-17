package in.apollo.view.activity;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import in.apollo.utils.Constant;
import in.apollo.view.fragment.FlickerGalleryFragment;

/**
 * Main Launcher class
 */
public class MainActivity extends AppCompatActivity {

    private FlickerGalleryFragment flickerGalleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        addGalleryFragment();
    }

    /**
     * Add Gallery fragment to Activity content
     */
    private void addGalleryFragment() {
        flickerGalleryFragment = new FlickerGalleryFragment();
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, flickerGalleryFragment).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isPermissionAccepted = false;
        switch(requestCode){
            case Constant.REQUEST_CODE_WRITE_STORAGE_PERMISSION:
                isPermissionAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;

        }

        if(flickerGalleryFragment != null) {
            flickerGalleryFragment.permissionActionDone(isPermissionAccepted);
        }
    }
}
