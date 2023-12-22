package com.example.learndriver.ui.activity


import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.learndriver.R
import com.example.learndriver.databinding.ActivityExamTestBinding

class ExamTestActivity : BaseAct<ActivityExamTestBinding>() {

    override fun initViewBinding(): ActivityExamTestBinding {
        return ActivityExamTestBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.icBack.setOnClickListener(this)
        binding.icTip.setOnClickListener(this)

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (R.id.ic_back == view.id) return startActivity(Intent(this, MainActivity::class.java))
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
}