package net.jack.discordbot.commands

import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands

class CommandManager : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val command = event.name
        if (command.equals("members", ignoreCase = true)) {
            var member = event.guild?.memberCount
            event.reply("**Member count is: $member**").queue()
        }
    }

    override fun onReady(event: ReadyEvent) {
        val commandData: MutableList<CommandData> = ArrayList<CommandData>()
        commandData.add(Commands.slash("members", "List the member count!"))
        event.jda.updateCommands().addCommands(commandData)
    }
}