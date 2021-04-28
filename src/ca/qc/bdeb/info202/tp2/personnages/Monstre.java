package ca.qc.bdeb.info202.tp2.personnages;

import ca.qc.bdeb.info202.tp2.tuiles.Tuile;

public class Monstre extends Entite {

    public Monstre(int x, int y, int pointsDeVie, int pointsDeForce) {
        super(x, y, pointsDeVie, pointsDeForce, '@');
    }

    public void deplacer(Aldez aldez, Tuile[][] grille) {

        int nouveauX = this.x;
        int nouveauY = this.y;
        if (aldez.x != this.x) {
            nouveauX = this.x + aldez.x > this.x ? 1 : -1;
        }
        if (aldez.y != this.y) {
            nouveauY = this.y + aldez.y > this.y ? 1 : -1;
        }

        if (grille[nouveauX][nouveauY].getPeutMarcherDessus()) {
            this.x = nouveauX;
            this.y = nouveauY;
        }
        /*
        else if (grille[nouveauY][this.x].getPeutMarcherDessus()) {
            this.x = nouveauX;
        } else if (grille[this.y][nouveauX].getPeutMarcherDessus()) {
            this.y = nouveauY;
        }
        */
    }
}
