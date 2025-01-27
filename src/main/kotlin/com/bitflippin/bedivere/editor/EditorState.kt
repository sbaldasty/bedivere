package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.ClaimId
import com.bitflippin.bedivere.model.Database
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.swing.bind.TabbedPaneBinder
import java.io.File

class EditorState(
    var argmap: Database,
    var file: File,
    var hub: Hub,
    var detailTabs: TabbedPaneBinder,
    var selectedClaimId: ClaimId = ClaimId(),
    var selectedSourceId: SourceId = SourceId()
)
