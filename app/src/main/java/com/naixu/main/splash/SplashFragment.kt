package com.naixu.main.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.naixu.R
import com.naixu.main.MainActivity
import com.naixu.ui.BaseFragment
import com.naixu.utils.extensions.gone
import com.naixu.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment: BaseFragment() {

    companion object {
        val TAG = SplashFragment::class.java.name ?: "SplashFragment::class.java.name"

        private const val SPLASH_TIME = 3000L
    }

    private var mainActivity: MainActivity? = null
    private val handler = Handler()
    private val runAnim1ForLogo = Runnable {
        view_logo.visible()
        view_logo.animation = AnimationUtils.loadAnimation(context, R.anim.up_fade_in)

        handler.postDelayed(runAnim2ForLogo, 2300)
    }
    private val runAnim2ForLogo = Runnable {
        view_logo.animation = AnimationUtils.loadAnimation(context, R.anim.right_exit)
        view_logo.gone()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        handler.postDelayed(runAnim1ForLogo, 500)

        startHomeScreen()
    }

    private fun startHomeScreen() {
        root_splash.postDelayed({
            mainActivity?.openHomeScreen()
            mainActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }, SPLASH_TIME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runAnim1ForLogo)
        handler.removeCallbacks(runAnim2ForLogo)
    }
}