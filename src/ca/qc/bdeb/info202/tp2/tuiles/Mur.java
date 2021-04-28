package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Aldez;

public class Mur extends Tuile{
    public Mur(int x, int y) {
        super(x, y, '#', false, false);
    }

    @Override
    public void action(Aldez aldez) {
        System.out.println("Je suis un mur. ");
    }
}
