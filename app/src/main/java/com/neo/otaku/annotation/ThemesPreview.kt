package com.neo.otaku.annotation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "dark", group = "themes", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "light", group = "themes",uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class ThemesPreview