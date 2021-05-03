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

public class Niveau implements Serializable {

    private Tuile[][] grille;
    private Aldez aldez;
    private ArrayList<Monstre> monstres;
    private ArrayList<Item> objets;
    private int niveau;
    private final int[][] CASES_INTERAGISSABLES = {
            {-1, -1},
            {0, -1},
            {1, -1},
            {-1, 0},
            {0, 0},
            {1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
    };

    private final String FICHIER_SAUVEGARDE = "partie.sav";

    public Niveau(Aldez a) {
        this.monstres = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.aldez = a;
        niveau = 1;
        chargerNiveau(niveau + ".txt");

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
                    String[] parametres = donnees[1].replace(", ", "/$%").split(",");
                    for(int i = 0; i < parametres.length; i++)
                        parametres [i] = parametres[i].replace("/$%", ", ");

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
        char commande;
        boolean quitter = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Vies: " + aldez.getPointVie() + "/" + Aldez.getVieMaximale() + "\t\t\tForce: " +
                    aldez.getPointForce() + "\t\t\tCrystaux: " + aldez.getNbreCristaux());
            afficherGrille();
            boolean erreur;

            System.out.println("Veuillez entrer la commande: ");

            String rawCommand = scanner.nextLine();
            erreur = rawCommand.isEmpty();
            if(erreur) {
                System.out.println("Erreur: la commande ne peut pas être vide");
            } else {
                for(int i = 0; i < rawCommand.length() && !quitter; i++) {
                    commande = rawCommand.charAt(i);
                    int targetX = 0;
                    int targetY = 0;
                    int persoX = aldez.getX();
                    int persoY = aldez.getY();
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

                        case 'c':
                            for(int j = 0; j < CASES_INTERAGISSABLES.length; j++) {
                                int interactionX = CASES_INTERAGISSABLES[j][0] + persoX;
                                int interactionY = CASES_INTERAGISSABLES[j][1] + persoY;
                                if(grille[interactionY][interactionX].isPeutInteragir()) {
                                    grille[interactionY][interactionX].action(aldez);
                                }
                            }
                            break;

                        case 'x':
                            for(int j = 0; j < CASES_INTERAGISSABLES.length; j++) {
                                int interactionX = CASES_INTERAGISSABLES[j][0] + persoX;
                                int interactionY = CASES_INTERAGISSABLES[j][1] + persoY;
                                for(int k = 0; k < monstres.size(); k++) {
                                    if(monstres.get(i).getX() == interactionX && monstres.get(i).getY() == interactionY) {
                                        aldez.attaquer(monstres.get(i));
                                        if(monstres.get(i).getPointVie() <= 0) {
                                            monstres.remove(monstres.get(i));
                                        }
                                    }

                                }
                            }
                            break;

                        case 'q':
                            quitter = true;
                            break;

                    }
                    if(grille[aldez.getY() + targetY][aldez.getX() + targetX].getPeutMarcherDessus())
                        aldez.bouger(targetX, targetY);

                    for(Monstre monstre : monstres) {
                        monstre.action(aldez, grille);
                        if (aldez.getPointVie() <= 0) {
                            Messages.afficherDefaite();
                            quitter = true;
                        }
                    }

                    if(aldez.getNbreCristaux() == niveau) {
                        System.out.println("Bravo! Vous avez trouvé le crystal magique! Vous passez au niveau " + niveau++ + ".");
                        if (niveau <= 6) {
                            chargerNiveau(niveau + ".txt");
                        } else {
                            Messages.afficherVictoire();
                            quitter = true;
                        }
                    }
                }

            }
        } while (!quitter);

    }

    private void afficherGrille() {
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

    public void sauvegarder() {
        try (FileOutputStream fos = new FileOutputStream(FICHIER_SAUVEGARDE)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde. ");
        }
    }

    public void chargerNiveau() {
        try (FileInputStream fis = new FileInputStream(FICHIER_SAUVEGARDE)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Niveau niveau = (Niveau) ois.readObject();
            this.niveau = niveau.niveau;
            this.aldez = niveau.aldez;
            this.objets = niveau.objets;
            this.monstres = niveau.monstres;
            this.grille = niveau.grille;

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde. ");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur, la classe demande est introuvable. ");
        }
    }
}
