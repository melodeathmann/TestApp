package by.life.testapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object FragmentUtils {

    private const val FRAGMENT_TAG = "Content"

    fun changeFragment(activity: FragmentActivity?, contentFrame: Int, fr: Fragment, addToBackStack: Boolean = false) {
        val ft = activity?.supportFragmentManager?.beginTransaction()

        ft?.replace(contentFrame, fr, FRAGMENT_TAG)
        if (addToBackStack) {
            ft?.addToBackStack(null)
        }

        ft?.commitAllowingStateLoss()
    }


}