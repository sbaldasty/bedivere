package com.bitflippin.bedivere

import com.bitflippin.bedivere.model.Argmap
import java.io.File

data class EditorState(
    var argmap: Argmap,
    var file: File
)