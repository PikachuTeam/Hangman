package yue.self.hangman.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;

/**
 * Created by the_e_000 on 10/27/2015.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View buttonPlay;
    private View buttonVersus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(view);
        return view;
    }

    @Override
    protected boolean enableToolbar() {
        return false;
    }

    private void findViews(View rootView) {
        buttonPlay = rootView.findViewById(R.id.buttonPlay);
        buttonVersus = rootView.findViewById(R.id.buttonVersus);

        buttonPlay.setOnClickListener(this);
        buttonVersus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Handler handler = new Handler();
        if (v == buttonPlay) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    replaceFragment(new TopicFragment());
                }
            }, BaseFragment.DELAY_TIME);
        } else if (v == buttonVersus) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    replaceFragment(new PlayerFragment());
                }
            }, BaseFragment.DELAY_TIME);
        }
    }
}
