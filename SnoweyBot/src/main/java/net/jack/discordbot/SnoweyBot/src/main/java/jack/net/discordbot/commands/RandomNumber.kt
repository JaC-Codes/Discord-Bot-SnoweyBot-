package jack.net.discordbot.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*

class RandomNumber : ListenerAdapter() {
    var random = Random()
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name.equals("random", ignoreCase = true)) {
            val randomNumber = random.nextInt(1, 100000)
            event.reply("Here is your random number: $randomNumber").queue()
        }
    }
}