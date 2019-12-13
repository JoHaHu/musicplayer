rootProject.name = "musicplayer"
include("application")
include("aspects")
include("core")
include("domain")

include("kotlinfx")
include("jupiterfx")

buildCache{
    local {
        isEnabled = true
    }
}
