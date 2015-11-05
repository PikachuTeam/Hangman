package yue.self.hangman.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;

/**
 * Created by dongc_000 on 11/3/2015.
 */
public class QuestionerFragment extends BaseFragment implements View.OnClickListener {

    public final static int PLAYER_1 = 1, PLAYER_2 = 2;

    private static int currentQuestioner;
    private static int currentAnswerer;
    private static int score1;
    private static int score2;
    private EditText editTextWord;
    private TextView textViewTest;
    private String player1;
    private String player2;
    private Button buttonOk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
        currentQuestioner = PLAYER_1;
        currentAnswerer = PLAYER_2;
        score1 = 0;
        score2 = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questioner, container, false);
        findViews(view);
        if (currentQuestioner == PLAYER_1) {
            textViewTest.setText("Player 1");
        } else {
            textViewTest.setText("Player 2");
        }
        return view;
    }

    private void loadData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            player1 = bundle.getString("Player1");
            player2 = bundle.getString("Player2");
        }
    }

    @Override
    protected boolean enableButtonBack() {
        return true;
    }

    private void findViews(View rootView) {
        editTextWord = (EditText) rootView.findViewById(R.id.editTextWord);
        textViewTest = (TextView) rootView.findViewById(R.id.textViewTest);
        buttonOk = (Button) rootView.findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(this);
    }

    public static void update(int playerScore1, int playerScore2) {
        switch (currentAnswerer) {
            case PLAYER_1:
                currentQuestioner = PLAYER_1;
                currentAnswerer = PLAYER_2;
                break;
            case PLAYER_2:
                currentQuestioner = PLAYER_2;
                currentAnswerer = PLAYER_1;
                break;
        }
        score1 = playerScore1;
        score2 = playerScore2;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putInt("Questioner", currentQuestioner);
        bundle.putInt("Answerer", currentAnswerer);
        bundle.putInt("Score1", score1);
        bundle.putInt("Score2", score2);
        bundle.putString("Player1", player1);
        bundle.putString("Player2", player2);
        bundle.putString("Word", editTextWord.getText().toString().toUpperCase().trim());
        replaceFragmentWithAnim(new AnswererFragment(), R.anim.card_flip_left_in, R.anim.card_flip_left_out, R.anim.card_flip_right_in, R.anim.card_flip_right_out, bundle);
    }
}
