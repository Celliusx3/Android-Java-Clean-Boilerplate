package com.app.cellstudio.presentation.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<MoviePresentationModel> movies;
    private PublishSubject<MoviePresentationModel> selectedModel = PublishSubject.create();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemRailBinding binding;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public ViewHolder(ListItemRailBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
    }

    public MovieListAdapter(List<MoviePresentationModel> movies) {
        this.movies = movies;
    }

    public Observable<MoviePresentationModel> getSelectedMovie() {
        return selectedModel;
    }


    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemRailBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_rail, parent, false);
        return new MovieListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MovieListAdapter.ViewHolder holder, int position) {
        holder.binding.setModel(movies.get(position));
        holder.binding.setListener(v -> {
            Log.d("Test", "Test");
            int pos = holder.getAdapterPosition();
            if (pos >= 0) {
                selectedModel.onNext(movies.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
