package com.example.roombookingapp.presentation.utils.extensions

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

fun <T : View> Activity.lazyView(@IdRes viewId: Int) : Lazy<T> = lazy { findViewById(viewId)}
