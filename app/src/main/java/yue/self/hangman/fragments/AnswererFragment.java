package yue.self.hangman.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;

/**
 * Created by dongc_000 on 11/3/2015.
 */
public class AnswererFragment extends BaseFragment implements View.OnClickListener {

    private int currentQuestioner;
    private int currentAnswerer;
    private Button buttonTouchMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answerer, container, false);
        findViews(view);
        return view;
    }

    @Override
    protected boolean enableButtonBack() {
        return true;
    }

    @Override
    protected void onBackPressed() {
        getFragmentManager().popBackStack(PlayerFragment.TRANSACTION_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void findViews(View rootView) {
        buttonTouchMe = (Button) rootView.findViewById(R.id.buttonTouchMe);
        buttonTouchMe.setOnClickListener(this);
    }

    private void loadData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            currentQuestioner = bundle.getInt("Questioner");
        }
    }

    @Override
    public void onClick(View v) {
        QuestionerFragment.update();
        getFragmentManager().popBackStack();
    }
}
