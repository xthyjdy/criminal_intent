package vch.proj.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import vch.proj.R;

import static vch.proj.classes.VchHelper.l;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frargment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frargment_container);

        if (null == fragment) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.frargment_container, fragment)
                    .commit();
        }
    }
}
