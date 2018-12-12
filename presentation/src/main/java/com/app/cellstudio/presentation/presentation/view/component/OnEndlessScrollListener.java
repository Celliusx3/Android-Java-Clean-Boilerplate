package com.app.cellstudio.presentation.presentation.view.component;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by coyan on 12/12/2018.
 */

public abstract class OnEndlessScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = OnEndlessScrollListener.class.getSimpleName();

    /**
     * The total number of items in the dataset after the last load
     */
    private int previousTotal = 0;
    /**
     * True if we are still waiting for the last set of data to load.
     */
    private boolean loading = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        int visibleThreshold = 5;
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            onLoadMore();

            loading = true;
        }
    }

    public abstract void onLoadMore();
}