package com.venkyapps.airquality.features.airquality;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.venkyapps.airquality.R;
import com.venkyapps.airquality.features.airquality.model.OtherPollutants;
import com.venkyapps.airquality.features.airquality.model.Recommendation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by viviliya on 14-02-2017.
 */

public class OtherPollutantAdapter extends RecyclerView.Adapter<OtherPollutantAdapter.ViewHolder> implements OnClickListener {

    ArrayList<OtherPollutants> listOfOtherPollutants;

    public OtherPollutantAdapter(ArrayList<OtherPollutants> listOfOtherPollutants) {
        this.listOfOtherPollutants = listOfOtherPollutants;
    }

    @Override
    public OtherPollutantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pollutants, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OtherPollutantAdapter.ViewHolder holder, final int position) {

        holder.tvName.setText(listOfOtherPollutants.get(position).getname());
        holder.tvConcentration.setText(listOfOtherPollutants.get(position).getconcentration());
    }

    @Override
    public int getItemCount() {
        return listOfOtherPollutants.size();
    }

    @Override
    public void onClick(View view) {
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pollutant_name)
        TextView tvName;
        @BindView(R.id.tv_pollutant_concentration)
        TextView tvConcentration;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
