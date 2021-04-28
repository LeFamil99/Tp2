package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.objets.Objet;

public class Tresor extends Tuile {
    private Objet obj;
    public Tresor(int x, int y, Objet obj) {
        super(x, y, '$', true);
        this.obj = obj;
    }
}
