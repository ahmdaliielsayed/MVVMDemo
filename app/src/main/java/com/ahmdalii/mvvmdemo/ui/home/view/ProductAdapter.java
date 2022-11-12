package com.ahmdalii.mvvmdemo.ui.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmdalii.mvvmdemo.R;
import com.ahmdalii.mvvmdemo.model.Products;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    ArrayList<Products> products = new ArrayList<>();

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTxtViewTitle().setText(context.getString(R.string.name).concat(" ").concat(products.get(position).getTitle()));
        holder.getTxtViewPrice().setText(context.getString(R.string.price).concat(" ").concat(String.valueOf(products.get(position).getPrice())));

        holder.getRatingBar().setStepSize(0.01f);
        holder.getRatingBar().setRating(products.get(position).getRating());
        holder.getRatingBar().setEnabled(false);

        Glide.with(context)
                .load(products.get(position).getProductImage())
                .into(holder.getIvPhoto());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataToAdapter(ArrayList<Products> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhoto;
        private TextView txtViewTitle, txtViewPrice;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TextView getTxtViewTitle() {
            if (txtViewTitle == null) {
                txtViewTitle = itemView.findViewById(R.id.txtViewTitle);
            }
            return txtViewTitle;
        }
        public TextView getTxtViewPrice() {
            if (txtViewPrice == null) {
                txtViewPrice = itemView.findViewById(R.id.txtViewPrice);
            }
            return txtViewPrice;
        }

        public ImageView getIvPhoto() {
            if (ivPhoto == null) {
                ivPhoto = itemView.findViewById(R.id.ivPhoto);
            }
            return ivPhoto;
        }

        public RatingBar getRatingBar() {
            if (ratingBar == null) {
                ratingBar = itemView.findViewById(R.id.ratingBar);
            }
            return ratingBar;
        }
    }
}
