package yue.self.hangman;

import yue.self.hangman.app.BaseActivity;
import yue.self.hangman.app.BaseFragment;
import yue.self.hangman.fragments.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseFragment getFragmentContent() {
        return new HomeFragment();
    }
}
