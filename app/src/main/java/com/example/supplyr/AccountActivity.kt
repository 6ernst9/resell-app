package com.example.supplyr

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class AccountActivity : AppCompatActivity() {
    public val TEXT = "myUser"
    public val BOOLEAN = "isLoggedIn"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        val accountHeader : ConstraintLayout = findViewById(R.id.accountHeader)
        val scrollView : ScrollView = findViewById(R.id.scrollView)
        accountHeader.setPadding(convertDpToPixel(40, this).toInt(), getStatusBarHeight() + convertDpToPixel(20, this).toInt(),
            convertDpToPixel(40, this).toInt(), convertDpToPixel(20, this).toInt() )
        scrollView.setPadding(0,  getStatusBarHeight() + convertDpToPixel(70, this).toInt(),  0, 0)
        val addPostBtn : ImageView = findViewById(R.id.addPostBtn)
        val logOutBtn : ImageView = findViewById(R.id.logOutArrow)
        logOutBtn.setOnClickListener{

        }



    }
    private fun logOutDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.logout_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val logOutBtn : AppCompatButton =  dialog.findViewById(R.id.logOutBtn)
        val cancelBtn : AppCompatButton = dialog.findViewById(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            dialog.cancel()
        }
        logOutBtn.setOnClickListener{
            Handler().postDelayed({
                val sharedPref = this.getSharedPreferences("LoggedIn", AppCompatActivity.MODE_PRIVATE)
                sharedPref.edit().putBoolean(BOOLEAN, false)
                val intent = Intent(this, GetStartedActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.from_left, R.anim.to_right)
            }, 2000)

        }
        dialog.show()
    }
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
    fun convertDpToPixel(dp: Int, context: Context): Double {
        val doubleValue = dp.toDouble()
        return doubleValue * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }
    fun convertPixelsToDp(px: Int, context: Context): Double {
        val doubleValue = px.toDouble()
        return doubleValue / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }
}