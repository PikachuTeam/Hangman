package yue.self.hangman.app;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import yue.self.hangman.R;

/**
 * Created by dongc_000 on 10/19/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        addFragment();
        findViews();
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() <= 0) {
            super.onBackPressed();
        } else {
            BaseFragment currentFragment = (BaseFragment) getFragmentManager().findFragmentById(R.id.fragmentContainer);
            currentFragment.onBackPressed();
        }
    }

    protected abstract BaseFragment getFragmentContent();

    private void addFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        BaseFragment fragment = getFragmentContent();
        transaction.add(R.id.fragmentContainer, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
