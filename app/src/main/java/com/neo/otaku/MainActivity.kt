package com.neo.otaku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.neo.otaku.ui.theme.OtakuBackground
import com.neo.otaku.ui.theme.OtakuPlusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtakuPlusTheme {
                OtakuBackground {
                    Text("Olá, Mundo!")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OtakuPlusTheme {
        Text("Olá, Mundo!")
    }
}