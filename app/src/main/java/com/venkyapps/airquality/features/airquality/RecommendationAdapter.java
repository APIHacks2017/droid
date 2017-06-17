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
import com.venkyapps.airquality.features.airquality.model.Recommendation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by viviliya on 14-02-2017.
 */

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> implements OnClickListener {

    ArrayList<Recommendation> listOfRecommendation;
    Context context;

    public RecommendationAdapter(Context context, ArrayList<Recommendation> listOfRecommendation) {
        this.context = context;
        this.listOfRecommendation = listOfRecommendation;
    }

    @Override
    public RecommendationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommendations, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecommendationAdapter.ViewHolder holder, final int position) {

        String title = listOfRecommendation.get(position).getTitle();
        int imageSrc = R.drawable.ic_health;

        holder.tvTitle.setText(title);
        holder.tvDescription.setText(listOfRecommendation.get(position).getMessage());
        if (title.equalsIgnoreCase("children")) {
            imageSrc = R.drawable.ic_children;
        } else if (title.equalsIgnoreCase("sport")) {
            imageSrc = R.drawable.ic_sport;
        } else if (title.equalsIgnoreCase("inside")) {
            imageSrc = R.drawable.ic_inside;
        } else if (title.equalsIgnoreCase("outside")) {
            imageSrc = R.drawable.ic_outside;
        }
        Glide.with(context).load(imageSrc).into(holder.imvImage);
    }

    @Override
    public int getItemCount() {
        return listOfRecommendation.size();
    }

    @Override
    public void onClick(View view) {
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_recommendation_title)
        TextView tvTitle;
        @BindView(R.id.tv_recommendation_description)
        TextView tvDescription;
        @BindView(R.id.imv_recommendation)
        ImageView imvImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
