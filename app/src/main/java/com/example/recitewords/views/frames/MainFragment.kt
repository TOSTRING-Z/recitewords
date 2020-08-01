package com.example.recitewords.views.frames

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.files.fileChooser
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.example.recitewords.databinding.FrameMainBinding
import com.example.recitewords.utlis.InjectorUtils
import com.example.recitewords.viewmodel.ScreenSlidePageViewModel
import com.example.recitewords.viewmodel.UserViewModel
import com.example.recitewords.views.activity.ScreenSlidePagerActivity


class MainFragment : Fragment() {



    private val screenSlidePageViewModel:ScreenSlidePageViewModel by viewModels {
        InjectorUtils.provideScreenSlidePageFactory()
    }
    private val userViewModel:UserViewModel by viewModels {
        InjectorUtils.provideUserFactory()
    }


    private lateinit var binding: FrameMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FrameMainBinding.inflate(layoutInflater)



        userViewModel.mDayWordsNum.observe(this, Observer { count ->
            count?.let {
                binding.dayWords.text = "${it}/每次"
                ScreenSlidePageViewModel.getInstance().get(it, true)
            }
        })

        screenSlidePageViewModel.mConutRemenber.observe(this, Observer { count ->
            count?.let {
                binding.allWords.text = "单词量：${it}/${screenSlidePageViewModel.mCount.value}"
            }
        })
        screenSlidePageViewModel.mCount.observe(this, Observer { count ->
            count?.let {
                binding.allWords.text = "单词量：${screenSlidePageViewModel.mConutRemenber.value}/${it}"
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getPermission()

        binding.dayWords.setOnClickListener {
            activity?.let {
                MaterialDialog(it).show {
                    listItemsSingleChoice(
                        items = listOf(
                            "10",
                            "20",
                            "30",
                            "50",
                            "100",
                            "200",
                            "300"
                        )
                    ) { dialog, index, text ->
                        UserViewModel.getInstance().setDayWordsNum(text.toString().toInt())
                    }
                }
            }
        }
        binding.remenber.setOnClickListener {
            val intent = Intent(activity, ScreenSlidePagerActivity::class.java)
            intent.putExtra("dayWordsNum", userViewModel.mDayWordsNum.value);
            startActivity(intent)
        }
        binding.importFile.setOnClickListener {
            MaterialDialog(activity!!).show {
                fileChooser(activity!!, initialDirectory = getExternalStorageDirectory()) { dialog, file ->
                    ScreenSlidePageViewModel.getInstance().insert(file.absolutePath)
                    Toast.makeText(
                        activity!!,
                        "词库导入成功！",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }
        return binding.root
    }

    //获取权限
    private fun getPermission() {
        //申请权限
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(READ_EXTERNAL_STORAGE),
            1
        )

        //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!, READ_EXTERNAL_STORAGE))
            kotlin.run {
                Toast.makeText(
                    activity!!,
                    "请开通相关权限，否则无法正常使用本应用！",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}
