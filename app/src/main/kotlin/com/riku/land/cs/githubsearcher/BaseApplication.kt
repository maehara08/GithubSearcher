package com.riku.land.cs.githubsearcher

import android.support.annotation.IdRes
import android.support.multidex.MultiDexApplication
import android.view.View

open class BaseApplication : MultiDexApplication() {

}

//Extension
fun <T : View> View.bindView(@IdRes resId: Int): T =
        this.findViewById(resId) as T