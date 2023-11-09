package com.ru.flag.quiz.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes stringRes: Int) {
    Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(string: String) {
    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
}