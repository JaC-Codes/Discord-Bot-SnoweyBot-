package jack.net.discordbot

import io.github.cdimascio.dotenv.Dotenv
import jack.net.discordbot.commands.Application
import jack.net.discordbot.commands.RandomNumber
import jack.net.discordbot.listeners.ApplicationListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.utils.cache.CacheFlag

object SnoweyBot {
    fun loadBuilder() {
        val config = Dotenv.load()
        val token = config["TOKEN"]
        JDABuilder.createDefault(token).setActivity(Activity.watching("the server very closely!"))
            .setBulkDeleteSplittingEnabled(true)
            .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE).addEventListeners(RandomNumber())
            .addEventListeners(Application())
            .addEventListeners(ApplicationListener())
            .build().updateCommands().addCommands(
                Commands.slash("random", "Get a random number"),
                Commands.slash("apply", "Apply for staff on our server")
                    .setGuildOnly(true)
            ).queue()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        loadBuilder()
    }
}