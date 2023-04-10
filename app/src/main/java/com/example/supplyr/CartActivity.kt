package com.example.supplyr

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val banner : ConstraintLayout = findViewById(R.id.bannerLayout)
        banner.setPadding(0, getStatusBarHeight() + convertDpToPixel(10, this).toInt(), 0, 0)
        window.navigationBarColor = resources.getColor(R.color.dark_theme)
        val cartRecyclerView : RecyclerView = findViewById(R.id.cartRecyclerView)
        val productTitles= ArrayList<String>()
        productTitles.add("Nike Uptempo Gold Brown")
        productTitles.add("Jordan 1 Low Gym Red")
        productTitles.add("Jordan 1 High Smoke Grey")

        val productCondition = ArrayList<String>()
        productCondition.add("DS")
        productCondition.add("10/10")
        productCondition.add("VNDS")

        val productPrices = ArrayList<Double>()
        productPrices.add(1099.0)
        productPrices.add(1099.0)
        productPrices.add(1099.0)

        val productImages = ArrayList<Int>()
        productImages.add(R.drawable.uptempo)
        productImages.add(R.drawable.gymred)
        productImages.add(R.drawable.smokegrey)

        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        val productAdapter = ProductsAdapter(productTitles, productImages, productPrices, productCondition, this)
        cartRecyclerView.adapter = productAdapter
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