package ca.qc.bdeb.info202.tp2.personnages;

import ca.qc.bdeb.info202.tp2.tuiles.Tuile;

public class Monstre extends Entite {

    /**
     * Initialise un monstre.
     * @param x position en x
     * @param y position en y
     * @param pointsDeVie
     * @param pointsDeForce
     */
    public Monstre(int x, int y, int pointsDeVie, int pointsDeForce) {
        super(x, y, pointsDeVie, pointsDeForce, '@');
    }

    /**
     * Détermine l'action que le monstre doit effectuer et l'effectue.
     * Le monstre bougera vers adlez s'il est loin de lui et l'attaquera s'il est proche.
     * @param adlez
     * @param grille de tuiles
     */
    public void action(Adlez adlez, Tuile[][] grille) {
        int distX = this.x - adlez.x;
        int distY = this.y - adlez.y;
        if (Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) < 2) {
            adlez.pointVie--;
        } else {
            deplacer(adlez, grille);
        }
    }

    /**
     * Déplacement du monstre vers adlez.
     * @param adlez
     * @param grille de tuiles
     */
    private void deplacer(Adlez adlez, Tuile[][] grille) {

        int nouveauX = this.x;
        int nouveauY = this.y;
        if (adlez.x != this.x) {
            nouveauX = this.x + (adlez.x > this.x ? 1 : -1);
        }
        if (adlez.y != this.y) {
            nouveauY = this.y + (adlez.y > this.y ? 1 : -1);
        }

        if (grille[nouveauY][nouveauX].peutMarcherDessus()) {
            this.x = nouveauX;
            this.y = nouveauY;
        }

        else if (grille[nouveauY][this.x].peutMarcherDessus()) {
            this.y = nouveauY;
        } else if (grille[this.y][nouveauX].peutMarcherDessus()) {
            this.x = nouveauX;
        }
    }



}
