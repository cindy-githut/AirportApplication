package com.cindymb.airportapplication.adapter.CustomBindingAdapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.cindymb.airportapplication.utils.RecyclerViewConfig;

public class CustomBindingAdapter {

    @BindingAdapter("configuration")
    public static void bindRecyclerViewConfiguration(RecyclerView view, RecyclerViewConfig config) {
        if (config != null) {
            if (view.getAdapter() == null && config.getAdapter() != null) {
                view.setAdapter(config.getAdapter());
            }
            if (view.getLayoutManager() == null && config.getLayoutManager() != null) {
                view.setLayoutManager(config.getLayoutManager());
            }
        }
    }

}
