package ca.qc.bdeb.info202.tp2.objets;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Un type d'item.
 * Une potion de force permet à Adlez d'augmenter ses points de force.
 * @see ca.qc.bdeb.info202.tp2.objets.Item
 */
public class PotionForce extends Item {

    public PotionForce(int x, int y) {
        super(x, y);
    }

    @Override
    public void utiliser(Adlez adlez) {
        adlez.setPointForce(adlez.getPointForce() + 1);
    }

}
