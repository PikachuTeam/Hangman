package yue.self.hangman.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;

/**
 * Created by dongc_000 on 10/28/2015.
 */
public class TopicFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        return view;
    }

    @Override
    protected boolean enableButtonBack() {
        return true;
    }
}
