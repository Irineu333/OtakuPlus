package com.neo.otaku.annotation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class ThemesPreview