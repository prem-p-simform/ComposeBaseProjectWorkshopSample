import arkana.GradleKeys

fun secretManifestPlaceholders(flavor: String): Map<String, Any> = buildMap {
    val env = when (flavor) {
        "qa" -> GradleKeys.Qa
        "production" -> GradleKeys.Production
        else -> GradleKeys.Dev
    }
    // put("apiKey", env.apiKey)
}