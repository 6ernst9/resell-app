package com.example.supplyr

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.slider.Slider
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    public val TEXT = "myUser"
    public val BOOLEAN = "isLoggedIn"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.navigationBarColor = resources.getColor(R.color.dark_theme)
        val max: Float = 100F
        val slider: Slider = findViewById(R.id.slider)
        val emailForm : EditText = findViewById(R.id.emailForm)
        val passForm : EditText = findViewById(R.id.passForm)
        slider.addOnChangeListener { slider, value, fromUser ->
            if (value == max) {
                val email = emailForm.text.toString()
                val pass = passForm.text.toString()
                if( email == "" || email == " ")
                {
                    emptyDialog("Email", "empty")
                    slider.value = 0f
                }
                if(pass == "" || pass == " "){
                    emptyDialog("Password", "empty")
                    slider.value = 0f
                }
                if(email!="" && email!="" && pass!=""  && pass!=" "){
                    email.trim{ it <=' '}
                    pass.trim{ it <= ' '}
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                        OnCompleteListener<AuthResult> {
                            if(it.isSuccessful){
                                Handler().postDelayed({
                                    val intent = Intent(this, HomeActivity::class.java)
                                    startActivity(intent)
                                    addSharedPrefs(true, FirebaseAuth.getInstance().currentUser!!.uid)
                                    overridePendingTransition(R.anim.from_right,  R.anim.to_left)
                                    finish()
                                }, 2000)
                            }
                            else{
                                emptyDialog("Email", it.exception!!.message.toString())
                                slider.value = 0f
                            }
                        }
                    )
                }

            }
        }
    }
    fun emptyDialog(emptyType : String, exeption : String){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.wrongemail_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val retryBtn : AppCompatButton =  dialog.findViewById(R.id.retryBtn)
        val emptyTitle : TextView = dialog.findViewById(R.id.emptyTitle)
        val emptyDes : TextView = dialog.findViewById(R.id.emptyDes)
        if(exeption!="empty"){
            emptyDes.text = "User not found. Please try again later"
            emptyTitle.text = "Invalid User"
        }
        else{
            emptyTitle.text = "Empty $emptyType"
        }
        val emailForm : EditText = findViewById(R.id.emailForm)
        val passForm : EditText = findViewById(R.id.passForm)
        retryBtn.setOnClickListener{
            dialog.cancel()
            emailForm.text.clear()
            passForm.text.clear()
        }
        dialog.show()
    }
    override fun onBackPressed() {
        startActivity(Intent(this, GetStartedActivity::class.java))
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
    }
    private fun addSharedPrefs(isLoggedIn: Boolean, myUser: String) {
        val sharedPref = getSharedPreferences("LoggedIn", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(TEXT,myUser)
        editor.putBoolean(BOOLEAN, isLoggedIn)
        editor.apply()
    }
}

