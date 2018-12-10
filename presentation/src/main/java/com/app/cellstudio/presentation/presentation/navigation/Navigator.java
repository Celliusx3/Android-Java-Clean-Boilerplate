package com.app.cellstudio.presentation.presentation.navigation;

/**
 * Created by coyan on 30/11/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.presentation.view.activity.MainActivity;
import com.app.cellstudio.presentation.presentation.view.activity.MovieDetailsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    private static final String TAG = Navigator.class.getSimpleName();

    @Inject
    public Navigator() {
    }

    public static void navigateToYoutube(Context context, String url){
        if (context != null) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            context.startActivity(webIntent);
        }
    }

    public void navigateToMain(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToMovieDetails(Context context, MoviePresentationModel movie) {
        if (context != null) {
            Intent intentToLaunch = MovieDetailsActivity.getCallingIntent(context, movie);
            context.startActivity(intentToLaunch);
        }
    }

    public void showPendingTransitions(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void showPendingTransitionFadeInFadeOut(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showPendingTransitionsSlideInRightFadeOut(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
    }

    public void showPendingTransitionModalIn(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_bottom, android.R.anim.fade_out);
    }

    public void showPendingTransitionModalOut(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, R.anim.slide_out_bottom);
    }
}