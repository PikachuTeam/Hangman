package yue.self.hangman.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;

/**
 * Created by dongc_000 on 11/3/2015.
 */
public class PlayerFragment extends BaseFragment implements View.OnClickListener {

    public static final String TRANSACTION_NAME = "Player Fragment";

    private EditText editTextPlayer1;
    private EditText editTextPlayer2;
    private View buttonStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View rootView) {
        editTextPlayer1 = (EditText) rootView.findViewById(R.id.editTextPlayer1);
        editTextPlayer2 = (EditText) rootView.findViewById(R.id.editTextPlayer2);
        buttonStart = rootView.findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(this);
    }

    @Override
    protected boolean enableButtonBack() {
        return true;
    }

    @Override
    public void onClick(View v) {
        QuestionerFragment fragment = new QuestionerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Player1", editTextPlayer1.getText().toString().trim());
        bundle.putString("Player2", editTextPlayer2.getText().toString().trim());
        fragment.setArguments(bundle);
        replaceFragment(getFragmentManager(), fragment, fragment.getClass().getName(), TRANSACTION_NAME);
    }
}
