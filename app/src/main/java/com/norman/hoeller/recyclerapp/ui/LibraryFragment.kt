package com.norman.hoeller.recyclerapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import com.norman.hoeller.recyclerapp.R
import com.norman.hoeller.recyclerapp.adapter.RecyclerViewAdapter
import com.norman.hoeller.recyclerapp.data.Resource
import com.norman.hoeller.recyclerapp.data.Status
import com.norman.hoeller.recyclerapp.injection.Injection
import com.norman.hoeller.recyclerapp.items.Item
import com.norman.hoeller.recyclerapp.viewmodel.LibraryViewModel
import com.norman.hoeller.recyclerapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fr_main.*

/**
 * Created by Norman Hoeller
 */
class LibraryFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var libViewModel: LibraryViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerViewAdapter = RecyclerViewAdapter()
        rv_vertical.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        context?.let {
            viewModelFactory = Injection.provideViewModelFactory(it)
            libViewModel = ViewModelProviders.of(this, viewModelFactory).get(LibraryViewModel::class.java)
            libViewModel.data.observe(this, Observer { resource: Resource<List<Item>>? ->
                resource?.let {
                    when (resource.status) {
                        Status.SUCCESS -> {
                            pb_library.visibility = View.GONE
                            resource.data?.let {
                                recyclerViewAdapter.data = resource.data
                                rv_vertical.adapter = recyclerViewAdapter
                            }
                        }
                        Status.LOADING -> {
                            pb_library.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            pb_library.visibility = View.GONE
                            Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            if (savedInstanceState == null) {
                libViewModel.loadData()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.sort_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.sort) {
            libViewModel.loadData()
            true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun createInstance() = LibraryFragment()
    }
}