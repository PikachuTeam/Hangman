package yue.self.hangman.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;
import yue.self.hangman.widget.Keys;
import yue.self.hangman.widget.Letter;
import yue.self.hangman.widget.NotifyDialog;

/**
 * Created by dongc_000 on 11/3/2015.
 */
public class AnswererFragment extends BaseFragment implements View.OnClickListener, Keys.OnKeysDownListener {

    private int currentQuestioner;
    private int currentAnswerer;
    private String player1;
    private String player2;
    private int score1;
    private int score2;
    private Letter[] letters;
    private Keys[] keyboard;
    private int lives;
    private String word;

    private ImageView hangmanImage;
    private LinearLayout guessArea;
    private LinearLayout topArea;
    private LinearLayout middleArea;
    private LinearLayout bottomArea;
    private NotifyDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answerer, container, false);
        findViews(view);
        initKeyboard();
        initLetters();
        changeImage();
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

    private void initData() {
        loadData();
        lives = 7;
    }

    private void initLetters() {
        int wordLength = word.length();
        letters = new Letter[wordLength];
        guessArea.removeAllViews();
        for (int i = 0; i < wordLength; i++) {
            letters[i] = new Letter(getBaseActivity().getApplicationContext());
            guessArea.addView(letters[i].getView());
            letters[i].setLetter("" + word.charAt(i));
            guessArea.invalidate();
        }
    }

    private void initKeyboard() {
        String[] keys = getResources().getStringArray(R.array.keyboard);

        keyboard = new Keys[keys.length];
        for (int i = 0; i < 10; i++) {
            keyboard[i] = new Keys(getBaseActivity().getApplicationContext());
            topArea.addView(keyboard[i].getView());
            keyboard[i].init(keys[i]);
            keyboard[i].setOnKeysDownListener(this);
        }
        for (int i = 10; i < 19; i++) {
            keyboard[i] = new Keys(getBaseActivity().getApplicationContext());
            middleArea.addView(keyboard[i].getView());
            keyboard[i].init(keys[i]);
            keyboard[i].setOnKeysDownListener(this);
        }
        for (int i = 19; i < keyboard.length; i++) {
            keyboard[i] = new Keys(getBaseActivity().getApplicationContext());
            bottomArea.addView(keyboard[i].getView());
            keyboard[i].init(keys[i]);
            keyboard[i].setOnKeysDownListener(this);
        }
        topArea.invalidate();
        middleArea.invalidate();
        bottomArea.invalidate();
    }

    private void changeImage() {
        switch (lives) {
            case 7:
                hangmanImage.setImageResource(R.drawable.hangman_1);
                break;
            case 6:
                hangmanImage.setImageResource(R.drawable.hangman_2);
                break;
            case 5:
                hangmanImage.setImageResource(R.drawable.hangman_3);
                break;
            case 4:
                hangmanImage.setImageResource(R.drawable.hangman_4);
                break;
            case 3:
                hangmanImage.setImageResource(R.drawable.hangman_5);
                break;
            case 2:
                hangmanImage.setImageResource(R.drawable.hangman_6);
                break;
            case 1:
                hangmanImage.setImageResource(R.drawable.hangman_7);
                break;
            case 0:
                hangmanImage.setImageResource(R.drawable.hangman_8);
                break;
        }
    }

    private void findViews(View rootView) {
        hangmanImage = (ImageView) rootView.findViewById(R.id.hangmanImage);
        guessArea = (LinearLayout) rootView.findViewById(R.id.guessArea);
        topArea = (LinearLayout) rootView.findViewById(R.id.topArea);
        middleArea = (LinearLayout) rootView.findViewById(R.id.middleArea);
        bottomArea = (LinearLayout) rootView.findViewById(R.id.bottomArea);
    }

    private void loadData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            currentQuestioner = bundle.getInt("Questioner");
            currentAnswerer = bundle.getInt("Answerer");
            player1 = bundle.getString("Player1");
            player2 = bundle.getString("Player2");
            word = bundle.getString("Word");
        }
    }

    @Override
    public void onClick(View v) {
        QuestionerFragment.update(score1, score2);
        getFragmentManager().popBackStack();
    }

    @Override
    public void onKeysDown(Keys keys) {

    }
}
