package ca.qc.bdeb.info202.tp2.personnages;

import java.io.Serializable;

/**
 * Représentation d'une entité qui peut se déplacer
 * et faire des actions dans le niveau
 */
public abstract class Entite implements Serializable {
    protected int x;
    protected int y;
    protected int pointVie;
    protected int pointForce;
    protected char symbole;

    /**
     * Initialise une entité
     * @param x
     * @param y
     * @param vie Nombre de points de vie de l'entité
     * @param force Nombre de points de force de l'entité
     * @param symbole charactère à afficher pour représenter l'entité
     */
    public Entite(int x, int y, int vie, int force, char symbole) {
        this.x = x;
        this.y = y;
        this.pointVie = vie;
        this.pointForce = force;
        this.symbole = symbole;
    }

    /**
     * Changer la position de l'entité
     * @param x
     * @param y
     */
    public void repositionner(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setPointVie(int pointVie) {
        this.pointVie = pointVie;
    }

    public void setPointForce(int pointForce) {
        this.pointForce = pointForce;
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

    /**
     * Bouger l'entité en x et y
     * @param x
     * @param y
     */
    public void bouger (int x, int y) {
        this.x += x;
        this.y += y;
    }


}
