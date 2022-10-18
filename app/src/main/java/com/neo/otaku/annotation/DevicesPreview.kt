package com.neo.otaku.annotation

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "default", group = "devices", device = Devices.DEFAULT)
@Preview(name = "tablet", group = "devices", device = Devices.TABLET)
annotation class DevicesPreview()
