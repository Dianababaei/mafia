package ir.sharif.math.ap2023.mafia.model;
import ir.sharif.math.ap2023.mafia.logic.GameState;

public class Sniper extends Citizen{
    int NumberOfBulletsLeft = 2;

    public Sniper(String name, int id) {
        super(name, id);
    }

    public int getNumberOfBulletsLeft() {

        return NumberOfBulletsLeft;
    }

    public String action(Player target){
        if(getNumberOfBulletsLeft()== 0){
            return "NO_BULLETS";
        }

        target.setSniperChosen(true);
        NumberOfBulletsLeft --;
        return "";
    }

    // TODO

}

