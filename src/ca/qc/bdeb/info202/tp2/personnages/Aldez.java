package ca.qc.bdeb.info202.tp2.personnages;

import ca.qc.bdeb.info202.tp2.objets.Item;
import ca.qc.bdeb.info202.tp2.objets.PotionForce;
import ca.qc.bdeb.info202.tp2.objets.PotionVie;

public class Aldez extends Entite {

    private static final int VIE_MAXIMALE = 6;
    private static final int ATTAQUE_INITIALE = 1;

    private int nbreCristaux = 0;

    public Aldez(int x, int y) {

        super(x, y, VIE_MAXIMALE, ATTAQUE_INITIALE, '&');
    }

    public void utiliserItem(Item item) {
        if (item.getClass() == PotionVie.class) {
            boirePotionDeVie();
        } else if (item.getClass() == PotionForce.class) {
            boirePotionDeForce();
        } else {
            utiliserCristal();
        }
    }

    private void boirePotionDeVie() {
        this.pointVie = VIE_MAXIMALE;
    }

    private void boirePotionDeForce() {
        this.pointForce++;
    }

    private void utiliserCristal() {
        this.nbreCristaux++;
    }



}
