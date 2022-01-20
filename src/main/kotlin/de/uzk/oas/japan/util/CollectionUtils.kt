package de.uzk.oas.japan.util

object CollectionUtils {
    fun <K, I, V> Map<K, I>.reverseMap(unfold: (I) -> Iterator<V>): Map<V, List<K>> {
        val inverse = mutableMapOf<V, MutableList<K>>()

        for ((key, groupValue) in this) {
            for (value in unfold(groupValue)) {
                inverse.getOrPut(value) { mutableListOf() }.add(key)
            }
        }

        return inverse
    }

    fun <K, V> Map<K, V>.reverseMap() = reverseMap { iterator { yield(it) } }
    fun <K, V> Map<K, Iterable<V>>.reverseMultiMap() = reverseMap { it.iterator() }

    fun <K, V> Map<K, Iterable<V>>.unfoldMultiMap() =
        entries.flatMap { (key, value) -> value.map { key to it } }

    fun <A, B, C> Pair<A, Pair<B, C>>.unfoldPair() = Triple(first, second.first, second.second)

    fun <K, V> Map<K, List<V>>.getOrEmpty(key: K) = get(key).orEmpty()
    fun <K> Map<K, Int>.getOrZero(key: K) = get(key) ?: 0

    fun <K, V1, V2> Map<K, V1>.mapValuesNotNull(transform: (Map.Entry<K, V1>) -> V2?): Map<K, V2> {
        return mapNotNull { entry ->
            transform(entry)?.let { entry.key to it }
        }.toMap()
    }

    fun <K, V> Map<K, V?>.filterValuesNotNull() = mapValuesNotNull { it.value }
    inline fun <K, reified R> Map<K, *>.filterValuesIsInstance() = mapValuesNotNull { it.value as? R }

    inline fun <K1, K2, V> Map<K1, V>.mapKeysNotNull(transform: (Map.Entry<K1, V>) -> K2?): Map<K2, V> {
        return mapNotNull { entry ->
            transform(entry)?.let { it to entry.value }
        }.toMap()
    }

    inline fun <reified R, V> Map<*, V>.filterKeysIsInstance() = mapKeysNotNull { it.key as? R }

    fun <T> T.repeatAsList(howManyTimes: Int) = List(howManyTimes) { this }
}