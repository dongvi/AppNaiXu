package com.naixu.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.naixu.R
import com.naixu.main.home.HomeFragment
import com.naixu.main.splash.SplashFragment
import com.naixu.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name ?: "MainActivity::class.java.name"
    }

    private var currentFragmentTag: String? = null
    /*private val onClickBottomMenuListener = object : OnClickBottomMenuListener {
        override fun onItemClick(position: Int) {
            when(position) {
                0 -> {}
                1 -> {}
                2 -> {}
                3 -> {}
            }
        }
    }
    private val dim = Runnable { btn_hide_or_show_btm.animate().alpha(0.3f).start() }
    private val handler = Handler()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initViews()
        initScreen()
        handleObservable()
    }

    private fun initViews() {
        // catch click event on bottom menu
//        bottom_menu.setOnClickBottomMenuListener(onClickBottomMenuListener)

//        hideOrShowBottomMenu()
    }

    private fun handleObservable() {

    }

    private fun initScreen() {
        /*btn_hide_or_show_btm.gone()
        bottom_menu.gone()*/
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                SplashFragment(),
                SplashFragment.TAG
            ).commitAllowingStateLoss()
        currentFragmentTag = SplashFragment.TAG
    }

    fun openHomeScreen() {
        // if don't interact the btn_hide_or_show_btm is dim
//        handler.postDelayed(dim, 1500)

        /*root_activity.postDelayed({
            btn_hide_or_show_btm.visible()
            bottom_menu.visible()

            btn_hide_or_show_btm.animation = AnimationUtils.loadAnimation(this, R.anim.up_fade_in)
            bottom_menu.animation = AnimationUtils.loadAnimation(this, R.anim.up_fade_in)
        }, 500)*/


        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
            .replace(
                R.id.container,
                HomeFragment(),
                HomeFragment.TAG
            ).commitAllowingStateLoss()
        currentFragmentTag = HomeFragment.TAG
    }

    fun addFragment(fragmentTag: String, fragment: Fragment? = null, isEnableAnimation: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()

        // hide current fragment
        (supportFragmentManager.findFragmentByTag(currentFragmentTag))?.let {
            if(fragmentTag != currentFragmentTag)
                transaction.hide(it)
        }

        if (isEnableAnimation) {
            transaction.setCustomAnimations(R.anim.right_enter, R.anim.fade_out, R.anim.fade_in, R.anim.right_exit)
        }

        // fragment not null then
        fragment?.let {
            // add fragment when it's not already added
            if(supportFragmentManager.findFragmentByTag(fragmentTag) == null) {
                transaction.add(R.id.container, it, fragmentTag)
            }

            // if fragment is added, show it and singleTop fragment
            if (fragmentTag.equals(fragmentTag)) {
                transaction.show(it)
            }
            else {
                transaction.show(it).addToBackStack(null)
                currentFragmentTag = fragmentTag
            }
        }

        transaction.commit()
    }

    /*private fun effectHideShowBottomMenu() {
        var valueRotate = 0

        btn_hide_or_show_btm.setOnClickListener {
            btn_hide_or_show_btm.isEnabled = false
            val isHideBottomMenu = bottom_menu.visibility == View.GONE

            bottom_menu.animation = AnimationUtils
                .loadAnimation(this,
                    if(!isHideBottomMenu) {
                        bottom_menu.gone()
                        R.anim.move_down_exit
                    } else {
                        bottom_menu.visible()
                        R.anim.move_up_in
                    })

            // set anim 1 for btn_hide_or_show_btm
            it.animate()
                .translationY(if(!isHideBottomMenu) bottom_menu.height.toFloat() else 0f)
                .setDuration(400)
                .withEndAction {
                    // after anim 1 finish, anim 2 is run
                    it.animate().rotation(++valueRotate * 180f).setDuration(200)
                        .withEndAction { btn_hide_or_show_btm.isEnabled = true }.start()
                }.start()

            // clarify btn_hide_or_show_btm
            it.animate().alpha(1.0f).setDuration(0).start()
            handler.removeCallbacks(dim)

            // if don't interact the btn_hide_or_show_btm is dim
            handler.postDelayed(dim, 1500)
        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
//        handler.removeCallbacks(dim)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {}
        else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {}
    }

    fun setCurrentFragmentTag(tag: String?) {
        currentFragmentTag = tag
    }

    fun setBackground(resId: Int) {
        root_activity.background = resources.getDrawable(resId)
    }
}