package in.apollo.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.apollo.R;
import in.apollo.model.pojo.FlickerPojo;
import in.apollo.utils.GridSpacingItemDecoration;
import in.apollo.utils.OnClickListener;
import in.apollo.utils.SortByDateComparator;
import in.apollo.view.adapter.FlickrImgAdapter;
import in.apollo.viewmodel.gallery.GalleryViewModel;

public class FlickerGalleryFragment extends Fragment implements OnClickListener {
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private FlickrImgAdapter flickrImgAdapter;
    private GalleryViewModel galleryViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flickr_image, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));

        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                flickrImgAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
        observeViewModel(galleryViewModel);
    }

    private void observeViewModel(GalleryViewModel viewModel) {
        viewModel.getFlickrImageObservable().observe(this, new Observer<FlickerPojo>() {
            @Override
            public void onChanged(@Nullable FlickerPojo projects) {
                if (projects != null) {
                    setAdapter(projects);
                }
            }
        });
    }

    private void setAdapter(FlickerPojo projects) {
        Collections.sort(projects.getItems(), new SortByDateComparator());
        flickrImgAdapter = new FlickrImgAdapter(projects.getItems());
        flickrImgAdapter.registerLongPress(this);
        recyclerView.setAdapter(flickrImgAdapter);
    }

    @Override
    public void longPress(String url, String title) {
        final CharSequence[] items = {
                "Save", "Share"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        save();
                        break;
                    case 1:
                        shareTextUrl(url, title);
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void click(String url, String title) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void save() {

    }

    private void shareTextUrl(String url, String title) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEXT, url);

        startActivity(Intent.createChooser(share, "Share"));
    }
}
