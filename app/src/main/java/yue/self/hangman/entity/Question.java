package yue.self.hangman.entity;

/**
 * Created by dongc_000 on 10/29/2015.
 */
public class Question {
    private String word;
    private String hint;

    public Question() {
        word = "";
        hint = "";
    }

    public Question(String word, String hint) {
        word = word.toUpperCase();
        this.word = word;
        this.hint = hint;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getWord() {
        return word;
    }

    public String getHint() {
        return hint;
    }
}
