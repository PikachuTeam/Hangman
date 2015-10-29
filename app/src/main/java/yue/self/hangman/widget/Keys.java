package yue.self.hangman.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import yue.self.hangman.R;

/**
 * Created by the_e_000 on 10/20/2015.
 */
public class Keys implements View.OnClickListener {

    private Context context;
    private View view;
    private TextView textViewKeys;
    private OnKeysDownListener listener;

    public boolean used;
    public boolean isCorrect;

    public Keys(Context context) {
        this.context = context;
        view = View.inflate(this.context, R.layout.keys, null);
        findViews(view);
    }

    public void init(String keys) {
        textViewKeys.setText(keys);
        reset();
        view.setOnClickListener(this);
    }

    public void changeState() {
        if (used) {
            if (isCorrect) {
                textViewKeys.setTextColor(ContextCompat.getColor(context, R.color.correct_keys));
            } else {
                textViewKeys.setTextColor(ContextCompat.getColor(context, R.color.wrong_keys));
            }
        }
    }

    public void checkLetter(String word) {
        isCorrect = isIn(word);
        changeState();
    }

    public String getLetter() {
        return textViewKeys.getText().toString();
    }

    public void reset() {
        used = false;
        isCorrect = false;
        textViewKeys.setTextColor(ContextCompat.getColor(this.context, R.color.black));
    }

    public boolean isIn(String word) {
        boolean tmp = false;
        for (int i = 0; i < word.length(); i++) {
            if (getLetter().equals("" + word.charAt(i))) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }

    public View getView() {
        return view;
    }

    public void setOnKeysDownListener(OnKeysDownListener listener) {
        this.listener = listener;
    }

    public void findViews(View rootView) {
        textViewKeys = (TextView) rootView.findViewById(R.id.textViewKeys);
    }

    @Override
    public void onClick(View v) {
        if (!used) {
            used = true;
            if (listener != null) {
                listener.onKeysDown(this);
            }
        }
    }

    public interface OnKeysDownListener {
        void onKeysDown(Keys keys);
    }
}
