package com.nsa.quantumassignmentapp.ui.login_signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.nsa.quantumassignmentapp.databinding.FragmentSigninSignUpBinding



import com.nsa.quantumassignmentapp.ui.login_signup.adapter.PagerAdapter

import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.nsa.quantumassignmentapp.R


import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nsa.quantumassignmentapp.ui.login_signup.extra.Constants
import com.nsa.quantumassignmentapp.ui.login_signup.extra.PassDataListener
import com.nsa.quantumassignmentapp.ui.login_signup.extra.SavedText


class Signin_SignUp_Fragment : Fragment(R.layout.fragment_signin__sign_up_) {







    private lateinit var binding:FragmentSigninSignUpBinding
    val count=MutableLiveData<Int>()
    private lateinit var navController: NavController



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSigninSignUpBinding.bind(view)

        navController=Navigation.findNavController(view)
        setStatusBarRed()
      if(!SavedText(requireContext()).getText(Constants.current_user).equals("")){
          navController.navigate(R.id.action_login_SignUp_Fragment_to_homeFragment)
      }


        binding.apply {
            val listener: PassDataListener = object : PassDataListener {
                override fun updateFragmentData(data: String) {
                    if(data.equals(Constants.action_register)){
                        viewpager.setCurrentItem(1,true)
                    }else if(data.equals(Constants.action_sign_in)){
                        viewpager.setCurrentItem(0,true)
                    }else if(data.equals(Constants.action_user_registered)
                        || data.equals(Constants.action_user_sign_in)){

                        navController.navigate(R.id.action_login_SignUp_Fragment_to_homeFragment)
                    }
                }
            }

            tabLayout.tabGravity=TabLayout.GRAVITY_FILL
            viewpager.setAdapter(PagerAdapter(activity as FragmentActivity,listener))
            TabLayoutMediator(
                tabLayout, viewpager
            ) { tab, position ->
                if (position == 0) {
                    tab.setText("Sign In")
                    tab.view.background = getResources().getDrawable(R.drawable.signin_signup_bg)
                } else {
                    tab.setText("Sign Up")

                }
            }.attach()


            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {

                    tab.view.background = getResources().getDrawable(R.drawable.signin_signup_bg)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    tab.view.background=null
                   // tab.view.setBackgroundColor(Color.TRANSPARENT)
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

        }

    }

    private fun setStatusBarRed() {
            val window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = requireContext().resources.getColor(R.color.red)


    }


}