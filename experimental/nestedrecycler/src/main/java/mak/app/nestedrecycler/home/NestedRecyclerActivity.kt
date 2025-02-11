package mak.app.nestedrecycler.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mak.app.nestedrecycler.detail.DetailActivity
import mak.app.nestedrecycler.R
import mak.app.nestedrecycler.databinding.ActivityNestedRecyclerBinding


class NestedRecyclerActivity : AppCompatActivity() {

    private val viewModel by viewModels<NestedViewModel>()
    private val adapter: MainRvAdapter? = null

    private var _binding: ActivityNestedRecyclerBinding? = null
    private val binding: ActivityNestedRecyclerBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityNestedRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {  }
            }
        }
    }


    private fun startActivity_DetailContents(
        sharedTransitionImageView: View, imgUrl: String?,
        sharedTransitionTitleTextView: View, title: String?,
        sharedTransitionCategoryTextView: View?, category: String?
    ) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.BUNDLE_KEY_CONTENTS_THUMBNAIL, imgUrl)
        intent.putExtra(DetailActivity.BUNDLE_KEY_CONTENTS_TITLE, title)
        intent.putExtra(DetailActivity.BUNDLE_KEY_CONTENTS_CATEGORY, category)

        val options: ActivityOptionsCompat
        if (sharedTransitionCategoryTextView != null && TextUtils.isEmpty(category)) {
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(
                    sharedTransitionImageView,
                    getString(R.string.transition_name_thumbnail)
                ),
                Pair(
                    sharedTransitionTitleTextView,
                    getString(R.string.transition_name_title)
                ),
                Pair(
                    sharedTransitionCategoryTextView,
                    getString(R.string.transition_name_category)
                )
            )
        } else {
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair<View, String>(
                    sharedTransitionImageView,
                    getString(R.string.transition_name_thumbnail)
                ),
                Pair<View, String>(
                    sharedTransitionTitleTextView,
                    getString(R.string.transition_name_title)
                )
            )
        }
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}