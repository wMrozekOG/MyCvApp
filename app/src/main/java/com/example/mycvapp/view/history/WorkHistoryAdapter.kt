package com.example.mycvapp.view.history

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import com.example.mycvapp.R
import com.example.mycvapp.database.WorkHistoryEntry
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycvapp.extensions.formatDefaultBold
import com.example.mycvapp.extensions.toFormattedDate
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.work_history_entry.view.company
import kotlinx.android.synthetic.main.work_history_entry.view.container
import kotlinx.android.synthetic.main.work_history_entry.view.duration
import kotlinx.android.synthetic.main.work_history_entry.view.start
import kotlinx.android.synthetic.main.work_history_entry.view.summary
import kotlinx.android.synthetic.main.work_history_entry.view.title
import java.lang.Exception

class WorkHistoryAdapter : ListAdapter<WorkHistoryEntry, WorkHistoryAdapter.WorkHistoryEntryVH>(WorkHistoryEntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : WorkHistoryEntryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.work_history_entry, parent, false)
        return WorkHistoryEntryVH(view)
    }

    override fun onBindViewHolder(holder: WorkHistoryEntryVH, position: Int) {
        holder.bind(getItem(position))
    }

    class WorkHistoryEntryVH(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(entry: WorkHistoryEntry) {
            with(view) {
                title.formatDefaultBold(context.getString(R.string.work_history_role), entry.title)
                company.formatDefaultBold(context.getString(R.string.work_history_company), entry.company)
                start.formatDefaultBold(context.getString(R.string.work_history_start), entry.startDate.toFormattedDate())
                duration.formatDefaultBold(context.getString(R.string.work_history_duration), entry.duration)
                summary.formatDefaultBold(context.getString(R.string.work_history_summary), entry.description)
            }

            Picasso.get().load(entry.companyLogo).into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    //nothing to do
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    //nothing to do
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                    val backgroundDrawable = BitmapDrawable(view.resources, bitmap).apply { alpha = 50 }
                    view.container.background = backgroundDrawable
                }
            })
        }
    }
}

class WorkHistoryEntryDiffCallback : DiffUtil.ItemCallback<WorkHistoryEntry>() {
    override fun areItemsTheSame(oldItem: WorkHistoryEntry, newItem: WorkHistoryEntry): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: WorkHistoryEntry, newItem: WorkHistoryEntry): Boolean = oldItem == newItem
}