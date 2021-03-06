package org.wordpress.android.ui.prefs;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;

import javax.inject.Inject;

public class MyProfileActivity extends AppCompatActivity {
    private static final String KEY_MY_PROFILE_FRAGMENT = "my-profile-fragment";

    @Inject Dispatcher mDispatcher;
    @Inject AccountStore mAccountStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WordPress) getApplication()).component().inject(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.my_profile);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        FragmentManager fragmentManager = getFragmentManager();
        MyProfileFragment myProfileFragment =
                (MyProfileFragment) fragmentManager.findFragmentByTag(KEY_MY_PROFILE_FRAGMENT);
        if (myProfileFragment == null) {
            myProfileFragment = MyProfileFragment.newInstance();

            fragmentManager.beginTransaction()
                           .add(android.R.id.content, myProfileFragment, KEY_MY_PROFILE_FRAGMENT)
                           .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
