package ru.gb.bufet.model.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import ru.gb.bufet.R

class SplashAnimation(private val context: Context, private val subTitle: TextView, private val foodItems: List<ImageView>) {
    private var currentAnimation = false
    fun subTitleAnimate() {
        subTitle.startAnimation(AnimationUtils.loadAnimation(context, R.anim.subtitle))
    }

    //food animation
    fun splashItemsAnimation(){
        val animation: Animation = if(!currentAnimation){
            AnimationUtils.loadAnimation(context, R.anim.splash_move_item_move_up)
        } else{
            AnimationUtils.loadAnimation(context, R.anim.splash_item_move_down)
        }
        val animation2: Animation = if(!currentAnimation){
            AnimationUtils.loadAnimation(context, R.anim.splash_item_move_right)
        } else{
            AnimationUtils.loadAnimation(context, R.anim.splash_item_move_left)
        }
        val animation3: Animation = if(!currentAnimation){
            AnimationUtils.loadAnimation(context, R.anim.splash_item_move_down)
        } else{
            AnimationUtils.loadAnimation(context, R.anim.splash_move_item_move_up)
        }
        val animation4: Animation = if(!currentAnimation){
            AnimationUtils.loadAnimation(context, R.anim.splash_item_move_left)
        } else{
            AnimationUtils.loadAnimation(context, R.anim.splash_item_move_right)
        }
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                currentAnimation = currentAnimation==false
                splashItemsAnimation()
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        foodItems[0].startAnimation(animation)
        foodItems[1].startAnimation(animation4)
        foodItems[2].startAnimation(animation3)
        foodItems[3].startAnimation(animation2)
        foodItems[4].startAnimation(animation3)
    }
}