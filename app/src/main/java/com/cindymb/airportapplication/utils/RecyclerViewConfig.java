package com.cindymb.airportapplication.utils;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewConfig extends BaseObservable {
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setConfig(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        this.layoutManager = layoutManager;
        this.adapter = adapter;
        notifyChange();
    }
}