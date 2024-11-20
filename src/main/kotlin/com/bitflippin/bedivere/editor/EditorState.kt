package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Argmap
import java.io.File

data class EditorState(
    var argmap: Argmap,
    var file: File,
    val hub: Hub
)