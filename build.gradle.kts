rootProject.subprojects.forEach { p: Project ->
    p.plugins.apply("base")
    p.tasks.register<DefaultTask>("collision") {
        val aboutHtml = p.file("about.html")
        inputs.file(aboutHtml).withPathSensitivity(PathSensitivity.NONE)
        val modifiedHtml = p.layout.buildDirectory.file("modified.html")
        outputs.file(modifiedHtml)
        outputs.cacheIf { true }

        doFirst {
            val content = aboutHtml.readText()
            modifiedHtml.get().asFile.writeText(content.replace("xyz", p.name))
        }
    }
}
