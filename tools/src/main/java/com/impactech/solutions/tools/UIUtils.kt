package com.impactech.solutions.tools

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private fun Context.hideKeyboard(view: View){
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * A high level function that hides the soft input keyboard from displaying on the screen
 * If this function is triggered,
 * - the keyboard is removed from the screen if it was active
 * - nothing happens if the keyboard was not visible.
 * @receiver [Activity]
 * - This function can be triggered from an [Activity] scope
 * Example Kotlin
 * - From an activity, this.hideKeyboard() or just hideKeyboard()
 * - From a Fragment, requireActivity().hideKeyboard()
 * Example Java
 * - From an activity, UtilsKt.hideKeyboard(this)
 * - From an Fragment, UtilsKt.hideKeyboard(requireActivity)
 * **/
fun Activity.hideKeyboard(){
    hideKeyboard(currentFocus ?: View(this))
}

/**
 * A high level function that hides the soft input keyboard from displaying on the screen
 * If this function is triggered,
 * - the keyboard is removed from the screen if it was active
 * - nothing happens if the keyboard was not visible.
 * @receiver [Fragment]
 * - This function can be triggered from [Fragment] scope
 * - Example Kotlin hideKeyboard()
 * - Example Java  UtilsKt.hideKeyboard(this)
 * **/
fun Fragment.hideKeyboard(){
    requireActivity().hideKeyboard()
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun Context.toast(msg: Any){
    val text = if(msg is String) msg else msg.toString()
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: Any){
    requireContext().toast(msg)
}

fun RecyclerView.paginationListener(paginate: () -> Unit): RecyclerView.OnScrollListener {
    return object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            layoutManager?.let {
                it as LinearLayoutManager
                val itemCount = it.itemCount
                val lastVisibleItem = it.childCount + it.findFirstVisibleItemPosition()

                if(lastVisibleItem >= itemCount){
                    paginate.invoke()
                }
            }

        }
    }
}

