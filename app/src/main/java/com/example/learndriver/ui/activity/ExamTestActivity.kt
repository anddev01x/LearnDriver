package com.example.learndriver.ui.activity


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.activity.viewModels
import com.example.learndriver.R
import com.example.learndriver.databinding.ActivityExamTestBinding
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class ExamTestActivity : BaseAct<ActivityExamTestBinding>() {
    private val viewModel: AllQuestionViewModel by viewModels()

    override fun initViewBinding(): ActivityExamTestBinding {
        return ActivityExamTestBinding.inflate(layoutInflater)
    }

    override fun initViews() {}

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (R.id.ic_back == view.id) return super.onBackPressed()
        if (R.id.ic_tip == view.id) {
            onShowDialog()
        }
    }

    private fun onShowDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_rules_exam)
        val window: Window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttributes = window.attributes
        windowAttributes.gravity = Gravity.CENTER

        val btCancel: Button = dialog.findViewById(R.id.bt_cancel)
        btCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun onBackPressed() {

    }
}