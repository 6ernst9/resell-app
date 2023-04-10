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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.navigationBarColor = resources.getColor(R.color.dark_theme)
        val navbar : ConstraintLayout = findViewById(R.id.navBar)
        val productsLayout : ConstraintLayout = findViewById(R.id.productsLayout)
        productsLayout.setPadding(convertDpToPixel(40, this).toInt(), getStatusBarHeight()+ convertDpToPixel(80, this).toInt(),convertDpToPixel(40, this).toInt() , convertDpToPixel(90, this).toInt())
        navbar.setPadding(convertDpToPixel(40, this).toInt(), convertDpToPixel(20, this).toInt() + getStatusBarHeight(),convertDpToPixel(40, this).toInt(), convertDpToPixel(20, this).toInt() )
        val cartBtn : ImageView = findViewById(R.id.cartBtn)
        cartBtn.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }
        val productsRecyclerView:RecyclerView = findViewById(R.id.productRecyclerView)
        val productTitles= ArrayList<String>()
        productTitles.add("Nike Uptempo Gold Brown")
        productTitles.add("Jordan 1 Low Gym Red")
        productTitles.add("Jordan 1 High Smoke Grey")
        productTitles.add("Jordan 1 High V Day")
        productTitles.add("Jordan 1 Elephant")

        val productCondition = ArrayList<String>()
        productCondition.add("DS")
        productCondition.add("10/10")
        productCondition.add("VNDS")
        productCondition.add("9.5/10")
        productCondition.add("9/10")

        val productPrices = ArrayList<Double>()
        productPrices.add(1099.0)
        productPrices.add(1099.0)
        productPrices.add(1099.0)
        productPrices.add(1099.0)
        productPrices.add(1099.0)

        val productImages = ArrayList<Int>()
        productImages.add(R.drawable.uptempo)
        productImages.add(R.drawable.gymred)
        productImages.add(R.drawable.smokegrey)
        productImages.add(R.drawable.jordanred)
        productImages.add(R.drawable.elephant)

        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        val productAdapter = ProductsAdapter(productTitles, productImages, productPrices, productCondition, this)
        productsRecyclerView.adapter = productAdapter

        val supplyrLogo : TextView = findViewById(R.id.supplyrLogo)
        supplyrLogo.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }


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