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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class SignActivity : AppCompatActivity() {
    public val TEXT = "myUser"
    public val BOOLEAN = "isLoggedIn"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        window.navigationBarColor = resources.getColor(R.color.dark_theme)
        val max: Float = 100F
        val slider: Slider = findViewById(R.id.slider)
        val nameForm : EditText = findViewById(R.id.nameForm)
        val emailForm : EditText = findViewById(R.id.emailForm)
        val passForm : EditText = findViewById(R.id.passForm)
        val loginBtn : TextView = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.from_right,  R.anim.to_left)
            finish()
        }
        slider.addOnChangeListener { slider, value, fromUser ->
            if (value == max) {
                val email = emailForm.text.toString()
                val name = nameForm.text.toString()
                val pass = passForm.text.toString()
                if(name == "" || name == " "){
                    emptyDialog("Email", "empty")
                    slider.value = 0f
                }
                if( email == "" || email == " ")
                {
                    emptyDialog("Email", "empty")
                    slider.value = 0f
                }
                if(pass == "" || pass == " "){
                    emptyDialog("Password", "empty")
                    slider.value = 0f
                }
                if(email!="" && email!=" " && pass!=""  && pass!=" " && name!="" && name!=" "){
                    name.trim{ it <= ' '}
                    email.trim{ it <=' '}
                    pass.trim{ it <= ' '}
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener(
                        OnCompleteListener<AuthResult> {
                            if(it.isSuccessful){
                                println("Succesful")
                                val firebaseUser : FirebaseUser = it.result!!.user!!
                                Handler().postDelayed({
                                    val intent = Intent(this, HomeActivity::class.java)
                                    val newAccountClass = Account(name, email, pass, "", "", "", 0.0,0, 0 )
                                    FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser.uid).setValue(newAccountClass)
                                    addSharedPrefs(true, firebaseUser.uid)
                                    startActivity(intent)
                                    overridePendingTransition(R.anim.from_right,  R.anim.to_left)
                                    this.finish()
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

    override fun onBackPressed() {
        startActivity(Intent(this, GetStartedActivity::class.java))
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
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
        val nameForm : EditText = findViewById(R.id.nameForm)
        val emailForm : EditText = findViewById(R.id.emailForm)
        val passForm : EditText = findViewById(R.id.passForm)

        retryBtn.setOnClickListener{
            dialog.cancel()
            nameForm.text.clear()
            emailForm.text.clear()
            passForm.text.clear()
        }
        dialog.show()
    }
    private fun addSharedPrefs(isLoggedIn: Boolean, myUser: String) {
        val sharedPref = getSharedPreferences("LoggedIn", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(TEXT,myUser)
        editor.putBoolean(BOOLEAN, isLoggedIn)
        editor.apply()
    }

}