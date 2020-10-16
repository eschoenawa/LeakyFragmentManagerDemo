package de.eschoenawa.leakyfragmentmanagerdemo

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = my_toolbar
        val supportActivity = (activity as AppCompatActivity)
        supportActivity.setSupportActionBar(toolbar)
        supportActivity.supportActionBar?.title = "Toolbar Title"
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mymenu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.action_switch)
        menuItem.setOnMenuItemClickListener {
            (activity as MainActivity).navigateToFragmentB()
            return@setOnMenuItemClickListener true
        }
    }

    /**
     * Necessary cleanup to prevent another memory leak, see
     * https://gist.github.com/eschoenawa/57c309c7e8dd7f9c5729bdeeb81ed72d for a trace of that one.
     *
     * Note: That is not the memory leak that this project should replicate, but another one.
     */
    override fun onStop() {
        super.onStop()
        val supportActivity = (activity as AppCompatActivity)
        supportActivity.setSupportActionBar(null)
        setHasOptionsMenu(false)
    }
}