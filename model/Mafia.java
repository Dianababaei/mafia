package ir.sharif.math.ap2023.mafia.model;

public  class Mafia extends Player {


    @Override
    public boolean isHeal() {
        return this.heal;
    }

    @Override
    public void setHeal(boolean heal) {
        this.heal = heal;
    }

    public Mafia(String name, int id) {
        super(name, id);
    }


    @Override
    public String action(Player target) {
        target.setAlive(false);
        return "";
    }


}


