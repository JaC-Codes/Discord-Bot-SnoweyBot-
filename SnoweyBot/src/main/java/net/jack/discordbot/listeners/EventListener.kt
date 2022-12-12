package net.jack.discordbot.listeners

import net.dv8tion.jda.api.entities.channel.Channel
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import kotlin.random.Random

class EventListener : ListenerAdapter() {


    override fun onMessageReactionAdd(event: MessageReactionAddEvent) {
        val user = event.user
        val emoji = event.reaction.emoji.asReactionCode
        val channelMention = event.channel.asMention
        val message = user!!.asTag + " reacted to a message with " + emoji + " in the " + channelMention + " channel."
        event.guild.defaultChannel!!.asTextChannel().sendMessage(message).queue()
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message.contentRaw
        if (message == "!random") {
            val randomNumber = Random.nextInt(0,10000)
            event.channel.sendMessageFormat("Your random number is: $randomNumber").queue()
        }
    }

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        val user = event.user.asTag
        val numberOfMembers = event.guild.memberCount
        val serverName = event.guild.name
        var txtChannel: TextChannel = event.guild.getTextChannelsByName("Welcome", true)[0]
        txtChannel.sendMessage("Welcome $user to $serverName (Member Count: $numberOfMembers)").queue()
    }
}