package com.nsa.quantumassignmentapp.ui.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.nsa.quantumassignmentapp.R
import com.nsa.quantumassignmentapp.databinding.FragmentHomeBinding


import com.nsa.quantumassignmentapp.ui.home.adapter.*
import com.nsa.quantumassignmentapp.ui.home.models.data
import kotlinx.android.synthetic.main.home_layout_2.*
import android.widget.Toast

import com.nsa.quantumassignmentapp.MainActivity


import android.R.attr

import android.R.attr.button
import android.view.Gravity
import android.view.MenuItem
import android.widget.PopupMenu
import com.nsa.quantumassignmentapp.ui.login_signup.extra.Constants
import com.nsa.quantumassignmentapp.ui.login_signup.extra.SavedText
import android.view.ViewGroup

import android.app.Activity
import android.graphics.Color
import androidx.core.view.GravityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class HomeFragment : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding
    val count= MutableLiveData<Int>()
    private lateinit var navController: NavController

    private var loopingImagesList= arrayListOf<Drawable>()
    private var typesImageList= arrayListOf<data>()
    private var level3ImagesList= arrayListOf<Drawable>()
    private var level4ImagesList= arrayListOf<data>()
    private var level5ImagesList= arrayListOf<data>()
    private var level6ImagesList= arrayListOf<data>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        navController=Navigation.findNavController(view)
        setSatusBarWhite()

        getAllLists()

        binding.apply {
            object : CountDownTimer(Long.MAX_VALUE, 5000) {

                override fun onTick(millisUntilFinished: Long) {
                    if(loopingPager.currentItem==loopingImagesList.size-1){
                        loopingPager.setCurrentItem(0,true)
                    }else{
                    loopingPager.setCurrentItem(loopingPager.currentItem+1,true)
                }
                }

                override fun onFinish() {
                }
            }.start()
            //top looping pager
            loopingPager.setAdapter(LoopingAdapter(loopingImagesList));
            loopingPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                // This method is triggered when there is any scrolling activity for the current page
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                // triggered when you select a new page
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                   addDot(position,dotTV)
                }

                // triggered when there is
                // scroll state will be changed
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })

            // types recylerview
            typesRecyclerView.setAdapter(TypesAdapter(typesImageList))

            // level3 recylerview
            level3RecyclerView.setAdapter(Level3_Adapter(level3ImagesList))

            // level4 recylerview
            level4RecyclerView.setAdapter(Level4_Adapter(level4ImagesList))

            // level5 recylerview
            level5_recycler_view.setAdapter(Level5_Adapter(level5ImagesList))

            // level5 recylerview
            level6_recycler_view.setAdapter(Level5_Adapter(level6ImagesList))

            // level7 recylerview
            level7_recycler_view.setAdapter(Level7_Adapter())

            setTimer(hours,minutes,seconds)

            menuBtn.setOnClickListener{
                drawerLayout.openDrawer(GravityCompat.START)
            }
            navView.setNavigationItemSelectedListener { menuItem ->

                drawerLayout.closeDrawers()


                when (menuItem.itemId) {

                    R.id.logout -> {
                       SavedText(requireContext()).setText(Constants.current_user,"")
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(requireActivity().getString(R.string.google_key))
                            .requestEmail()
                            .build()
                    GoogleSignIn.getClient(requireActivity(), gso).signOut().addOnSuccessListener {
                        navController.navigate(R.id.action_homeFragment_to_login_SignUp_Fragment)
                    }
                    }

                }
                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                true
            }


        }

    }


    private fun setTimer(hours: TextView?, minutes: TextView, seconds: TextView) {
        val milliseconds = System.currentTimeMillis() + 7200000 // 24 hours = 86400000 milliseconds
        var startTime = System.currentTimeMillis()

        val cdt: CountDownTimer = object : CountDownTimer(milliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                startTime = startTime - 1
                val serverUptimeSeconds: Long = (millisUntilFinished - startTime) / 1000

                // now you repalce txtViewHours,  txtViewMinutes, txtViewSeconds by your textView.

                val hoursLeft = String.format("%d", serverUptimeSeconds % 86400 / 3600)
                hours?.setText("0"+hoursLeft)
                val minutesLeft = String.format("%d", serverUptimeSeconds % 86400 % 3600 / 60)

                minutes?.setText(minutesLeft)
                val secondsLeft = String.format("%d", serverUptimeSeconds % 86400 % 3600 % 60)

                seconds?.setText(secondsLeft)

            }

            override fun onFinish() {

            }
        }

        cdt.start()
    }

    private fun getAllLists() {
        loopingImagesList.add(requireContext().resources.getDrawable(R.drawable.imgloop1))
        loopingImagesList.add(requireContext().resources.getDrawable(R.drawable.imgloop2))
        loopingImagesList.add(requireContext().resources.getDrawable(R.drawable.imgloop3))

        typesImageList.add(data(requireContext().resources.getDrawable(R.drawable.t_arts_img),"Arts & Crafts"))
        typesImageList.add(data(requireContext().resources.getDrawable(R.drawable.t_automotive_img),"Automotive"))
        typesImageList.add(data(requireContext().resources.getDrawable(R.drawable.t_baby_img),"Baby"))
        typesImageList.add(data(requireContext().resources.getDrawable(R.drawable.t_computer_img),"Computer"))
        typesImageList.add(data(requireContext().resources.getDrawable(R.drawable.t_cloud_music),"Digital Music"))

        level3ImagesList.add(requireContext().resources.getDrawable(R.drawable.l3_girl_dress_img))
        level3ImagesList.add(requireContext().resources.getDrawable(R.drawable.l3_camera_img))
        level3ImagesList.add(requireContext().resources.getDrawable(R.drawable.l3_speaker_image))

        level4ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l4_mobile_img),"Up to 20% Off"))
        level4ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l4_watches_img),"Up to 50% Off"))
        level4ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l4_purses_img),"Up to 30% Off"))

        level5ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l5_watch_img),"Up to 30% Off"))
        level5ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l5_apple_img),"Up to 5% Off"))
        level5ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l5_nike_img),"Up to 5% Off"))

        level6ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l6_levis_img),"Up to 30% Off"))
        level6ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l6_dress_img),"Up to 5% Off"))
        level6ImagesList.add(data(requireContext().resources.getDrawable(R.drawable.l5_nike_img),"Up to 5% Off"))


    }

    private fun addDot(position: Int, dotTV: TextView) {
        var str:String=""
        for (i in 0 until loopingImagesList.size) {
          if(position==i){
              str+="● "
          }else{
              str+="○ "
          }
        }
        dotTV.setText(str)
    }

    private fun setSatusBarWhite() {
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = requireContext().resources.getColor(R.color.white)

    }
}


