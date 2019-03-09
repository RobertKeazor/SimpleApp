package com.robertkeazor.chatapp.base.custombindings

import android.widget.EditText
import androidx.databinding.BindingAdapter

object EditTextBindings {
    @JvmStatic
    @BindingAdapter("setErrorBinding")
    fun setError(view: EditText, text: String?) {
        view.error = if (text.isNullOrEmpty()) null else text
    }
}