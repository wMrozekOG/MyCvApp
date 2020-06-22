package com.example.mycvapp.view.history

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycvapp.database.WorkHistoryEntry

@BindingAdapter("data")
fun RecyclerView.setData(entries: List<WorkHistoryEntry>?) {
    entries?.let { nonNullEntries ->
        adapter?.run {
            (this as WorkHistoryAdapter).submitList(nonNullEntries)
        } ?: run {
            layoutManager = LinearLayoutManager(context)
            adapter = WorkHistoryAdapter().apply {
                submitList(nonNullEntries)
            }
        }
    }
}