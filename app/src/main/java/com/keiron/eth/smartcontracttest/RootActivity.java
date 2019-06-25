package com.keiron.eth.smartcontracttest;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.keiron.eth.smartcontracttest.screens.main.MainFragment;

public class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        replaceFragmentWith(true, MainFragment.newInstance());
    }

    private void replaceFragmentWith(Boolean isRoot, Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (isRoot) {

            for (int i = 0; i < supportFragmentManager.getBackStackEntryCount(); i++) {
                supportFragmentManager.popBackStack();
            }

            supportFragmentManager.beginTransaction()
                    .replace(R.id.parent, fragment)
                    .commit();
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.parent, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
