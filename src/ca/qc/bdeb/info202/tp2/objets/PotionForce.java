package ca.qc.bdeb.info202.tp2.objets;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

public class PotionForce extends Item {

    public PotionForce(int x, int y) {
        super(x, y);
    }

    @Override
    public void utiliser(Adlez adlez) {
        adlez.renforcer();
    }

}
