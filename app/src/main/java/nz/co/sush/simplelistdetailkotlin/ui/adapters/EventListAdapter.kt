package nz.co.sush.simplelistdetailkotlin.ui.adapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by tomtang on 18/02/16.
 */
class EventListAdapter() : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {
    var items:List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}