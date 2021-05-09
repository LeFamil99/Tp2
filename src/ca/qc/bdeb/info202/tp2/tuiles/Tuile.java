package ca.qc.bdeb.info202.tp2.tuiles;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;
import ca.qc.bdeb.info202.tp2.personnages.Entite;

import java.io.Serializable;

public abstract class Tuile implements Serializable {
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

    public abstract void action(Adlez adlez);

    public boolean peutMarcherDessus() {
        return peutMarcherDessus;
    }

    public Entite getEntiteDessus() {
        return entiteDessus;
    }

    public char getSymbole() {
        return symbole;
    }

    public boolean isPeutInteragir() {
        return peutInteragir;
    }
}
