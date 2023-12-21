package ir.sharif.math.ap2023.mafia.model;

public abstract class Player {
    protected int id;
    protected String name;
    protected boolean alive = true;

    protected boolean mute = false;
    protected boolean heal = false;

    protected int voteCounter = 0;
    protected Player Player;
    protected boolean mafiaChosen = false;
    protected boolean sniperChosen = false;

    public boolean isMafiaChosen() {
        return mafiaChosen;
    }

    public void setMafiaChosen(boolean mafiaChosen) {
        this.mafiaChosen = mafiaChosen;
    }

    public boolean isSniperChosen() {
        return sniperChosen;
    }

    public void setSniperChosen(boolean sniperChosen) {
        this.sniperChosen = sniperChosen;
    }

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public boolean isMaxVoted() {
        return isMaxVoted;
    }

    public void setMaxVoted(boolean maxVoted) {
        isMaxVoted = maxVoted;
    }

    protected boolean isMaxVoted = false;

    public int getVoteMafia() {
        return voteMafia;
    }

    public void setVoteMafia(int voteMafia) {
        this.voteMafia = voteMafia;
    }

    protected int voteMafia;

    public boolean isHeal() {
        return heal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return Player;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean setAlive(boolean alive) {
        this.alive = alive;
        return alive;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }


    public void vote(Player target) {
        target.voteCounter++;
    }

    public int getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(int numVote) {
        this.voteCounter = numVote;
    }

    public String action(Player target) {
        return "";
    }

    public void setHeal(boolean heal) {
        this.heal = heal;
    }

}

