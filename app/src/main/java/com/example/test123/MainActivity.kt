package com.example.test123

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        package com.samsung.android.plugin.tv.oobe.pages.customizationservice.ui

        import androidx.lifecycle.LiveData
                import androidx.lifecycle.MutableLiveData
                import androidx.lifecycle.ViewModel
                import com.samsung.android.plugin.tv.oobe.pages.customizationservice.data.CustomizationActionData
                import com.samsung.android.plugin.tv.oobe.pages.customizationservice.data.CustomizationActionType
                import com.samsung.android.plugin.tv.oobe.pages.customizationservice.data.CustomizationServiceRspParser
                import com.samsung.android.plugin.tv.oobe.pages.customizationservice.data.model.Agreement

        class CustomizationServiceViewModel : ViewModel() {

            var percentStep: Int = 0
            private val _uIDataLiveData = MutableLiveData<CustomizationServiceRspParser>()
            val uIDataLiveData: LiveData<CustomizationServiceRspParser> get() = _uIDataLiveData

            private val _selectedAgreementItem = MutableLiveData<Agreement>()
            val selectedAgreementItemLiveData: LiveData<Agreement> = _selectedAgreementItem

            private val _sendActionIntoTv = MutableLiveData<CustomizationActionData>()
            val sendActionIntoTvLiveData: LiveData<CustomizationActionData> = _sendActionIntoTv

            fun setUIData(data: CustomizationServiceRspParser) {
                _uIDataLiveData.postValue(data)
            }

            fun selectAgreementItem(item: Agreement) {
                _selectedAgreementItem.postValue(item)
            }

            fun sendActionIntoTv(type: CustomizationActionType, sumOfAgreement: Int = 0) {
                _sendActionIntoTv.postValue(CustomizationActionData(type, sumOfAgreement))
            }
        }
    }
}