package com.app.cellstudio.presentation.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.databinding.ListItemRailBinding;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 04/12/2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_MOVIE = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    private boolean loading;

    private List<MoviePresentationModel> movies;
    private PublishSubject<MoviePresentationModel> selectedModel = PublishSubject.create();

    class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemRailBinding binding;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        ViewHolder(ListItemRailBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

         LoadingViewHolder(View itemView) {
            super(itemView);
         }
    }

    public MovieListAdapter(List<MoviePresentationModel> movies) {
        this.movies = movies;
    }

    public Observable<MoviePresentationModel> getSelectedMovie() {
        return selectedModel;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_LOADING:
                View v2 = layoutInflater.inflate(R.layout.loading_bar, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
            case VIEW_TYPE_MOVIE:
            default:
                ListItemRailBinding binding = DataBindingUtil
                        .inflate(layoutInflater, R.layout.list_item_rail, parent, false);
                viewHolder = new ViewHolder(binding);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder baseHolder, int position) {
        if (baseHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) baseHolder;
            holder.binding.setModel(movies.get(position));
            holder.binding.setListener(v -> {
                int pos = holder.getAdapterPosition();
                if (pos >= 0) {
                    selectedModel.onNext(movies.get(pos));
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        int size = movies.size();
        return loading ? size + 1 : size;
    }

    @Override
    public int getItemViewType(int position) {
        if (loading && position == getItemCount() - 1) {
            return  VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_MOVIE;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        if (loading) {
            notifyItemInserted(getItemCount() - 1);
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    public void updateData(List<MoviePresentationModel> models) {
        int start = this.movies.size();
        this.movies.addAll(models);
        int newItemCount = models.size();
        notifyItemRangeInserted(start, newItemCount);
    }
}
