package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

public class Mur extends Tuile{
    public Mur(int x, int y) {
        super(x, y, '#', false, false);
    }

    @Override
    public void action(Adlez adlez) {
        System.out.println("Je suis un mur. ");
    }
}
