package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Aldez;

public class Pancarte extends Tuile{
    private String texte;
    public Pancarte(int x, int y, String texte) {
        super(x, y, '!', true, true);
        this.texte = texte;
    }

    @Override
    public void action(Aldez aldez) {
        System.out.println(texte);
    }
}
