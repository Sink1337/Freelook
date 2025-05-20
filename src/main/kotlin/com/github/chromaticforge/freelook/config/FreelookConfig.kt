package com.github.chromaticforge.freelook.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Dropdown
import cc.polyfrost.oneconfig.config.annotations.Info
import cc.polyfrost.oneconfig.config.annotations.KeyBind
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.core.OneKeyBind
import cc.polyfrost.oneconfig.config.data.InfoType
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.libs.universal.UKeyboard
import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils
import com.github.chromaticforge.freelook.Constants
import com.github.chromaticforge.freelook.hook.FreelookHook

object FreelookConfig : Config(
    Mod(
        Constants.NAME,
        ModType.UTIL_QOL,
        "/assets/${Constants.ID}/icon.svg"
    ), Constants.ID + ".json"
) {

    // General

    @Info(text = "Freelook has already been unlocked in hypixel.", type = InfoType.INFO, size = 2)
    var hypixelWarning = false

    @Dropdown(name = "Perspective", options = ["First", "Third", "Reverse"])
    var perspective = 1

    @Switch(name = "Snaplook")
    var snaplook = false

    @Switch(name = "Invert Pitch (Up and Down)")
    var invertPitch = false

    @Switch(name = "Pitch Lock")
    var lockPitch = true

    @Switch(name = "Invert Yaw (Left and Right)")
    var invertYaw = false

    // General

    // Controls

    @KeyBind(name = "Freelook", subcategory = "Controls")
    var keyBind = OneKeyBind(UKeyboard.KEY_LMENU)

    // TODO: Add "both" option that will toggle if pressed quickly and will hold if held
    @Dropdown(name = "Freelook Mode", options = ["Hold", "Toggle"], subcategory = "Controls")
    var mode = 0

    // Controls

    init {
        initialize()

        registerKeyBind(keyBind) { if (mode == 1) FreelookHook.togglePerspective() }

        addDependency("invertPitch", "pitch")
        addDependency("lockPitch", "pitch")
        addDependency("invertYaw", "yaw")
    }
}
