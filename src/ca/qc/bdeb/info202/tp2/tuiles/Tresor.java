package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.objets.Item;
import ca.qc.bdeb.info202.tp2.personnages.Aldez;

public class Tresor extends Tuile {
    private Item obj;
    private boolean estOuvert = false;
    public Tresor(int x, int y, Item obj) {
        super(x, y, '$', false, true);
        this.obj = obj;
    }

    @Override
    public void action(Aldez aldez) {

    }

    @Override
    public char getSymbole() {
        return estOuvert ? '_' : super.symbole;
    }
}
