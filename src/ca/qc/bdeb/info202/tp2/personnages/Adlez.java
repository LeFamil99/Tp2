package ca.qc.bdeb.info202.tp2.personnages;

import ca.qc.bdeb.info202.tp2.objets.Item;

/**
 * Représente une Adlez, le héro.
 * Est contrôler par le joueur.
 * @see ca.qc.bdeb.info202.tp2.personnages.Entite
 */
public class Adlez extends Entite {

    private final static int VIE_MAXIMALE = 6;
    private final static int ATTAQUE_INITIALE = 1;

    private int nbreCristaux = 0;

    /**
     * Initialise Adlez
     * @param x position en x
     * @param y position en y
     */
    public Adlez(int x, int y) {

        super(x, y, VIE_MAXIMALE, ATTAQUE_INITIALE, '&');
    }

    /**
     * Permet d'utiliser un item trouvé.
     * @param item item à utiliser, trouvé dans un trésor.
     */
    public void utiliserItem(Item item) {
        item.utiliser(this);
    }

    public static int getVieMaximale() {
        return VIE_MAXIMALE;
    }

    /**
     * Obtention d'un cristal. Le nombre de cristal d'Adlez augmente de 1.
     */
    public void gagnerCristal() {
        nbreCristaux++;
    }

    public int getNbreCristaux() {
        return nbreCristaux;
    }

    /**
     * Attaque un monstre et baisse ses points de vies par les points de force d'Adlez.
     * @param monstre monstre à attaquer
     */
    public void attaquer(Monstre monstre) {
        monstre.pointVie -= this.pointForce;
    }
}
