package me.asiimwedismas.androidretrofitfileupload

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import me.asiimwedismas.androidretrofitfileupload.ui.theme.AndroidRetrofitFileUploadTheme
import java.io.File

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidRetrofitFileUploadTheme {
                val viewModel = viewModel<FileViewModel>()

                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        /**
                         * this logic should be in the data layer, not going to do any
                         * best practises as this isn't production code */
                        val file = File(cacheDir, "image.png")
                        file.createNewFile()
                        file.outputStream().use {
                            assets.open("screenshot.png").copyTo(it)
                        }
                        viewModel.uploadFile(file)
                    }) {
                        Text(text = "Click to upload")
                    }
                }
            }
        }
    }
}