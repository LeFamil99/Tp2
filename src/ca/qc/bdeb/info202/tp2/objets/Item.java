package ca.qc.bdeb.info202.tp2.objets;

import ca.qc.bdeb.info202.tp2.personnages.Aldez;

import java.io.Serializable;

public abstract class Item implements Serializable {

    protected int x, y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void utiliser(Aldez aldez);

}
