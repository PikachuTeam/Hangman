package yue.self.hangman.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;

import yue.self.hangman.R;

/**
 * Created by the_e_000 on 10/25/2015.
 */
public class NotifyDialog extends Dialog implements View.OnClickListener {

    public final static int BUTTON_PLAY_AGAIN = 1, BUTTON_NEXT = 2;

    private Context context;
    private View view;
    private TextView textViewDialogTitle;
    private TextView textViewStatus;
    private TextView textViewWord;
    private LinearLayout buttonPlayAgain;
    private LinearLayout buttonNext;
    private int state;
    private String word;
    private OnNotifyDialogButtonClickListener listener;

    private void findViews() {
        textViewDialogTitle = (TextView) findViewById(R.id.textViewDialogTitle);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        textViewWord = (TextView) findViewById(R.id.textViewWord);
        buttonPlayAgain = (LinearLayout) findViewById(R.id.buttonPlayAgain);
        buttonNext = (LinearLayout) findViewById(R.id.buttonNext);

        buttonPlayAgain.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

    public NotifyDialog(Context context, int state, String word) {
        super(context);
        this.context = context;
        view = View.inflate(this.context, R.layout.dialog_notify, null);
        this.state = state;
        this.word = word;
    }

    public void setOnNotifyDialogButtonClickListener(OnNotifyDialogButtonClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);

        findViews();

        switch (this.state) {
            case 1: // lose
                textViewDialogTitle.setText(MessageFormat.format(context.getString(R.string.dialog_title), "Sorry!"));
                textViewStatus.setText("You lose");
                buttonNext.setVisibility(View.GONE);
                buttonPlayAgain.setVisibility(View.VISIBLE);
                break;
            case 2: // win
                textViewDialogTitle.setText(MessageFormat.format(context.getString(R.string.dialog_title), "Congratulation!"));
                textViewStatus.setText("You win");
                buttonNext.setVisibility(View.VISIBLE);
                buttonPlayAgain.setVisibility(View.GONE);
                break;
        }

        textViewWord.setText(MessageFormat.format(context.getString(R.string.dialog_word), word));
    }

    public void changeState(int state) {
        this.state = state;
        switch (this.state) {
            case 1: // lose
                textViewDialogTitle.setText(MessageFormat.format(context.getString(R.string.dialog_title), "Sorry!"));
                textViewStatus.setText("You lose");
                buttonNext.setVisibility(View.GONE);
                buttonPlayAgain.setVisibility(View.VISIBLE);
                break;
            case 2: // win
                textViewDialogTitle.setText(MessageFormat.format(context.getString(R.string.dialog_title), "Congratulation!"));
                textViewStatus.setText("You win");
                buttonNext.setVisibility(View.VISIBLE);
                buttonPlayAgain.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        LinearLayout button = (LinearLayout) v;
        switch (button.getId()) {
            case R.id.buttonPlayAgain:
                if (listener != null) {
                    listener.onNotifyDialogButtonClick(BUTTON_PLAY_AGAIN);
                }
                break;
            case R.id.buttonNext:
                if (listener != null) {
                    listener.onNotifyDialogButtonClick(BUTTON_NEXT);
                }
                break;
        }
    }

    public interface OnNotifyDialogButtonClickListener {
        void onNotifyDialogButtonClick(int button);
    }
}
