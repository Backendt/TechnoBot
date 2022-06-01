package technobot.commands.staff;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import technobot.TechnoBot;
import technobot.commands.Category;
import technobot.commands.Command;
import technobot.util.EmbedUtils;

import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 * Command that puts a channel in slowmode with specified time.
 *
 * @author TechnoVision
 */
public class SlowmodeCommand extends Command {

    public SlowmodeCommand(TechnoBot bot) {
        super(bot);
        this.name = "slowmode";
        this.description = "Sets slowmode time for a channel.";
        this.category = Category.STAFF;
        this.args.add(new OptionData(OptionType.STRING, "time", "The time to set for slowmode"));
        this.permission = Permission.MANAGE_CHANNEL;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        OptionMapping timeOption = event.getOption("time");
        if (timeOption != null) {
            // Retrieve time in seconds from input
            String timeString = timeOption.getAsString();
            int time;
            try {
                try {
                    Duration duration = Duration.parse("PT" + timeString.replaceAll(" ", ""));
                    time = (int) duration.toSeconds();
                    if (time <= 0) throw new NumberFormatException();
                } catch (DateTimeParseException e) {
                    time = Integer.parseInt(timeString);
                    if (time <= 0) throw new NumberFormatException();
                }
            } catch (NumberFormatException e2) {
                // Disable slowmode
                event.getTextChannel().getManager().setSlowmode(0).queue();
                event.getHook().sendMessageEmbeds(EmbedUtils.createDefault(":stopwatch: Slowmode has been disabled from this channel.")).queue();
                return;
            }
            // Set slowmode timer
            if (time > TextChannel.MAX_SLOWMODE) {
                event.getHook().sendMessageEmbeds(EmbedUtils.createError("Time should be less than or equal to **6 hours**.")).queue();
                return;
            }
            event.getTextChannel().getManager().setSlowmode(time).queue();
            event.getHook().sendMessageEmbeds(EmbedUtils.createDefault(":stopwatch: This channel's slowmode has been set to **"+formatTime(time)+"**.")).queue();
        } else {
            // Display current slowmode timer
            int totalSecs = event.getTextChannel().getSlowmode();
            String timeString = formatTime(totalSecs);
            event.getHook().sendMessageEmbeds(EmbedUtils.createDefault(":stopwatch: This channel's slowmode is **"+timeString+"**.")).queue();
        }
    }

    /**
     * Formats seconds into a string 'x hours, x minutes, x seconds'.
     *
     * @param totalSeconds the number of seconds to convert to string.
     * @return a formatted string.
     */
    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        String time = "";
        if (hours > 0) {
            time += hours + " hour";
            if (hours > 1) time += "s";
            if (minutes > 0) time += ", ";
        }
        if (minutes > 0) {
            time += minutes + " minute";
            if (minutes > 1) time += "s";
            if (seconds > 0) time += ", ";
        }
        if (seconds > 0) {
            time += seconds + " second";
            if (seconds > 1) time += "s";
        }
        return time;
    }
}