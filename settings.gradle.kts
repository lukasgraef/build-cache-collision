import java.nio.file.Files

rootProject.name = "reproducer-project"

//code to create the reproducer subprojects
(1..20000).forEach {
    val projectName = "project$it"
    val projectDir = Files.createDirectories(settings.rootDir.toPath().resolve("subprojects/$projectName"))
    include(":$projectName")
    project(":$projectName").projectDir = projectDir.toFile()
    if (!Files.isRegularFile(projectDir.resolve("about.html"))) {
        Files.copy(settings.rootDir.toPath().resolve("about.html"), projectDir.resolve("about.html"))
    }
}
