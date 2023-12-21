package ir.sharif.math.ap2023.mafia.model;

public class Detective extends Citizen {
    public Detective(String name, int id) {
        super(name, id);
    }

    public String action(Player target) {
        if (target instanceof Mafia) {
            if (target instanceof GodFather) {
                if (((GodFather) target).isAsked()) {
                    return "MAFIA";
                } else {
                    ((GodFather) target).setAsked(true);
                    return "NO_MAFIA";
                }
            } else {
                return "MAFIA";
            }
        } else {
            return "NO_MAFIA";
        }
    }
}
