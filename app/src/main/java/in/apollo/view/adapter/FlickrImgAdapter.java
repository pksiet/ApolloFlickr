package in.apollo.view.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import in.apollo.R;
import in.apollo.model.pojo.FlickerItemPojo;
import in.apollo.utils.OnClickListener;
import in.apollo.view.main.ApolloApplication;

public class FlickrImgAdapter extends RecyclerView.Adapter<FlickrImgAdapter.FlickrItemViewHolder> implements Filterable {
    private List<FlickerItemPojo> flickerItemList;
    private List<FlickerItemPojo> flickerItemFilterList;
    private FlickrItemFilter flickrItemFilter;
    private OnClickListener onLongPressListener;

    public FlickrImgAdapter(List<FlickerItemPojo> flickerItemList){
        this.flickerItemList = flickerItemList;
        this.flickerItemFilterList = flickerItemList;
    }

    @NonNull
    @Override
    public FlickrImgAdapter.FlickrItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_flikr_items, null, false);

        return new FlickrItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImgAdapter.FlickrItemViewHolder myViewHolder, int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);
        requestOptions.error(R.mipmap.ic_launcher);
        Glide.with(ApolloApplication.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(flickerItemFilterList.get(position).getMedia().getM()).into(myViewHolder.flickerImageIV);
        myViewHolder.titleTV.setText(flickerItemFilterList.get(position).getTitle());

        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onLongPressListener != null){
                    onLongPressListener.click(flickerItemFilterList.get(position).getMedia().getM(), flickerItemFilterList.get(position).getTitle());
                }
            }
        });

        myViewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onLongPressListener != null){
                    onLongPressListener.longPress(flickerItemFilterList.get(position).getMedia().getM(), flickerItemFilterList.get(position).getTitle());
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return flickerItemFilterList != null ? flickerItemFilterList.size() : 0;
    }

    @Override
    public Filter getFilter() {
        if (flickrItemFilter == null) {
            flickrItemFilter = new FlickrItemFilter();
        }
        return flickrItemFilter;
    }

    public void registerLongPress(OnClickListener onLongPressListener) {
        this.onLongPressListener = onLongPressListener;
    }

    private class FlickrItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<FlickerItemPojo> filterList = new ArrayList<>();
                for (int i = 0; i < flickerItemList.size(); i++) {
                    if ((flickerItemList.get(i).getTags().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(flickerItemList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = flickerItemList.size();
                results.values = flickerItemList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            flickerItemFilterList = (List<FlickerItemPojo>) results.values;
            notifyDataSetChanged();
        }

    }

    public class FlickrItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTV;
        public ImageView flickerImageIV;
        private View view;

        public FlickrItemViewHolder(View view) {
            super(view);
            this.view = view;
            titleTV = (TextView) view.findViewById(R.id.tv_title);
            flickerImageIV = (ImageView) view.findViewById(R.id.iv_flickr);
        }
    }
}
