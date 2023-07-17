package com.example.doodlenoodle.ui.components

/**
 * THIS CODE IS SUPPLIED BY THE 3RD PARTY SKETCHPAD USED WITHIN THIS APP
 * https://github.com/GetStream/sketchbook-compose
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.doodlenoodle.R
import io.getstream.sketchbook.Sketchbook
import io.getstream.sketchbook.SketchbookController

@Composable
fun SketchbookScreen(
    modifier: Modifier,
    controller: SketchbookController
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 20.dp),
            bitmap = ImageBitmap.imageResource(R.drawable.sketchbook_bg),
            contentDescription = null
        )

        Sketchbook(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    start = 30.dp,
                    top = 60.dp,
                    end = 30.dp,
                    bottom = 30.dp
                ),
            controller = controller,
            backgroundColor = Color.White
        )
    }
}