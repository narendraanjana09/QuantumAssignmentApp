package com.nsa.quantumassignmentapp.ui.login_signup.onboarding

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.View
import com.nsa.quantumassignmentapp.R
import com.nsa.quantumassignmentapp.databinding.FragmentSignUpBinding
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nsa.quantumassignmentapp.ui.login_signup.db.User
import com.nsa.quantumassignmentapp.ui.login_signup.extra.*

import com.nsa.quantumassignmentapp.ui.login_signup.viewmodel.SignInSignUpViewModel
import java.util.regex.Pattern


class SignUpFragment(private var listener: PassDataListener) : Fragment(R.layout.fragment_sign_up) {

       private val viewModel by viewModels<SignInSignUpViewModel>()
    private var _binding : FragmentSignUpBinding?= null

    private val binding get() = _binding!!
    private lateinit var user_register_data:User
    var country_code:String="+91"
    var progress=Progress()


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)


      binding.apply {

        countryCodePicker.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val model = parent.adapter.getItem(position) as CountryCodeSpinner.CountryModel
                country_code=model.phoneCode.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
          val str= SpannableString("terms & condition" )
          str.setSpan(  UnderlineSpan() , 0 , str.length , 0 ) ;
          termsCondionBtn.setText(str)

          signInBtn.setOnClickListener{
              listener.updateFragmentData(Constants.action_sign_in)
          }

          termsCheckBox.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
              override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                  if(p1){
                  val name=nameEd.text.toString().trim()
                  val email=emailEd.text.toString().trim()
                  val contact=contactEd.text.toString().trim()
                  val password=passwordEd.text.toString().trim()
                  if(checkData(name,email,contact,password)){
                      signUpBtn.isVisible=true
                  }else{
                      termsCheckBox.isChecked=false
                  }
              }else{
                  signUpBtn.isVisible=false
                  }
              }
          })
          viewModel.add_query.observe(viewLifecycleOwner, Observer {
              progress.dismiss()
              if(it>0){
                  showToast("Registered Succesfully")
                  listener.updateFragmentData(Constants.action_user_registered)
              }else{

                  showToast("error registering user!")
              }
          })
          viewModel.user_query.observe(viewLifecycleOwner, Observer {
              progress.dismiss()
              if(it==null){
                  progress.show(parentFragmentManager,"progress")
                  context?.let { it1 -> SavedText(it1).setText(Constants.current_user,user_register_data.email) }
                  viewModel.addUser(user_register_data)
              }else{
                  showToast("an user with this email already exist")
              }
          })
          signUpBtn.setOnClickListener {
              val name=nameEd.text.toString().trim()
              val email=emailEd.text.toString().trim()
              val contact=contactEd.text.toString().trim()
              val password=passwordEd.text.toString().trim()
              if(checkData(name,email,contact,password)){
                  progress.show(parentFragmentManager,"progress")
                  viewModel.checkUser(email)
                  user_register_data= User(name,email,country_code+contact, password)
              }else{
                  termsCheckBox.isChecked=false
              }
          }
    }

    }

    private fun checkData(name: String, email: String, contact: String, password: String): Boolean {
        if(name.length==0){
            showToast("Name can't be empty")
            return false
        }
        if(!isValidEmail(email)){
            showToast("Invalid Email")
            return false
        }
        if(contact.length!=10){
            showToast("Contact is Invalid")
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