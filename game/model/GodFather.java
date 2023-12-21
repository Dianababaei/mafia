package ir.sharif.math.ap2023.mafia.model;

public class GodFather extends Mafia {

    protected boolean asked = false;

    public GodFather(String name, int id) {
        super(name, id);
    }


    public void setAsked(boolean isAsked) {
        this.asked = isAsked;
    }


    public boolean isAsked() {
        return asked;
    }

    public String action(Player target) {
        target.setMafiaChosen(true);
        return "";
    }

}
