package com.mfathy.ladelicerestaurant.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfathy.ladelicerestaurant.R;
import com.mfathy.ladelicerestaurant.view.model.HomeItem;

import java.util.List;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 *
 * {@link HomeItemsAdapter} is the adapter for {@link com.mfathy.ladelicerestaurant.view.HomeActivity} recyclerView.
 */
public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsAdapter.ViewHolder> {

    private OnHomeItemInteractionListener mListener;
    private List<HomeItem> itemList;
    private int itemImageHeight = 0;
    private Context mContext;

    // Prevents user clicks while Collapsing or Expanding Toolbar.
    private boolean isScrollFinished = false;

    public HomeItemsAdapter(List<HomeItem> itemList, Context context, OnHomeItemInteractionListener listener) {
        this.itemList = itemList;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(inflater.inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeItem item = itemList.get(position);
        holder.itemTitle.setText(item.getItemName().toUpperCase());
        holder.itemDescription.setText(item.getItemDescription());
        holder.itemImage.setImageResource(item.getImageResources()[0]);

        //  Adjust image itemImageHeight based on AppBarLayout Scroll.
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.itemImage.getLayoutParams();
        params.height = itemImageHeight;
        holder.itemImage.requestLayout();

        ViewCompat.setTransitionName(holder.itemTitle, mContext.getString(R.string.item_title) + position);
        ViewCompat.setTransitionName(holder.itemImage, mContext.getString(R.string.item_image) + position);
        ViewCompat.setTransitionName(holder.itemDescription, mContext.getString(R.string.item_description) + position);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * This method adjust image itemImageHeight based on AppBarLayout Scroll.
     * @param newHeight is the new image height.
     * @param isEnd indicates that animation is ended.
     */
    public void changeImageWidth(int newHeight, boolean isEnd) {
        itemImageHeight = newHeight;
        isScrollFinished = isEnd;

        notifyDataSetChanged();
    }

    public interface OnHomeItemInteractionListener {
        void onItemInteraction(HomeItem homeItem, View itemView, int adapterPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDescription;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDescription = itemView.findViewById(R.id.item_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (isScrollFinished) {
                mListener.onItemInteraction(itemList.get(getAdapterPosition()), itemView, getAdapterPosition());
            }
        }
    }
}
