pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(uri("https://www.jitpack.io"))
        google()
        mavenCentral()
    }
}

rootProject.name = "kotlin_study"
include(":app")
include(":app:gallery")
