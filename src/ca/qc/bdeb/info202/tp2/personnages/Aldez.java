package ca.qc.bdeb.info202.tp2.personnages;

import ca.qc.bdeb.info202.tp2.objets.Item;
import ca.qc.bdeb.info202.tp2.objets.PotionForce;
import ca.qc.bdeb.info202.tp2.objets.PotionVie;

public class Aldez extends Entite {

    private final static int VIE_MAXIMALE = 6;
    private final static int ATTAQUE_INITIALE = 1;

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
        System.out.println(pointVie);
    }

    private void boirePotionDeForce() {
        this.pointForce++;
        System.out.println(pointForce);
    }

    private void utiliserCristal() {
        this.nbreCristaux++;
    }

    public static int getVieMaximale() {
        return VIE_MAXIMALE;
    }

    public int getNbreCristaux() {
        return nbreCristaux;
    }
}
