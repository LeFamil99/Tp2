package ca.qc.bdeb.info202.tp2.objets;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Un type d'item.
 * Une potion de vie permet à Adlez de restaurer tous ses points de vie.
 * @see ca.qc.bdeb.info202.tp2.objets.Item
 */
public class PotionVie extends Item {

    public PotionVie(int x, int y) {
        super(x, y);
    }

    @Override
    public void utiliser(Adlez adlez) {
        adlez.setPointVie(Adlez.getVieMaximale());
    }

}
