package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Aldez;

public class Teleporteur extends Tuile {
    private int destinationY;
    private int destinationX;

    public Teleporteur(int x, int y, int destinationX, int destinationY) {
        super(x, y, '*', true, true);
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    @Override
    public void action(Aldez aldez) {

    }
}
