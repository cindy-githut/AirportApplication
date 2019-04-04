package com.cindymb.airportapplication.ui.schedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cindymb.airportapplication.databinding.ItemFlightScheduleBinding;
import com.cindymb.airportapplication.ui.schedule.model.FlightScheduleModel;

import java.util.ArrayList;
import java.util.List;


public class FlightScheduleAdapter extends RecyclerView.Adapter<FlightScheduleAdapter.FlightScheduleViewHolder> {

    private final List<FlightScheduleModel> mFlightScheduleModels = new ArrayList<>();

    @NonNull
    @Override
    public FlightScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFlightScheduleBinding binding = ItemFlightScheduleBinding.inflate(inflater, parent, false);
        return new FlightScheduleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightScheduleViewHolder holder, int position) {
        holder.bind(mFlightScheduleModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mFlightScheduleModels.size();
    }

    void setFlightScheduleModels(List<FlightScheduleModel> aFlightScheduleModels) {
        if (aFlightScheduleModels == null) return;
        mFlightScheduleModels.clear();
        mFlightScheduleModels.addAll(aFlightScheduleModels);
        notifyDataSetChanged();
    }

    class FlightScheduleViewHolder extends RecyclerView.ViewHolder {
        ItemFlightScheduleBinding mBinding;

        FlightScheduleViewHolder(ItemFlightScheduleBinding aBinding) {
            super(aBinding.getRoot());
            mBinding = aBinding;
        }

        public void bind(FlightScheduleModel aFlightScheduleModel) {
            mBinding.setFlightScheduleModel(aFlightScheduleModel);
        }
    }
}
