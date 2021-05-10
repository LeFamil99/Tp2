package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

import java.io.Serializable;

/**
 * Représentation d'une case d'un niveau.
 * Il y a une sous classe pour tous les types de tuiles.
 */
public abstract class Tuile implements Serializable {
    protected char symbole;
    protected int x;
    protected int y;
    protected boolean peutMarcherDessus;
    protected boolean peutInteragir;

    /**
     * Initialise une tuile.
     * @param x
     * @param y
     * @param symbole charactère à afficher pour représenter la tuile
     * @param peutMarcherDessus permet de savoir si Adlez ou un monstre peut occuper la tuile
     * @param peutInteragir permet de savoir si Adlez peut interagir avec la tuile
     */
    public Tuile(int x, int y, char symbole, boolean peutMarcherDessus, boolean peutInteragir) {
        this.x = x;
        this.y = y;
        this.symbole = symbole;
        this.peutMarcherDessus = peutMarcherDessus;
        this.peutInteragir = peutInteragir;
    }

    /**
     * Interaction avec Adlez.
     * @param adlez
     */
    public abstract void action(Adlez adlez);

    public boolean peutMarcherDessus() {
        return peutMarcherDessus;
    }

    public char getSymbole() {
        return symbole;
    }

    public boolean peutInteragir() {
        return peutInteragir;
    }
}
