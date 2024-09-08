package com.zerox.navi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zerox.navi.databinding.NaviNoTitleRowBinding
import com.zerox.navi.databinding.NaviRowBinding

class NaviAdapter internal constructor(
    private var selectedDestination: Int,
    private val items: List<Navi>,
    private val itemClickListener: (Int) -> Unit,
    private val selectedColor: Int,
    private val nonSelectedColor: Int,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            0 ->
                ItemNoTitleViewHolder(
                    NaviNoTitleRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    ),
                )

            else ->
                ItemViewHolder(
                    NaviRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    ),
                )
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = items[position]
        when (holder) {
            is ItemNoTitleViewHolder ->
                holder.bind(
                    item = item as Navi.ItemNoTitle,
                    isSelected = selectedDestination == item.destination,
                )

            is ItemViewHolder ->
                holder.bind(
                    item = item as Navi.Item,
                    isSelected = selectedDestination == item.destination,
                )

            else -> throw IllegalArgumentException()
        }
        holder.itemView.setOnClickListener { itemClickListener.invoke(item.destination) }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        when {
            items[position] is Navi.ItemNoTitle -> 0
            else -> 1
        }

    fun updateSelectedDestination(id: Int) {
        selectedDestination = id
        notifyDataSetChanged()
    }

    inner class ItemViewHolder internal constructor(
        private val bindingNaviRowBinding: NaviRowBinding,
    ) : RecyclerView.ViewHolder(bindingNaviRowBinding.root) {
        fun bind(
            item: Navi.Item,
            isSelected: Boolean,
        ) {
            bindingNaviRowBinding.titleTv.attachText(title = item.title, isSelected = isSelected)
            bindingNaviRowBinding.iconIv.attachIcons(icon = item.icon, isSelected = isSelected)
        }
    }

    inner class ItemNoTitleViewHolder internal constructor(
        private val binNoTitleRowBinding: NaviNoTitleRowBinding,
    ) : RecyclerView.ViewHolder(binNoTitleRowBinding.root) {
        fun bind(
            item: Navi.ItemNoTitle,
            isSelected: Boolean,
        ) {
            binNoTitleRowBinding.iconIv.attachIcons(icon = item.icon, isSelected = isSelected)
        }
    }

    fun ImageView.attachIcons(
        icon: Int,
        isSelected: Boolean,
    ) {
        setImageResource(icon)
        this.isSelected = isSelected
    }

    fun TextView.attachText(
        title: String,
        isSelected: Boolean,
    ) {
        text = title
        if (isSelected) {
            setTextColor(selectedColor)
        } else {
            setTextColor(nonSelectedColor)
        }
    }
}
