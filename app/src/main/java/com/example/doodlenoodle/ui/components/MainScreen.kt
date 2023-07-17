package com.example.doodlenoodle.ui.components

/**
 * THIS CODE IS SUPPLIED BY THE 3RD PARTY SKETCHPAD USED WITHIN THIS APP
 * https://github.com/GetStream/sketchbook-compose
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doodlenoodle.R
import com.example.doodlenoodle.models.DoodleNoodleViewModel
import io.getstream.sketchbook.ColorPickerPaletteIcon
import io.getstream.sketchbook.PaintColorPalette
import io.getstream.sketchbook.PaintColorPaletteTheme
import io.getstream.sketchbook.rememberSketchbookController

@Composable
fun DrawScreen(noodleViewModel: DoodleNoodleViewModel,navController: NavController) {
    val sketchbookController = rememberSketchbookController()

    LaunchedEffect(Unit) {
        sketchbookController.setPaintStrokeWidth(23f)
    }

    Column {

        SketchbookScreen(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, fill = false),
            controller = sketchbookController
        )

        PaintColorPalette(
            modifier = Modifier.padding(6.dp),
            controller = sketchbookController,
            theme = PaintColorPaletteTheme(borderColor = MaterialTheme.colors.onPrimary),
            initialSelectedIndex = 3,
            onColorSelected = { _, _ -> sketchbookController.setPaintShader(null) },
            header = {
                ColorPickerPaletteIcon(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(6.dp),
                    controller = sketchbookController,
                    bitmap = ImageBitmap.imageResource(R.drawable.palette)
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        SketchbookControlMenu(controller = sketchbookController)

        Spacer(modifier = Modifier.height(20.dp))
    }
}