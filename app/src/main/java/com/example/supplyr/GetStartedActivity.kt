package com.example.supplyr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import org.w3c.dom.Text
import kotlin.system.exitProcess

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        val loginBtn : AppCompatButton = findViewById(R.id.loginBtn)
        val signInBtn : AppCompatButton = findViewById(R.id.signInBtn)
        val forgotPassword : TextView = findViewById(R.id.forgotPassword)
        forgotPassword.setPadding(0, 0, 0, getNavBarHeight() + 10)
        loginBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
            finish()
        }
        signInBtn.setOnClickListener{
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
            finish()
        }
    }
    fun getNavBarHeight() : Int{
        var result = 0
        val resourceId= resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}