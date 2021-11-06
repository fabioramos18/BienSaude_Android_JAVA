package com.example.biensaudev1.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.biensaudev1.ui.description.DescriptionDetailFragment;
import com.example.biensaudev1.ui.expectation.ExpectationDetailFragment;
import com.example.biensaudev1.ui.recipients.RecipientsDetailFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new RecipientsDetailFragment();
            case 2:
                return new ExpectationDetailFragment();
        }
        return new DescriptionDetailFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
