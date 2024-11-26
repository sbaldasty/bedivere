package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Argmap
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.swing.TabManager
import java.io.File

class EditorState(
    var argmap: Argmap,
    var file: File,
    var hub: Hub,
    var tabManager: TabManager,
    var selectedSourceId: SourceId = SourceId(0)
)