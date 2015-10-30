package yue.self.hangman.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Random;

import yue.self.hangman.R;
import yue.self.hangman.app.BaseFragment;
import yue.self.hangman.database.DataSource;
import yue.self.hangman.entity.Question;
import yue.self.hangman.widget.Keys;
import yue.self.hangman.widget.Letter;
import yue.self.hangman.widget.NotifyDialog;

/**
 * Created by the_e_000 on 10/22/2015.
 */
public class PlayFragment extends BaseFragment implements Keys.OnKeysDownListener, View.OnClickListener, NotifyDialog.OnNotifyDialogButtonClickListener {

    private Letter[] letters;
    private Keys[] keyboard;
    private int lives;
    private int guessedWord;
    private Question question;
    private String topic;
    private Random random;
    private ImageView hangmanImage;
    private Button buttonHint;
    private Button buttonShowLetter;
    private TextView textViewHint;
    private TextView textViewTopic;
    private TextView textViewScore;
    private LinearLayout guessArea;
    private LinearLayout topArea;
    private LinearLayout middleArea;
    private LinearLayout bottomArea;
    private NotifyDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        findViews(view);
        init();
        return view;
    }

    private void init() {
        lives = 7;
        guessedWord = 0;
        getData();

        textViewTopic.setText(MessageFormat.format(getString(R.string.topic), topic));
        initWord();
        initKeyboard();
        hangmanImage.setImageResource(R.drawable.hangman_1);

        random = new Random();

        dialog = new NotifyDialog(getBaseActivity());
        dialog.setOnNotifyDialogButtonClickListener(this);
    }

    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            topic = bundle.getString("Topic");
        }
    }

    private void findViews(View rootView) {
        hangmanImage = (ImageView) rootView.findViewById(R.id.hangmanImage);
        buttonHint = (Button) rootView.findViewById(R.id.buttonHint);
        buttonShowLetter = (Button) rootView.findViewById(R.id.buttonShowLetter);
        textViewHint = (TextView) rootView.findViewById(R.id.textViewHint);
        textViewTopic = (TextView) rootView.findViewById(R.id.textViewTopic);
        textViewScore = (TextView) rootView.findViewById(R.id.textViewScore);
        guessArea = (LinearLayout) rootView.findViewById(R.id.guessArea);
        topArea = (LinearLayout) rootView.findViewById(R.id.topArea);
        middleArea = (LinearLayout) rootView.findViewById(R.id.middleArea);
        bottomArea = (LinearLayout) rootView.findViewById(R.id.bottomArea);

        buttonHint.setOnClickListener(this);
        buttonShowLetter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonHint) {
            textViewHint.setVisibility(View.VISIBLE);
        } else if (v == buttonShowLetter) {
            int i = 0;
            do {
                i = random.nextInt(letters.length);
            } while (letters[i].isShowed);
            letters[i].showLetter();
            if (isPassed()) {
                dialog.show(2, question.getWord());
            }
        }
    }

    private void initWord() {
        question = DataSource.getInstance().getQuestion(topic);
        textViewHint.setText(question.getHint());
        textViewScore.setText(MessageFormat.format(getString(R.string.score), "" + guessedWord));

        int wordLength = question.getWord().length();
        letters = new Letter[wordLength];
        guessArea.removeAllViews();
        for (int i = 0; i < wordLength; i++) {
            letters[i] = new Letter(getBaseActivity().getApplicationContext());
            guessArea.addView(letters[i].getView());
            letters[i].setLetter("" + question.getWord().charAt(i));
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

    private void moveToNext() {
        reset();
        guessedWord++;
        textViewScore.setText(MessageFormat.format(getString(R.string.score), "" + guessedWord));
    }

    private void reset() {
        lives = 7;
        initWord();
        hangmanImage.setImageResource(R.drawable.hangman_1);
        textViewHint.setVisibility(View.GONE);
        resetKeyboard();
    }

    private void resetKeyboard() {
        for (int i = 0; i < keyboard.length; i++) {
            keyboard[i].reset();
        }
    }

    private boolean isPassed() {
        for (int i = 0; i < question.getWord().length(); i++) {
            if (!letters[i].isShowed) {
                return false;
            }
        }
        return true;
    }

    private void changeImage() {
        switch (lives) {
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

    @Override
    public void onKeysDown(Keys keys) {
        keys.checkLetter(question.getWord());
        if (keys.isCorrect) {
            for (int i = 0; i < question.getWord().length(); i++) {
                letters[i].changeLetter(keys.getLetter());
            }
        } else {
            lives--;
            changeImage();
        }
        if (isPassed()) {
            dialog.show(2, question.getWord());
        }
        if (lives == 0) {
            dialog.show(1, question.getWord());
        }
    }

    @Override
    public void onNotifyDialogButtonClick(int button) {
        switch (button) {
            case NotifyDialog.BUTTON_PLAY_AGAIN:
                reset();
                guessedWord = 0;
                dialog.dismiss();
                break;
            case NotifyDialog.BUTTON_NEXT:
                moveToNext();
                dialog.dismiss();
                break;
        }
    }
}
