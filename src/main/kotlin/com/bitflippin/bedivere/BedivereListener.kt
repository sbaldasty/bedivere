package com.bitflippin.bedivere

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.Ideology
import com.bitflippin.bedivere.model.IdeologyClaim
import com.bitflippin.bedivere.model.RelatedClaim
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportClaim
import com.bitflippin.bedivere.model.SupportSource

interface BedivereListener {

    fun onClaimAdded(e: Claim) = Unit

    fun onClaimUpdated(old: Claim, new: Claim) = Unit

    fun onClaimRemoved(e: Claim) = Unit

    fun onClaimSourceAdded(e: ClaimSource) = Unit

    fun onClaimSourceUpdated(old: ClaimSource, new: ClaimSource) = Unit

    fun onClaimSourceRemoved(e: ClaimSource) = Unit

    fun onIdeologyAdded(e: Ideology) = Unit

    fun onIdeologyUpdated(old: Ideology, new: Ideology) = Unit

    fun onIdeologyRemoved(e: Ideology) = Unit

    fun onIdeologyClaimAdded(e: IdeologyClaim) = Unit

    fun onIdeologyClaimUpdated(old: IdeologyClaim, new: IdeologyClaim) = Unit

    fun onIdeologyClaimRemoved(e: IdeologyClaim) = Unit

    fun onRelatedClaimAdded(e: RelatedClaim) = Unit

    fun onRelatedClaimUpdated(old: RelatedClaim, new: RelatedClaim) = Unit

    fun onRelatedClaimRemoved(e: RelatedClaim) = Unit

    fun onSourceAdded(e: Source) = Unit

    fun onSourceUpdated(old: Source, new: Source) = Unit

    fun onSourceRemoved(e: Source) = Unit

    fun onSupportAdded(e: Support) = Unit

    fun onSupportUpdated(old: Support, new: Support) = Unit

    fun onSupportRemoved(e: Support) = Unit

    fun onSupportClaimAdded(e: SupportClaim) = Unit

    fun onSupportClaimUpdated(old: SupportClaim, new: SupportClaim) = Unit

    fun onSupportClaimRemoved(e: SupportClaim) = Unit

    fun onSupportSourceAdded(e: SupportSource) = Unit

    fun onSupportSourceUpdated(old: SupportSource, new: SupportSource) = Unit

    fun onSupportSourceRemoved(e: SupportSource) = Unit

    fun onContextualClaimChanged() = Unit

    fun onContextualSupportChanged() = Unit

}