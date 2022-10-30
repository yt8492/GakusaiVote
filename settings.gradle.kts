pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "GakusaiVote"
include("web", "server")
include("common")
