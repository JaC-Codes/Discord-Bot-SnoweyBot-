package jack.net.discordbot.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle
import net.dv8tion.jda.api.interactions.modals.Modal

class Application : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val channelMention = event.channel.asMention
        val name = TextInput.create("user-name", "Name", TextInputStyle.SHORT)
            .setMinLength(1).setRequired(true).build()
        val age = TextInput.create("user-age", "age", TextInputStyle.SHORT)
            .setMinLength(1).setRequired(true).build()
        val whyYou = TextInput.create("why-you", "Why should we pick you", TextInputStyle.PARAGRAPH)
            .setMinLength(150).setRequired(true).build()
        val modalApplication = Modal.create("apply-modal", "Application")
            .addActionRows(ActionRow.of(name), ActionRow.of(age), ActionRow.of(whyYou))
            .build()
        if (event.name.equals("apply", ignoreCase = true)) {
            if (event.channel.name != "applications") {
                event.reply("You are in $channelMention, you can only use this in the **#applications** channel!")
                    .queue()
            }
            event.replyModal(modalApplication).queue()
        }
    }
}