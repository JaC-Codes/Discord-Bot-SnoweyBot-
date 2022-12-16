package jack.net.discordbot.listeners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ApplicationListener : ListenerAdapter() {
    override fun onModalInteraction(event: ModalInteractionEvent) {
        if (event.modalId.equals("apply-modal", ignoreCase = true)) {
            val name = event.getValue("user-name")!!.asString
            val age = event.getValue("user-age")!!.asString
            val whyYou = event.getValue("why-you")!!.asString
            event.reply("Application sent!").queue()
            val embed = EmbedBuilder()
            embed.setTitle("$name's application")
            embed.addField("Name:", name, false)
            embed.addField("Age: ", age, false)
            embed.addField("Why should we pick you?", whyYou, false)

            val textChannel: TextChannel = event.jda.getTextChannelsByName("staff-applications", true)[0]
            textChannel.sendMessageEmbeds(embed.build()).queue()
        }
    }
}