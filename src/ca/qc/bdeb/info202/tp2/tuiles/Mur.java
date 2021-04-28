package ca.qc.bdeb.info202.tp2.tuiles;

public class Mur extends Tuile{
    public Mur(int x, int y) {
        super(x, y, '#', false);
    }

    @Override
    public void action() {
        System.out.println("Je suis un mur. ");
    }
}
