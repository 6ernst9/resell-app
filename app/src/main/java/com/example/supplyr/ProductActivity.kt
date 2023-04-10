package com.example.supplyr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout


class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val navbar : ConstraintLayout = findViewById(R.id.navBar)
        val cartBtn : ImageView = findViewById(R.id.cartBtn)
        window.navigationBarColor = resources.getColor(R.color.dark_theme)
        val productScrollView : ScrollView = findViewById(R.id.productScrollview)
        cartBtn.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }
        navbar.setPadding(convertDpToPixel(40, this).toInt(),
            convertDpToPixel(20, this).toInt() + getStatusBarHeight(),convertDpToPixel(40, this).toInt(), convertDpToPixel(20, this).toInt() )
        val productCardview : CardView = findViewById(R.id.productCardview)
        productCardview.minimumHeight = productCardview.width
        val productTitle : TextView = findViewById(R.id.productNameTitle)
        productTitle.setPadding(convertDpToPixel(70, this).toInt(),
            convertDpToPixel(60, this).toInt() + getStatusBarHeight(),convertDpToPixel(70, this).toInt(), 0)
        val productPrice : TextView = findViewById(R.id.priceTitle)
        val productCondition : TextView = findViewById(R.id.productCondition)
        val productImage : ImageView = findViewById(R.id.mainImage)
        val firstPhoto : ImageView = findViewById(R.id.prevPhoto)
        val secondPhoto : ImageView = findViewById(R.id.currentPhoto)
        val thirdPhoto : ImageView = findViewById(R.id.nextPhoto)
        val productTitleText = intent.getStringExtra("name")
        val productPriceText = intent.getDoubleExtra("price", 1.0)
        val productConditionText = intent.getStringExtra("condition")
        val productImageDrawable = intent.getIntExtra("images", 1)
        productTitle.text = productTitleText
        productPrice.text = "${productPriceText}Ron"
        productCondition.text = productConditionText
        firstPhoto.setImageResource(productImageDrawable)
        secondPhoto.setImageResource(productImageDrawable)
        thirdPhoto.setImageResource(productImageDrawable)
        productImage.setImageResource(productImageDrawable)
        val beforeCardView = convertPixelsToDp(350, this)
        val beforeFooter = convertPixelsToDp(980, this)


    }
    fun getStatusBarHeight(): Int {
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