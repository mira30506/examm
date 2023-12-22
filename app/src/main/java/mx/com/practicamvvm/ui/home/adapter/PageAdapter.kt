package mx.com.practicamvvm.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.practicamvvm.R
import mx.com.practicamvvm.data.local.model.ResultsModel
import mx.com.practicamvvm.ui.home.OnClickResult

class PageAdapter(private val dataset: ArrayList<ResultsModel>, private val listener: OnClickResult):
    RecyclerView.Adapter<PageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView
        val tvNmae: TextView
        val mview: View
        val organization: TextView

        init {
            mview = view
            // Define click listener for the ViewHolder's View.
            id = view.findViewById(R.id.tvId)
            tvNmae= view.findViewById(R.id.tvName)
            organization = view.findViewById(R.id.tvOrg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_facts, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = dataset.get(position).Id
        holder.organization.text = dataset.get(position).organization
        holder.tvNmae.text = dataset.get(position).operations
        holder.mview.setOnClickListener {
        // listener.setOnClickListner(dataset.get(position))
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun addElementsList(result: ResultsModel) {
        dataset.add(result)
    }

}
