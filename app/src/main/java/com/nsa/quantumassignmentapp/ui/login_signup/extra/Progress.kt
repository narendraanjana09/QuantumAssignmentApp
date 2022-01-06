package com.nsa.quantumassignmentapp.ui.login_signup.extra

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.nsa.quantumassignmentapp.R

class Progress: DialogFragment(R.layout.progress_dialog_layout){
    init {
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
    }
}