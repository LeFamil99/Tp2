package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Entite;

public abstract class Tuile {
    protected char symbole;
    protected int x;
    protected int y;
    protected boolean peutMarcherDessus;
    protected Entite entiteDessus;

    public Tuile(int x, int y, char symbole, boolean peutMarcherDessus) {
        this.x = x;
        this.y = y;
        this.symbole = symbole;
        this.peutMarcherDessus = peutMarcherDessus;
    }

    public abstract void action();

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
}
