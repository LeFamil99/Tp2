package ca.qc.bdeb.info202.tp2.personnages;

public abstract class Entite {
    private int x;
    private int y;
    private int pointVie;
    private int pointForce;
    private char symbole;


    public Entite(int x, int y, int vie, int force, char symbole) {
        this.x = x;
        this.y = y;
        this.pointVie = vie;
        this.pointForce = force;
        this.symbole = symbole;
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
}
