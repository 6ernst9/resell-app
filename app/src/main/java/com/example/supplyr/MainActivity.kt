package com.example.supplyr

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    var loggerVerifier = false
    public val BOOLEAN = "isLoggedIn"
    override fun onCreate(savedInstanceState: Bundle?) {
        getSharedPrefs()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.navigationBarColor = resources.getColor(R.color.black)
        Handler().postDelayed({
            if(loggerVerifier){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.from_right,  R.anim.to_left)
                finish()
            }
            else{
                val intent = Intent(this, GetStartedActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.from_right,  R.anim.to_left)
                finish()
            }

        }, 2000)
    }
    private fun getSharedPrefs(){
        val sharedPref = getSharedPreferences("LoggedIn", MODE_PRIVATE)
        loggerVerifier = sharedPref.getBoolean(BOOLEAN, false)
    }
}