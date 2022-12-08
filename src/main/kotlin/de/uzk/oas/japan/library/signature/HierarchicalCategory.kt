package de.uzk.oas.japan.library.signature

interface HierarchicalCategory {
    val parent: HierarchicalCategory?

    val keywordLiterals: Array<out String>

    val signatureIdPart: String

    companion object {
        tailrec fun computeParentAncestry(
            node: HierarchicalCategory?,
            accu: List<HierarchicalCategory> = emptyList()
        ): List<HierarchicalCategory> {
            if (node == null)
                return accu

            return computeParentAncestry(node.parent, accu + node)
        }
    }
}
