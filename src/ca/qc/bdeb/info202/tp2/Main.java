package ca.qc.bdeb.info202.tp2;

import ca.qc.bdeb.info202.tp2.personnages.Adlez;

public class Main {

    public static void main(String[] args) {

        Adlez a = new Adlez(0, 0);
        Niveau niveau = new Niveau(a);

	    niveau.jouer();

    }
}
