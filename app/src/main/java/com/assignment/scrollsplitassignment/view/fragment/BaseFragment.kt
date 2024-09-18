package com.assignment.scrollsplitassignment.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.assignment.scrollsplitassignment.view.activity.BaseActivity

open class BaseFragment : Fragment() {

    var baseActivity: BaseActivity? = null
        private set
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun toString(): String {
        return this.javaClass.simpleName
    }

    /**
     * this isn't really needed but makes it easier to get the parent activity fragment for
     * switching content without having to cast and check for types and so.
     *
     * @return the parent activity as a BaseActivity instance
     */
    protected val parentActivity: BaseActivity?
        protected get() = activity as BaseActivity?

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }
}