/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.giveawaybot;

import com.jagrosh.jdautilities.commandclient.Command;
import java.awt.Color;
import java.time.OffsetDateTime;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class Constants {
    
    public static final OffsetDateTime START = OffsetDateTime.now();
    public static final int PRIZE_MAX   = 250;
    public static final String TADA     = "\uD83C\uDF89";
    public static final String YAY      = "<:yay:294906617378504704>";
    public static final Color  BLURPLE  = Color.decode("#7289DA");
    public static final String INVITE   = "https://discordapp.com/oauth2/authorize?permissions=347200&scope=bot&client_id=294882584201003009";
    public static final int MIN_TIME    = 10;
    public static final int MAX_TIME    = 60*60*24*7;
    public static final String WEBSITE  = "http://giveawaybot.party";
    public static final String GITHUB   = "https://github.com/jagrosh/GiveawayBot";
    public static final String VERSION  = "2.0";
    public static final String PERMS    = "`Read Messages`, `Write Messages`, `Read Message History`, `Embed Links`, `Use External Emoji`, and `Add Reactions`";
    
    public static final boolean validTime(int seconds)
    {
        return seconds>=MIN_TIME && seconds<=MAX_TIME;
    }
    
    public static final boolean canSendGiveaway(TextChannel channel)
    {
        return channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_READ, Permission.MESSAGE_WRITE, 
                Permission.MESSAGE_HISTORY, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_ADD_REACTION);
    }
    
    public static final boolean canGiveaway(Member member)
    {
        return member.hasPermission(Permission.MANAGE_SERVER) || 
                    member.getRoles().stream().anyMatch(r -> r.getName().equalsIgnoreCase("giveaways"));
    }
    
    public static final Command.Category GIVEAWAY = new Command.Category("Giveaway", event -> {
        if(event.getGuild()==null)
        {
            event.replyError("This command cannot be used in Direct Messages!");
            return false;
        }
        if(canGiveaway(event.getMember()))
            return true;
        event.reply(event.getClient().getError()+" You must have the Manage Server permission, or a role called \"Giveaways\", to use this command!");
        return false;
    });
}
