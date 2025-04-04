import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

val DependencyHandler.homeModule: ProjectDependency get() = project(":feature:home")
val DependencyHandler.tvModule: ProjectDependency get() = project(":feature:tv")
val DependencyHandler.vodModule: ProjectDependency get() = project(":feature:vod")
val DependencyHandler.settingsModule: ProjectDependency get() = project(":feature:settings")
val DependencyHandler.libraryModule: ProjectDependency get() = project(":feature:library")
val DependencyHandler.mainModule: ProjectDependency get() = project(":feature:main")
val DependencyHandler.videoDetails: ProjectDependency get() = project(":feature:horizontal-video-detail")
val DependencyHandler.playerManagerModule: ProjectDependency get() = project(":feature:player-manager")
val DependencyHandler.commentModule: ProjectDependency get() = project(":feature:comment")
val DependencyHandler.playlistModule: ProjectDependency get() = project(":feature:playlist")
val DependencyHandler.shareModule: ProjectDependency get() = project(":feature:share")
val DependencyHandler.programsModule: ProjectDependency get() = project(":feature:programs")
val DependencyHandler.verticalVideoModule: ProjectDependency get() = project(":feature:vertical-video-detail")
val DependencyHandler.actionMenuModule: ProjectDependency get() = project(":feature:action-menu")
val DependencyHandler.childCategoryModule: ProjectDependency get() = project(":feature:child-category")
val DependencyHandler.metadataModule: ProjectDependency get() = project(":feature:metadata")
val DependencyHandler.resolutionModule: ProjectDependency get() = project(":feature:resolution")
val DependencyHandler.demoModule: ProjectDependency get() = project(":feature:demo")
val DependencyHandler.dubbingModule: ProjectDependency get() = project(":feature:dubbing")
val DependencyHandler.profileModule: ProjectDependency get() = project(":feature:profile")
val DependencyHandler.subtitleModule: ProjectDependency get() = project(":feature:subtitle")
val DependencyHandler.authenticationModule: ProjectDependency get() = project(":feature:authentication")
val DependencyHandler.ssoModule: ProjectDependency get() = project(":feature:sso")
val DependencyHandler.featureFlagModule: ProjectDependency get() = project(":feature:feature-flag")
val DependencyHandler.devToolsModule: ProjectDependency get() = project(":feature:dev-tools")

val DependencyHandler.coreUiModule: ProjectDependency get() = project(":core:ui")
val DependencyHandler.coreDomainModule: ProjectDependency get() = project(":core:domain")
val DependencyHandler.coreResourceModule: ProjectDependency get() = project(":core:resource")
val DependencyHandler.coreDataModule: ProjectDependency get() = project(":core:data")
val DependencyHandler.coreNavigatorModule: ProjectDependency get() = project(":core:navigator")
val DependencyHandler.coreNetworkModule: ProjectDependency get() = project(":core:network")
val DependencyHandler.coreCommonModule: ProjectDependency get() = project(":core:common")
val DependencyHandler.coreDesignSystemModule: ProjectDependency get() = project(":core:designsystem")
val DependencyHandler.coreModelModule: ProjectDependency get() = project(":core:model")
val DependencyHandler.coreLocalModule: ProjectDependency get() = project(":core:local")
