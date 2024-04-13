package com.example.myapplication.common.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    lateinit var binding: VB
    private val type = (javaClass.genericSuperclass as ParameterizedType)
    private val classVB = type.actualTypeArguments[0] as Class<VB>

    private val inflateMethod = classVB.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = inflateMethod.invoke(null, inflater, container, false) as VB
        subscribe()
        initViews()
        return binding.root
    }

    fun hideSystemUI() {
        requireActivity().window.statusBarColor = Color.TRANSPARENT
        requireActivity().window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        requireActivity().window?.let { WindowCompat.setDecorFitsSystemWindows(it, false) }
        val windowInsetsController = requireActivity().window?.decorView?.let {
            ViewCompat.getWindowInsetsController(
                it
            )
        } ?: return
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        WindowInsetsCompat.Type.systemBars().run {
            windowInsetsController.hide(this)
        }
    }

    fun showSystemUI() {
        requireActivity().window.decorView.systemUiVisibility
    }

    fun onBackPress(closeActivity: Boolean = false) {
        requireActivity().onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (closeActivity) requireActivity().finish()

                if (isEnabled && !closeActivity) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })
    }

    open fun initViews() {}

    open fun subscribe() {}
}