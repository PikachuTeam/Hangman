package yue.self.hangman.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import yue.self.hangman.R;

/**
 * Created by dongc_000 on 10/19/2015.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (enableToolbar()) {
            getBaseActivity().getToolbar().setVisibility(View.VISIBLE);
            getBaseActivity().getSupportActionBar().setTitle(getTitle());
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(enableButtonBack());
            getBaseActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        } else {
            getBaseActivity().getToolbar().setVisibility(View.GONE);
        }
    }

    protected String getTitle() {
        return getString(R.string.app_name);
    }

    protected void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    protected boolean enableToolbar() {
        return true;
    }

    protected boolean enableButtonBack() {
        return false;
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getBaseActivity(), "Aloha", Toast.LENGTH_LONG).show();
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(BaseFragment newFragment) {
        replaceFragment(newFragment, newFragment.getClass().getName(), null);
    }

    public void replaceFragment(BaseFragment newFragment, String fragmentTag, String transactionName) {
        replaceFragment(getFragmentManager(), newFragment, fragmentTag, transactionName);
    }

    public static void replaceFragment(FragmentManager manager, BaseFragment newFragment, String fragmentTag,
                                       String transactionName) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);
        transaction.replace(R.id.fragmentContainer, newFragment, fragmentTag);
        transaction.addToBackStack(transactionName);
        transaction.commit();
    }
}
