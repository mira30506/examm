package mx.com.practicamvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mx.com.practicamvvm.R
import mx.com.practicamvvm.data.local.model.ResultsModel
import mx.com.practicamvvm.databinding.FragmentFirstBinding
import mx.com.practicamvvm.ui.home.adapter.PageAdapter

@AndroidEntryPoint
class ListFragment : Fragment(), OnClickResult {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private val binding get() = _binding!!
    lateinit var adapter: PageAdapter
    var list = arrayListOf<ResultsModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        viewModel.getPage()
        viewModel.factsLiveData.observe(viewLifecycleOwner,this::setResults)
    }


    private fun setPages(result: List<ResultsModel>) {
        for (r in result)
            list.add(r)
        adapter = PageAdapter(list, this)
        binding.recyclerPage.adapter = adapter
        binding.progressCircular.visibility = View.GONE
        if (result.isEmpty())
            Toast.makeText(context, "No hay mas datos", Toast.LENGTH_SHORT).show()
    }

    private fun setResults(results: List<ResultsModel>) {
        for (r in results)
            list.add(r)
        this.adapter = PageAdapter(list, this)
        binding.recyclerPage.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerPage.adapter = adapter
        binding.scroll.visibility = View.VISIBLE
        binding.loadingPage.visibility = View.GONE

        binding.scroll.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight)
                    viewModel.getPage()
                binding.progressCircular.visibility = View.VISIBLE
            }
        })
    }



    fun setList() {
        adapter = PageAdapter(list, this)
        binding.recyclerPage.adapter = adapter
    }

    override fun setOnClickListener(result: ResultsModel) {
        var bundle = Bundle()
        bundle.putString("ID", result.Id)
         findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
    }
}