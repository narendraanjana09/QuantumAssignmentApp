package com.nsa.quantumassignmentapp.ui.login_signup.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.nsa.quantumassignmentapp.R
import com.nsa.quantumassignmentapp.databinding.FragmentSignInBinding
import com.nsa.quantumassignmentapp.ui.login_signup.extra.Constants
import com.nsa.quantumassignmentapp.ui.login_signup.extra.Constants.Companion.RC_SIGN_IN
import com.nsa.quantumassignmentapp.ui.login_signup.extra.PassDataListener
import com.nsa.quantumassignmentapp.ui.login_signup.extra.Progress
import com.nsa.quantumassignmentapp.ui.login_signup.extra.SavedText
import com.nsa.quantumassignmentapp.ui.login_signup.viewmodel.SignInSignUpViewModel
import java.util.regex.Pattern

class SignInFragment(private var listener: PassDataListener) : Fragment(R.layout.fragment_sign_in) {

    private var _binding : FragmentSignInBinding?= null
    private val viewModel by viewModels<SignInSignUpViewModel>()

    private val binding get() = _binding!!

    var progress= Progress()
    var email:String=""
    var password:String=""

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var account: GoogleSignInAccount? = null



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)

        val str= SpannableString("Register Now" )
        str.setSpan(  UnderlineSpan() , 0 , str.length , 0 ) ;

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(requireActivity().getString(R.string.google_key))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        account = GoogleSignIn.getLastSignedInAccount(requireActivity())

        if(account!=null){
            context?.let { it1 -> SavedText(it1).setText(Constants.current_user,account?.email.toString()) }
            listener.updateFragmentData(Constants.action_user_sign_in)
        }

        binding.apply {
            registerNowBtn.setText(str)
            registerNowBtn.setOnClickListener {
                listener.updateFragmentData(Constants.action_register)
            }

            viewModel.user_query.observe(viewLifecycleOwner, Observer {
                progress.dismiss()
                if(it==null){
                    showToast("no user found with this email")
                }else{
                    if(it.password.equals(password)){
                        showToast("sign-in success!")
                        context?.let { it1 -> SavedText(it1).setText(Constants.current_user,it.email) }
                        listener.updateFragmentData(Constants.action_user_sign_in)
                    }else{
                        showToast("password wrong")
                    }
                }
            })

            signInBtn.setOnClickListener {

                 email=emailEd.text.toString().trim()
                 password=passwordEd.text.toString().trim()
                if(checkData(email,password)){
                    progress.show(parentFragmentManager,"progress")
                   viewModel.checkUser(email)
                }
            }
            googleBtn.setOnClickListener {
                var signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()
                 startActivityForResult(signInIntent,RC_SIGN_IN)
            }


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_SIGN_IN -> {

                    //show progress
                    progress.show(parentFragmentManager,"signIn")
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    handleSignInResult(task)
                }
            }
        }

    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {

            progress.dismiss()
            account = task.getResult(ApiException::class.java)
            showToast("Google SignIn SuccessFull")
            SavedText(requireContext()).setText(Constants.current_user,account!!.email.toString())
            listener.updateFragmentData(Constants.action_user_sign_in)

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.message)
            e.printStackTrace()
            showToast("Google sign in failed" + e.toString() + e.localizedMessage)
        }
    }
    private fun checkData(email: String, password: String): Boolean {
        if(!isValidEmail(email)){
            showToast("Invalid Email")
            return false
        }

        if(password.length<8){
            showToast("password must be of 8 characters")
            return false
        }
        return true
    }
    private fun isValidEmail(email: String?): Boolean {
        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"
        val pat: Pattern = Pattern.compile(emailRegex)
        return if (email == null) false else pat.matcher(email).matches()
    }
    private fun showToast(message:String){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show()
    }

}