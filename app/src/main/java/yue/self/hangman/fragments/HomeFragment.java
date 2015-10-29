package yue.self.hangman.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;

/**
 * Created by the_e_000 on 10/27/2015.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View buttonPlay;

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
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonPlay) {
            replaceFragment(new TopicFragment());
        }
    }
}
