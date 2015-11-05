package yue.self.hangman.app;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import tatteam.com.app_common.util.CloseAppHandler;
import yue.self.hangman.R;

/**
 * Created by dongc_000 on 10/19/2015.
 */
public abstract class BaseActivity extends AppCompatActivity implements CloseAppHandler.OnCloseAppListener {

    private Toolbar toolbar;
    private CloseAppHandler closeAppHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        addFragment();
        findViews();
        setSupportActionBar(toolbar);
        closeAppHandler = new CloseAppHandler(this);
        closeAppHandler.setListener(this);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() <= 0) {
            closeAppHandler.handlerKeyBack(this);
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

    @Override
    public void onRateAppDialogClose() {
        finish();
    }

    @Override
    public void onTryToCloseApp() {
        Toast.makeText(this, getString(R.string.message_exit), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReallyWantToCloseApp() {
        finish();
    }
}
