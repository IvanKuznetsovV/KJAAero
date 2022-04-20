package com.ivankuznetsov.kjaaero.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.viewbinding.ViewBinding
import com.ivankuznetsov.kjaaero.R


abstract class BaseFragment<B: ViewBinding, V: AndroidViewModel>: Fragment() {
    private var day = R.string.app_name
    private var viewBinding: B? = null
    private var viewModel: V? = null
    protected val binding get() = checkNotNull(viewBinding)
    protected val vModel get() = checkNotNull(viewModel)

    abstract fun initViewModel(): V
    abstract fun initBinding (inflater: LayoutInflater): B
    abstract fun getAllArrivalDepart(d: Int)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater){
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu,menuInflater )
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.info -> {
                val dialogInfo = InfoDialogFragment()
                dialogInfo.show(childFragmentManager, "info")
            }
            R.id.reload ->{
                getAllArrivalDepart(day)
            }
            R.id.yesterday -> {
                day = R.string.yesterday
                activity?.title = getString(day)
                getAllArrivalDepart(day)
            }
            R.id.today -> {
                day = R.string.app_name
                activity?.title = getString(day)
                getAllArrivalDepart(day)
            }
            R.id.tomorrow -> {
                day = R.string.tomorrow
                activity?.title = getString(day)
                getAllArrivalDepart(day)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?{
            viewBinding = initBinding(inflater)
            viewModel = initViewModel()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
            day = R.string.app_name
            activity?.title = getString(day)
            viewBinding = null
    }
}