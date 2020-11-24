package vch.proj.activities;

import androidx.fragment.app.Fragment;

import vch.proj.fragments.CrimeFragment;
import vch.proj.fragments.CrimeListFragment;

import static vch.proj.classes.VchHelper.l;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
