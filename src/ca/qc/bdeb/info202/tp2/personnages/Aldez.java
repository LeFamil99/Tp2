package ca.qc.bdeb.info202.tp2.personnages;

public class Aldez extends Entite {

    private static final int VIE_MAXIMALE = 6;
    private static final int ATTAQUE_INITIALE = 1;

    public Aldez(int x, int y, int a, int b) {

        super(x, y, VIE_MAXIMALE, ATTAQUE_INITIALE, '&');
    }

    // CHANGER DE MANIERE QUE CE SOIR PLUS LOGIQUE
    public void boirePotionDeVie() {
        this.pointVie = VIE_MAXIMALE;
    }

    public void boirePotionDeForce() {
        this.pointForce++;
    }

}
