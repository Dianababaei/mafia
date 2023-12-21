package ir.sharif.math.ap2023.mafia.model;

public class Citizen extends Player {
    protected boolean healByDoctor = false;

    @Override
    public boolean isHeal() {
        return healByDoctor;
    }

    @Override
    public void setHeal(boolean heal) {
        this.healByDoctor = heal;
    }

    public Citizen(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        return "";
    }






}
