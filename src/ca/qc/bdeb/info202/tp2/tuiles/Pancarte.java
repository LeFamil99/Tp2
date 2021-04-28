package ca.qc.bdeb.info202.tp2.tuiles;

public class Pancarte extends Tuile{
    private String texte;
    public Pancarte(int x, int y, String texte) {
        super(x, y, '!', true);
        this.texte = texte;
    }
}
