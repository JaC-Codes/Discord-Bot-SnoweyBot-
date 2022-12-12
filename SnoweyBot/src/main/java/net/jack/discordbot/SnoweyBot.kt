package net.jack.discordbot

import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import net.jack.discordbot.commands.CommandManager
import net.jack.discordbot.listeners.EventListener
import javax.security.auth.login.LoginException

class SnoweyBot {
    private val config: Dotenv = Dotenv.configure().load()
    private val shardManager: ShardManager

    init {
        val token = config["TOKEN"]
        val builder = DefaultShardManagerBuilder.createDefault(token)
        builder.setStatus(OnlineStatus.ONLINE)
        builder.setActivity(Activity.watching("the server carefully"))
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
        shardManager = builder.build()
        shardManager.addEventListener(EventListener())
        shardManager.addEventListener(CommandManager())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                val bot = SnoweyBot()
            } catch (e: LoginException) {
                println("Login Error")
            }
        }
    }
}