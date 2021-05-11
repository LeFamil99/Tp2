package ca.qc.bdeb.info202.tp2.objets;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

/**
 * Un type d'item.
 * Un cristal magique permet à Adlez de finir un niveau.
 * @see ca.qc.bdeb.info202.tp2.objets.Item
 */
public class CristalMagique extends Item {

    public CristalMagique(int x, int y) {
        super(x, y);

    }

    @Override
    public void utiliser(Adlez adlez) {
        adlez.gagnerCristal();
    }

}
