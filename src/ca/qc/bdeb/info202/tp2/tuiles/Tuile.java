package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Aldez;
import ca.qc.bdeb.info202.tp2.personnages.Entite;

public abstract class Tuile {
    protected char symbole;
    protected int x;
    protected int y;
    protected boolean peutMarcherDessus;
    protected boolean peutInteragir;
    protected Entite entiteDessus;

    public Tuile(int x, int y, char symbole, boolean peutMarcherDessus, boolean peutInteragir) {
        this.x = x;
        this.y = y;
        this.symbole = symbole;
        this.peutMarcherDessus = peutMarcherDessus;
        this.peutInteragir = peutInteragir;
    }

    public abstract void action(Aldez aldez);

    public boolean getPeutMarcherDessus() {
        return peutMarcherDessus;
    }

    public Entite getEntiteDessus() {
        return entiteDessus;
    }

    public char getSymbole() {
        return symbole;
    }

    public boolean isPeutMarcherDessus() {
        return peutMarcherDessus;
    }

    public boolean isPeutInteragir() {
        return peutInteragir;
    }
}
