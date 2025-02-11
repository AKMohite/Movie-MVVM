package mak.app.nestedrecycler.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.transition.addListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import mak.app.nestedrecycler.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding
        get() = _binding!!

    companion object {
        val TAG: String = DetailActivity::class.java.simpleName
        val BUNDLE_KEY_CONTENTS_THUMBNAIL: String = TAG + "_BUNDLE_KEY_CONTENTS_THUMBNAIL"
        val BUNDLE_KEY_CONTENTS_TITLE: String = TAG + "_BUNDLE_KEY_CONTENTS_TITLE"
        val BUNDLE_KEY_CONTENTS_CATEGORY: String = TAG + "_BUNDLE_KEY_CONTENTS_CATEGORY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val imgThumbnail = intent.getStringExtra(BUNDLE_KEY_CONTENTS_THUMBNAIL)
        val title = intent.getStringExtra(BUNDLE_KEY_CONTENTS_TITLE)
        val category = intent.getStringExtra(BUNDLE_KEY_CONTENTS_CATEGORY)

        binding.detailTvTitle.text = title

        binding.detailTvCategory.text = category

        if (imgThumbnail != null) {
            Picasso.get()
                .load(imgThumbnail)
                .fit()
                .centerCrop()
                .into(binding.detailIv)
        }

        val sharedElementTransition = window.sharedElementEnterTransition;
        sharedElementTransition.addListener {}

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}