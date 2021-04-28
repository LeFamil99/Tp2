package ca.qc.bdeb.info202.tp2.personnages;

import ca.qc.bdeb.info202.tp2.tuiles.Tuile;

public class Monstre extends Entite {

    public Monstre(int x, int y, int pointsDeVie, int pointsDeForce) {
        super(x, y, pointsDeVie, pointsDeForce, '@');
    }

    public void deplacer(Aldez aldez, Tuile[][] grille) {

        if (aldez.x != this.x) {
            int nouveauX = this.x + aldez.x > this.x ? 1 : -1;
        }
        if (aldez.y != this.y) {
            this.y += aldez.y > this.y ? 1 : -1;
        }

    }

}
