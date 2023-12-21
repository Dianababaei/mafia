package ir.sharif.math.ap2023.mafia.logic;
//package model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ir.sharif.math.ap2023.mafia.model.*;

public class GameState {


    private List<Player> allPlayers;
    private List<Player> mafiaPlayers = new ArrayList<>();
    private List<Player> civilPlayers = new ArrayList<>();
    private int numMafia, numCivil;
    private List<Player> deadPlayerInLastRound = new ArrayList<>();
    public Set<Player> deadPlayers = new HashSet<>();

    protected int round = 0;
    private boolean isDay = true;
    private boolean isJokerDeadInDay = false;
    private Player joker;
    private boolean isGodfatherDead = true;
    private Player executedLastRound;
    private List<Player> winnerList = new ArrayList<>();

    public GameState(List<Player> Players) {
        this.allPlayers = Players;
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) instanceof Mafia) {
                mafiaPlayers.add(allPlayers.get(i));
            } else if (allPlayers.get(i) instanceof Citizen) {
                civilPlayers.add(allPlayers.get(i));
            } else {
                joker = allPlayers.get(i);
            }
        }
        this.numCivil = civilPlayers.size();
        this.numMafia = this.mafiaPlayers.size();
    }

    public List<Player> getAlivePlayers() {
        List<Player> alivePlayersList = new ArrayList<>();

        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i).isAlive()) {
                alivePlayersList.add(allPlayers.get(i));
            }
        }
        return alivePlayersList;
    }

    public List<Player> allPlayers() {
        return allPlayers;
    }

    public void nextDay() {
        isDay = true;
        executedLastRound = null;
        List<Player> alivePlayers = getAlivePlayers();

        Player negonBakhtPlayer = alivePlayers.get(0);

        for (int i = 0; i < alivePlayers.size(); i++) {
            if (alivePlayers.get(i) instanceof GodFather) {
                isGodfatherDead = false;
            }

            if (negonBakhtPlayer.getVoteMafia() <= alivePlayers.get(i).getVoteMafia()) {
                negonBakhtPlayer = alivePlayers.get(i);
            }
        }

        if (isGodfatherDead) {
            if (!negonBakhtPlayer.isHeal()) {
                negonBakhtPlayer.setAlive(false);
                deadPlayerInLastRound.add(negonBakhtPlayer);
            }
            for (int i = 0; i < alivePlayers.size(); i++) {
                if (alivePlayers.get(i).isSniperChosen() && !alivePlayers.get(i).isHeal()) {
                    alivePlayers.get(i).setAlive(false);
                    deadPlayerInLastRound.add(alivePlayers.get(i));
                }
            }
        } else {
            for (int i = 0; i < alivePlayers.size(); i++) {
                if ((alivePlayers.get(i).isSniperChosen() || alivePlayers.get(i).isMafiaChosen()) && !alivePlayers.get(i).isHeal()) {
                    alivePlayers.get(i).setAlive(false);
                    deadPlayerInLastRound.add(alivePlayers.get(i));
                }
            }
        }

        doWeHaveWinner();

        for (int i = 0; i < alivePlayers.size(); i++) {
            alivePlayers.get(i).setVoteMafia(0);
            alivePlayers.get(i).setVoteCounter(0);
        }

        this.round++;
    }

    public void nextNight() {
        isDay = false;
        executedLastRound = maxVotePlayer();
        deadPlayerInLastRound.clear();
        doWeHaveWinner();

        for (Player player : allPlayers) {
            player.setVoteMafia(0);
            player.setMafiaChosen(false);
            player.setSniperChosen(false);
            player.setHeal(false);
            player.setMute(false);
        }
    }

    public boolean isGodfatherDead() {
        return isGodfatherDead;
    }

    public void setGodfatherDead(boolean godfatherDead) {
        isGodfatherDead = godfatherDead;
    }

    public int getRound() {
        return round;
    }

    public boolean isDay() {
        return isDay;
    }

    public List<Player> getDeadPlayersInLastRound() {
        return deadPlayerInLastRound;
    }

    public Player getSilentPlayerInLastRound() {
        List<Player> alivePlayers = getAlivePlayers();
        for (int i = 0; i < alivePlayers.size(); i++) {
            if (alivePlayers.get(i).isMute()) {
                return alivePlayers.get(i);
            }
        }
        return null;
    }

    public List<Player> getWinners() {
        return winnerList;
    }

    public Player maxVotePlayer() {
        List<Player> alivePlayers = getAlivePlayers();

        for (int i = 0; i < alivePlayers.size(); i++) {

            if (alivePlayers.get(i).getVoteCounter() > alivePlayers.size() / 2) {
                alivePlayers.get(i).setAlive(false);
                return alivePlayers.get(i);
            }
        }

        return null;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public List<Player> getMafiaPlayers() {
        return mafiaPlayers;
    }

    public void setMafiaPlayers(List<Player> mafiaPlayers) {
        this.mafiaPlayers = mafiaPlayers;
    }

    public List<Player> getCivilPlayers() {
        return civilPlayers;
    }

    public void setCivilPlayers(List<Player> civilPlayers) {
        this.civilPlayers = civilPlayers;
    }

    public int getNumMafia() {
        return numMafia;
    }

    public void setNumMafia(int numMafia) {
        this.numMafia = numMafia;
    }

    public int getNumCivil() {
        return numCivil;
    }

    public void setNumCivil(int numCivil) {
        this.numCivil = numCivil;
    }


    public List<Player> getDeadPlayerInLastRound() {
        return deadPlayerInLastRound;
    }

    public void setDeadPlayerInLastRound(List<Player> deadPlayerInLastRound) {
        this.deadPlayerInLastRound = deadPlayerInLastRound;
    }

    public Set<Player> getDeadPlayers() {
        return deadPlayers;
    }

    public void setDeadPlayers(Set<Player> deadPlayers) {
        this.deadPlayers = deadPlayers;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setDay(boolean day) {
        isDay = day;
    }

    public boolean isJokerDeadInDay() {
        return isJokerDeadInDay;
    }

    public void setJokerDeadInDay(boolean jokerDeadInDay) {
        isJokerDeadInDay = jokerDeadInDay;
    }

    public Player getJoker() {
        return joker;
    }

    public void setJoker(Player joker) {
        this.joker = joker;
    }

    public Player getExecutedLastRound() {
        return executedLastRound;
    }

    public void setExecutedLastRound(Player executedLastRound) {
        this.executedLastRound = executedLastRound;
    }

    public List<Player> getWinnerList() {
        return winnerList;
    }

    public void setWinnerList(List<Player> winnerList) {
        this.winnerList = winnerList;
    }

    public Player getExecutedPlayer() {
        return executedLastRound;
    }

    public void doWeHaveWinner() {
        int numMafia = 0;
        int numCivil = 0;
        List<Player> alivePlayer = getAlivePlayers();

        if (executedLastRound instanceof Joker) {
            winnerList.add(joker);
        } else {

            for (int i = 0; i < alivePlayer.size(); i++) {
                if (alivePlayer.get(i) instanceof Mafia) {
                    numMafia++;
                } else if (alivePlayer.get(i) instanceof Citizen) {
                    numCivil++;
                }
                //this is mean the player is joker
                else {
                    numCivil++;
                }

            }
        }
        if (numMafia >= numCivil) {
            winnerList = mafiaPlayers;
        } else if (numMafia == 0) {
            winnerList = civilPlayers;
        }
    }

}