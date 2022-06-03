package technobot.data.cache;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Leveling {

    private long guild;

    private long user;

    private String color;

    private long timestamp;

    private int level;

    private String background;

    private long xp;

    @BsonProperty("total_xp")
    private long totalXP;

    private double opacity;

    private String accent;

    public Leveling() { }

    public Leveling(long guild, long user) {
        this.guild = guild;
        this.user = user;
        this.color = "#8394eb";
        this.timestamp = 0L;
        this.level = 0;
        this.background = "";
        this.xp = 0L;
        this.totalXP = 0L;
        this.opacity = 0.5;
        this.accent = "#FFFFFF";
    }

    public long getGuild() {
        return guild;
    }

    public void setGuild(long guild) {
        this.guild = guild;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public long getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(long totalXP) {
        this.totalXP = totalXP;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }
}