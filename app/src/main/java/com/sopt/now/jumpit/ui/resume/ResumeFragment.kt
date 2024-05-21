package com.sopt.now.jumpit.ui.resume

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentResumeBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class ResumeFragment : BindingFragment<FragmentResumeBinding>(R.layout.fragment_resume) {
    private lateinit var resumeAdapter: ResumeAdapter
    private val viewModel by activityViewModels<ResumeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onResumeHelpBtnClick()
        onAttachmentHelpBtnClick()
        onResumeAddBtnClick()
        initResumeAdapter()
        observeResumeList()
    }

    private fun initResumeAdapter() {
        resumeAdapter = ResumeAdapter(requireActivity().supportFragmentManager)
        binding.rvMyResumeList.run {
            adapter = resumeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeResumeList() {
        viewModel.mockResumeList.observe(viewLifecycleOwner) { resumeList ->
            if (resumeList.isEmpty()) {
                with(binding) {
                    clNoResumeListArea.visibility = View.VISIBLE
                    clMyResumeListArea.visibility = View.INVISIBLE
                }
            } else {
                with(binding) {
                    clNoResumeListArea.visibility = View.INVISIBLE
                    clMyResumeListArea.visibility = View.VISIBLE
                }
            }
            binding.tvMyResumeCount.text = resumeList.size.toString()
            resumeAdapter.setResumeList(resumeList)
        }
    }

    private fun onResumeHelpBtnClick() {
        binding.ivResumeHelp.setOnClickListener {
            val originalImage = binding.ivResumeHelp.drawable

            PopupWindow(requireContext()).apply {
                contentView = layoutInflater.inflate(R.layout.popup_resume, null)
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
                isFocusable = true
                showAsDropDown(binding.ivResumeHelp)

                setOnDismissListener {
                    binding.ivResumeHelp.setImageDrawable(originalImage)
                }

                contentView.setOnTouchListener { _, _ ->
                    dismiss()
                    true
                }
            }
            binding.ivResumeHelp.setImageResource(R.drawable.ic_resume_help_selected)
        }
    }

    private fun onAttachmentHelpBtnClick() {
        binding.ivAttachmentHelp.setOnClickListener {
            val originalImage = binding.ivAttachmentHelp.drawable
            PopupWindow(requireContext()).apply {
                contentView = layoutInflater.inflate(R.layout.popup_attachment, null)
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
                isFocusable = true
                showAsDropDown(binding.ivAttachmentHelp)

                setOnDismissListener {
                    binding.ivAttachmentHelp.setImageDrawable(originalImage)
                }

                contentView.setOnTouchListener { _, _ ->
                    dismiss()
                    true
                }
            }
            binding.ivAttachmentHelp.setImageResource(R.drawable.ic_resume_help_selected)
        }
    }

    private fun onResumeAddBtnClick() {
        binding.ivResumeAdd.setOnClickListener {
            showAddBottomSheetDialog()
        }
    }

    private fun showAddBottomSheetDialog() {
        val addBottomSheetDialog = AddBottomSheetDialogFragment()
        addBottomSheetDialog.show(
            requireActivity().supportFragmentManager,
            addBottomSheetDialog.tag
        )
    }
}