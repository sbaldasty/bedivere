package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Argmap
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.swing.bind.TabbedPaneBinder
import java.io.File

class EditorState(
    var argmap: Argmap,
    var file: File,
    var hub: Hub,
    var detailTabs: TabbedPaneBinder,
    var selectedSourceId: SourceId = SourceId(0)
)