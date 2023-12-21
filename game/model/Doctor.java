package ir.sharif.math.ap2023.mafia.model;

public class Doctor extends Citizen {
    int numberOfSelfSave = 2;

    public Doctor(String name, int id) {
        super(name, id);
    }

    public int getNumberOfSelfSave() {
        return numberOfSelfSave;
    }

    public String action(Player target) {
        if (target == this) {
            if (numberOfSelfSave == 0) {
                return "NO_SAVE";
            }
            numberOfSelfSave--;
        }
        target.setHeal(true);
        target.setAlive(true);
        return "";
    }
}
