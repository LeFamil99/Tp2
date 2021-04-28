package ca.qc.bdeb.info202.tp2;

import ca.qc.bdeb.info202.tp2.objets.CristalMagique;
import ca.qc.bdeb.info202.tp2.objets.Item;
import ca.qc.bdeb.info202.tp2.objets.PotionForce;
import ca.qc.bdeb.info202.tp2.objets.PotionVie;
import ca.qc.bdeb.info202.tp2.personnages.Aldez;
import ca.qc.bdeb.info202.tp2.personnages.Monstre;
import ca.qc.bdeb.info202.tp2.tuiles.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Niveau {

    // TUILLES NE POINTE PAS AUX OCCUPANTS.

    private Tuile[][] grille;
    private Aldez aldez;
    private ArrayList<Monstre> monstres;
    private ArrayList<Item> objets;
    private int niveau;
    private final char[] COMMANDES_POSSIBLES = {'w', 'a', 's', 'd', 'c', 'x', 'q'};

    public Niveau(String fichier, Aldez aldez) {
        this.monstres = new ArrayList<>();
        this.aldez = aldez;
        this.objets = new ArrayList<>();
        chargerNiveau(fichier);
    }

    /**
     * Lit le fichier donné et crée une grille de truile conforme aux informations du fichier donné
     * en paramètre.
     * @param fichier
     */
    private void chargerNiveau(String fichier) {

        /* Les monstres et aldez ne sont pas ajoute dans la grille, a la place, il faudra les inserer apres les
         * avoir bouger et avant des des afficher, pour ensuite les retirers. Cela permet de facilement les afficher
         * avec le moins de modification a la grille possible. */

        ArrayList<Tuile[]> grille = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {

            ArrayList<String[]> casesImportantes = lireElementsImportants(lecteur);

            int y = 1;

            String ligne = lecteur.readLine();

            grille.add(new Tuile[ligne.length()]);

            for (int i = 0; i < grille.get(0).length; i++) {
                grille.get(0)[i] = new Mur(i, 0);
            }

            while (ligne != null) {
                ArrayList<Tuile> rangeDeTuile = new ArrayList<>();
                /* Les murs du niveau commence. */
                if (ligne.charAt(0) == '#') {

                    int x = 0;

                    for (char tuile : ligne.toCharArray()) {
                        switch (tuile) {
                            case '#' -> rangeDeTuile.add(new Mur(x, y));
                            default -> chargerCaseVide(rangeDeTuile, x, y, casesImportantes);
                        }

                        x++;
                    }
                    grille.add(rangeDeTuile.toArray(new Tuile[rangeDeTuile.size()]));
                }

                y++;
                ligne = lecteur.readLine();
            }

            this.grille = new Tuile[grille.size()][grille.get(0).length];

            for (int col = 0; col < grille.size(); col++) {
                for (int rang = 0; rang < grille.get(col).length; rang++) {
                    this.grille[col][rang] = grille.get(col)[rang];
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fichier + " n'existe pas! ");
        } catch (IOException e) {
            System.out.println("Probleme lors du traitement du fichier " + fichier + ". ");
        }
    }

    /**
     * Lit le début du fichier des niveaux. Crée Aldez et les monstres et retourne les paramètres des tuiles
     * spéciales comme les trésors, les téléporteurs et les pancartes, afin de les ajouter plus tard au bon
     * endroit dans le niveau.
     * @param lecteur
     * @return le nom et les paramètres des tuiles spéciales à créer.
     */
    private ArrayList<String[]> lireElementsImportants(BufferedReader lecteur) {

        ArrayList<String[]> casesImportantes = new ArrayList<>();

        try {
            String ligne = lecteur.readLine();

            while (ligne != null && ligne.charAt(0) != '#') {
                String[] donnees = ligne.split(":");
                if (donnees.length == 1) {
                    /* Personnage */
                    String[] parametres = donnees[0].split(",");
                    this.aldez.repositionner(Integer.parseInt(parametres[0]),
                            Integer.parseInt(parametres[1]));
                } else if (donnees[0].equalsIgnoreCase("monstre")) {
                    /* Monstre */
                    String[] parametres = donnees[1].split(",");
                    this.monstres.add(new Monstre(Integer.parseInt(parametres[0]), Integer.parseInt(parametres[1]),
                            Integer.parseInt(parametres[2]), Integer.parseInt(parametres[3])));
                } else {
                    /* Rempli la case avec le nom, et puis les parametres */
                    String[] parametres = donnees[1].split(",");
                    String[] caseImportante = new String[parametres.length + 1];
                    for (int i = 0; i < caseImportante.length; i++) {
                        if (i == 0) {
                            caseImportante[i] = donnees[0];
                        } else {
                            caseImportante[i] = parametres[i - 1];
                        }
                    }
                    casesImportantes.add(caseImportante);
                }

                ligne = lecteur.readLine();
            }
        } catch (IOException e) {
            System.err.println("Problème lors de lecteur des personnages et des cases importantes. ");
        }

        return casesImportantes;
    }

    /**
     * Permet d'insérer la bonne tuile, lorsque le charactère dans le fichier est un espace. La tuile
     * peut soit être une pancarte, un trésor ou un téléporteur.
     * @param range
     * @param x
     * @param y
     * @param tuilesPotentielles
     */
    private void chargerCaseVide(ArrayList<Tuile> range, int x, int y, ArrayList<String[]> tuilesPotentielles) {

        for (String[] tuile : tuilesPotentielles) {
            if (tuile[1].equalsIgnoreCase(Integer.toString(x)) &&
                    tuile[2].equalsIgnoreCase(Integer.toString(y))) {
                if (tuile[0].equalsIgnoreCase("pancarte")) {
                    range.add(new Pancarte(x, y, tuile[3]));
                } else if (tuile[0].equalsIgnoreCase("tresor")) {
                    // CRISTAL
                    if (tuile[3].equalsIgnoreCase("CristalMagique")) {
                        this.objets.add(new CristalMagique(x, y));
                    } else if (tuile[3].equalsIgnoreCase("PotionVie")) {
                        this.objets.add(new PotionVie(x, y));
                    } else if (tuile[3].equalsIgnoreCase("PotionForce")) {
                        this.objets.add(new PotionForce(x, y));
                    }
                    range.add(new Tresor(x, y, this.objets.get(this.objets.size() - 1)));
                } else if (tuile[0].equalsIgnoreCase("teleporteur")) {
                    range.add(new Teleporteur(x, y, Integer.parseInt(tuile[3]), Integer.parseInt(tuile[4])));
                }
                tuilesPotentielles.remove(tuile);
                return;
            }
        }

        range.add(new Plancher(x, y));
    }

    public void jouer() {
        char commande = 'a';
        Scanner scanner = new Scanner(System.in);
        do {
            afficher();
            boolean erreur = false;

            System.out.println("Veuillez entrer la commande: ");

            String rawCommand = scanner.nextLine();
            erreur = rawCommand.isEmpty();
            if(erreur) {
                System.out.println("Erreur: Commade invalide");
            } else {
                commande = rawCommand.charAt(0);
                int targetX = 0;
                int targetY = 0;
                switch (commande) {
                    case 'w':
                        targetY = -1;
                        break;

                    case 'a':
                        targetX = -1;
                        break;

                    case 's':
                        targetY = 1;
                        break;

                    case 'd':
                        targetX = 1;
                        break;
                }
                aldez.bouger(targetX, targetY);
            }
        } while (commande != 'q');

    }

    private void afficher () {
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++) {
                boolean entiteDessus = false;
                if(aldez.getX() == j && aldez.getY() == i) {
                    System.out.print(aldez.getSymbole());
                    entiteDessus = true;
                } else {
                    for(Monstre monstre : monstres) {
                        if(monstre.getX() == j && monstre.getY() == i && !entiteDessus) {
                            System.out.print(monstre.getSymbole());
                            entiteDessus = true;
                        }
                    }
                }
                if(!entiteDessus)
                    System.out.print(grille[i][j].getSymbole());
            }
            System.out.println();
        }
    }


}
