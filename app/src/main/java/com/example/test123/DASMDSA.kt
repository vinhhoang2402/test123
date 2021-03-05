package com.example.test123

package com.samsung.android.plugin.tv.oobe.pages.customizationservice.ui

import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsung.android.plugin.tv.oobe.R
import com.samsung.android.plugin.tv.oobe.pages.customizationservice.data.model.Agreement
import kotlinx.android.synthetic.main.assisted_tv_custom_service_agreement_list_item.view.*

class AgreementItemAdapter : RecyclerView.Adapter<AgreementItemAdapter.AgreementItemViewHolder>() {

    private val agreementItems = arrayListOf<Agreement>()
    private var onSelectedItem: ((isOneLeastSelectedItem: Boolean, isSelectedAllItem: Boolean) -> Unit)? = null
    private var onNavigateToItemDetail: ((agreement: Agreement) -> Unit)? = null
    private var isOneLeastSelectedItem = false

    fun updateItems(agreementList: List<Agreement>) {
        this.agreementItems.clear()
        this.agreementItems.addAll(agreementList)
        notifyDataSetChanged()
    }

    fun isOneLeastSelectedItem(): Boolean {
        return isOneLeastSelectedItem
    }

    fun getSumOfSelectedAgreement(): Int {
        return agreementItems.filter { it.isIsSelected }.sumBy { it.id }
    }

    fun setSelectedAllItem(isSelectedAll: Boolean) {
        this.isOneLeastSelectedItem = isSelectedAll
        val newAgreementItems = arrayListOf<Agreement>()
        newAgreementItems.addAll(agreementItems)
        updateItems(newAgreementItems.map {
            it.isIsSelected = isSelectedAll
            it
        })
    }

    fun setSelectedItemListener(block: ((isOneLeastSelectedItem: Boolean, isSelectedAllItem: Boolean) -> Unit)? = null) {
        this.onSelectedItem = block
    }

    fun setNavigateToItemDetailListener(block: ((agreement: Agreement) -> Unit)? = null) {
        this.onNavigateToItemDetail = block
    }

    inner class AgreementItemViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView) {

        fun bind(item: Agreement) {
            itemView.assisted_tv_customization_service_list_item_name.apply {
                val wordToSpan = SpannableString(item.guide)
                wordToSpan.setSpan(UnderlineSpan(), 0, item.guide.length, SPAN_EXCLUSIVE_EXCLUSIVE)
                text = wordToSpan
                setOnClickListener {
                    onNavigateToItemDetail?.invoke(item)
                }
            }

            itemView.assisted_tv_customization_service_list_item_check_box.isChecked = item.isIsSelected
            itemView.assisted_tv_customization_service_list_item_check_box.setOnCheckedChangeListener { _, isChecked ->
                item.isIsSelected = isChecked
                isOneLeastSelectedItem = agreementItems.any { it.isIsSelected }
                val isSelectedAllItem = agreementItems.all { it.isIsSelected }
                onSelectedItem?.invoke(isOneLeastSelectedItem, isSelectedAllItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgreementItemViewHolder {
        val parentView = LayoutInflater.from(parent.context)
            .inflate(R.layout.assisted_tv_custom_service_agreement_list_item, parent, false)
        return AgreementItemViewHolder(parentView)
    }

    override fun onBindViewHolder(holder: AgreementItemViewHolder, position: Int) {
        val agreementItem = agreementItems[position]
        holder.bind(agreementItem)
    }

    override fun getItemCount(): Int {
        return agreementItems.size
    }
}