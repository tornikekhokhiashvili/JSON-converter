package com.epam.functions

@Suppress("ClassName")
object arr {
    /**
     * Creates json array from given [elements]. Each element
     * in the resulting json array must be converted applying the following rules:
     * - Any json primitive (number, string, etc.) must be used as is. In some cases numeric values
     * can't be represented without any transformations, in such cases string representation should
     * be used
     * - If element is an instance of [JsonValue], it must be used as is
     * - Else convert element using [Any]'s [toString] method
     * For these, you should use [toJsonValue] extension of [Any]
     */
    operator fun get(vararg elements: Any?): JsonArray {
        val jsonElement = elements.map { element ->
            when (element) {
                null -> JsonNull
                is JsonValue -> element
                is Number -> JsonPrimitive.of(element)
                is Char -> JsonPrimitive.of(element.toString())
                is Boolean -> JsonPrimitive.of(element)
                else -> JsonPrimitive.of(element.toString())
            }
        }
        return JsonArray.of(jsonElement)
    }

    /**
     * Creates a JSON array from given [elements]. Each element
     * in the resulting JSON array must be converted applying the following rules:
     * - Any JSON primitive (number, string, etc.) must be used as is. In some cases, numeric values
     * can't be represented without any transformations, in such cases, string representation should
     * be used
     * - If an element is an instance of [JsonValue], it must be used as is
     * - Else, convert the element using [Any]'s [toString] method
     * For these, you should use [toJsonValue] extension of [Any]
     */
    operator fun get(elements: Iterable<Any?>): JsonArray {
        val jsonElements = mutableListOf<JsonValue>()
        for (element in elements) {
            val jsonValue: JsonValue = when (element) {
                null -> JsonNull
                is JsonValue -> element
                is Number -> JsonPrimitive.of(element)
                is Char -> JsonPrimitive.of(element.toString())
                is Boolean -> JsonPrimitive.of(element)
                else -> JsonPrimitive.of(element.toString())
            }
            jsonElements.add(jsonValue)
        }
        return JsonArray.of(jsonElements)
    }
}

/**
 * Creates a JSON object using the supplied configuration [block]
 */
fun obj(
    block: JsonObjectCreator.() -> Unit
): JsonObject {
    val creator = JsonObjectCreator()
    creator.block()
    return creator.toJsonTree()
}
