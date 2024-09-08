package com.zerox.navi

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageView

class CheckableImageView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : AppCompatImageView(context, attrs, defStyleAttr),
        Checkable {
        private var isCheckedState = false

        override fun isChecked(): Boolean = isCheckedState

        override fun setChecked(checked: Boolean) {
            isCheckedState = checked
            refreshDrawableState() // This updates the drawable state
        }

        override fun toggle() {
            isChecked = !isChecked
        }

        override fun onCreateDrawableState(extraSpace: Int): IntArray {
            val drawableState = super.onCreateDrawableState(extraSpace + 1)
            if (isChecked) {
                mergeDrawableStates(drawableState, CHECKED_STATE_SET)
            }
            return drawableState
        }

        companion object {
            private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
        }
    }
