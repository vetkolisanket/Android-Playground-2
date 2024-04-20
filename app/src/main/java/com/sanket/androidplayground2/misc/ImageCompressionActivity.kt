package com.sanket.androidplayground2.misc

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.compose.ComposeTutorialTheme

class ImageCompressionActivity : AppCompatActivity() {

    private val workManager: WorkManager by lazy { WorkManager.getInstance(applicationContext) }
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            ComposeTutorialTheme {
                val workerResult = viewModel.workId?.let { id ->
                    workManager.getWorkInfoByIdLiveData(id).observeAsState().value
                }
                LaunchedEffect(key1 = workerResult?.outputData) {
                    workerResult?.outputData?.let {
                        val filePath = it.getString(PhotoCompressionWorker.KEY_RESULT_PATH)
                        filePath?.let {
                            val bitmap = BitmapFactory.decodeFile(it)
                            viewModel.updateCompressedBitmap(bitmap)
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    viewModel.uncompressedUri?.let {
                        Text(text = "Uncompressed Photo", color = Color.White)
                        AsyncImage(model = it, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    viewModel.compressedBitmap?.let {
                        Text(text = "Compressed Photo", color = Color.White)
                        Image(bitmap = it.asImageBitmap(), contentDescription = null)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        } ?: return

        viewModel.updateUncompressedUri(uri)

        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
            .setInputData(
                workDataOf(
                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L
                )
            ).build()
        viewModel.updateWorkId(request.id)
        workManager.enqueue(request)
    }
}