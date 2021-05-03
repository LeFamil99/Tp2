package ca.qc.bdeb.info202.tp2;

import ca.qc.bdeb.info202.tp2.personnages.Aldez;

public class Main {

    public static void main(String[] args) {

        Aldez a = new Aldez(0, 0);
        Niveau niveau = new Niveau(a);
        niveau.sauvegarder();
	    niveau.jouer();
    }
}
