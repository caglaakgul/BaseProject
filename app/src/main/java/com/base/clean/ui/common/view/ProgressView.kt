package com.base.clean.ui.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.transition.TransitionManager
import com.base.clean.R

class ProgressView : CardView {

    private var txtProgressMessage: TextView? = null
    private var imgX: ImageView? = null

    private var text: String? = null

    private var icon : Drawable?=null
    private var xIcon : Int?=null

    constructor(context: Context) : super(context) {
        initializeViews(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeViews(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeViews(context, attrs, defStyleAttr)
    }

    private fun initializeViews(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_progress, this)

        val a = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressView, defStyleAttr, 0)

        text = a.getString(R.styleable.ProgressView_ProgressText)
        icon = a.getDrawable(R.styleable.ProgressView_ProgressIcon)

        a.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        txtProgressMessage = findViewById(R.id.txt_progress_message)
        imgX = findViewById(R.id.img_plane)

        val myFadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.progress_animation)
        imgX?.startAnimation(myFadeInAnimation)
        if (xIcon != null){
            imgX?.setImageResource(xIcon!!)
        } else if(icon != null){
            imgX?.setImageDrawable(icon)
        }

        updateUi(text)
    }

    private fun updateUi(text: String?) {
        TransitionManager.beginDelayedTransition(this)
        txtProgressMessage!!.text = text
        txtProgressMessage!!.visibility = if (TextUtils.isEmpty(text)) View.GONE else View.VISIBLE
        if (xIcon != null){
            imgX?.setImageResource(xIcon!!)
        }
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
        updateUi(text)
    }

    fun setIcon(drawable: Int) {
        xIcon = drawable
        imgX?.setImageResource(xIcon!!)
    }
}