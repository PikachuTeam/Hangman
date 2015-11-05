package yue.self.hangman.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;
import yue.self.hangman.database.DataSource;

/**
 * Created by dongc_000 on 10/28/2015.
 */
public class TopicFragment extends BaseFragment {

    private ArrayList<String> topics;
    private TextView textViewTopics;
    private View testTopic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        findViews(view);
        String temp = "";
        for (int i = 0; i < topics.size(); i++) {
            temp += topics.get(i) + " ";
        }
        textViewTopics.setText(temp);
        return view;
    }

    private void init() {
        topics = DataSource.getInstance().getTopics();
    }

    private void findViews(View rootView) {
        textViewTopics = (TextView) rootView.findViewById(R.id.textViewTopics);
        testTopic = rootView.findViewById(R.id.testButton);

        testTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayFragment playFragment = new PlayFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Topic", "animals");
                playFragment.setArguments(bundle);
                replaceFragment(playFragment);
            }
        });
    }

    @Override
    protected boolean enableButtonBack() {
        return true;
    }
}
