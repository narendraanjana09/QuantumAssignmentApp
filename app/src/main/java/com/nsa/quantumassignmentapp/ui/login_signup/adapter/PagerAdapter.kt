package com.nsa.quantumassignmentapp.ui.login_signup.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nsa.quantumassignmentapp.ui.login_signup.onboarding.SignInFragment
import com.nsa.quantumassignmentapp.ui.login_signup.onboarding.SignUpFragment
import com.nsa.quantumassignmentapp.ui.login_signup.extra.PassDataListener

class PagerAdapter(fragmentActivity: FragmentActivity,private var listener: PassDataListener) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
       if (position === 0) {
           return SignInFragment(listener)
        } else {
           return  SignUpFragment(listener)
        }
    }
}