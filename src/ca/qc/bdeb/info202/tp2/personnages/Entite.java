package ca.qc.bdeb.info202.tp2.personnages;

public abstract class Entite {
    protected int x;
    protected int y;
    protected int pointVie;
    protected int pointForce;
    protected char symbole;


    public Entite(int x, int y, int vie, int force, char symbole) {
        this.x = x;
        this.y = y;
        this.pointVie = vie;
        this.pointForce = force;
        this.symbole = symbole;
    }

    public void repositionner(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPointVie() {
        return pointVie;
    }

    public int getPointForce() {
        return pointForce;
    }

    public char getSymbole() {
        return symbole;
    }

    public void bouger (int x, int y) {
        this.x += x;
        this.y += y;
    }
}
