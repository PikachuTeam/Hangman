package yue.self.hangman.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import yue.self.hangman.R;

/**
 * Created by the_e_000 on 10/22/2015.
 */
public class Letter {

    private Context context;
    private View view;
    private TextView textViewLetter;
    private TextView textViewTemporary;
    public boolean isShowed;

    public Letter(Context context) {
        this.context = context;
        view = View.inflate(this.context, R.layout.guess_text, null);
        findViews(view);
        isShowed = false;
    }

    public View getView() {
        return view;
    }

    public void changeLetter(String letter) {
        if (getLetter().equals(letter)) {
            showLetter();
        }
    }

    public void showLetter() {
        textViewLetter.setVisibility(View.VISIBLE);
        textViewTemporary.setVisibility(View.GONE);
        isShowed = true;
    }

    public String getLetter() {
        return textViewLetter.getText().toString();
    }

    public void setLetter(String letter) {
        textViewLetter.setText(letter);
    }

    private void findViews(View rootView) {
        textViewLetter = (TextView) rootView.findViewById(R.id.textViewLetter);
        textViewTemporary = (TextView) rootView.findViewById(R.id.textViewTemporary);
    }
}
