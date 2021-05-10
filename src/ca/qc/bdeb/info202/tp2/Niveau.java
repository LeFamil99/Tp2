package ca.qc.bdeb.info202.tp2;

import ca.qc.bdeb.info202.tp2.objets.CristalMagique;
import ca.qc.bdeb.info202.tp2.objets.Item;
import ca.qc.bdeb.info202.tp2.objets.PotionForce;
import ca.qc.bdeb.info202.tp2.objets.PotionVie;
import ca.qc.bdeb.info202.tp2.personnages.Adlez;
import ca.qc.bdeb.info202.tp2.personnages.Monstre;
import ca.qc.bdeb.info202.tp2.tuiles.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Niveau implements Serializable {

    private Tuile[][] grille;
    private Adlez adlez;
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

    /**
     * Initialise un niveau.
     * @param adlez
     */
    public Niveau(Adlez adlez) {
        this.monstres = new ArrayList<>();
        this.adlez = adlez;
        niveau = 1;
        chargerNiveau(niveau + ".txt");

    }

    /**
     * Lit le fichier donné et crée une grille de truile conforme aux informations du fichier donné
     * en paramètre.
     * @param fichier
     */
    public void chargerNiveau(String fichier) {

        /* Les monstres et Adlez ne sont pas ajoute dans la grille, a la place, il faudra les inserer apres les
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
     * Lit le début du fichier des niveaux. Crée Adlez et les monstres et retourne les paramètres des tuiles
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
                    this.adlez.repositionner(Integer.parseInt(parametres[0]),
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
     * @param range la range de tuile à remplir.
     * @param x
     * @param y
     * @param tuilesPotentielles les données des tuiles spéciales pouvant se trouver sur un espace.
     */
    private void chargerCaseVide(ArrayList<Tuile> range, int x, int y, ArrayList<String[]> tuilesPotentielles) {

        for (String[] tuile : tuilesPotentielles) {
            if (tuile[1].equalsIgnoreCase(Integer.toString(x)) &&
                    tuile[2].equalsIgnoreCase(Integer.toString(y))) {
                if (tuile[0].equalsIgnoreCase("pancarte")) {
                    range.add(new Pancarte(x, y, tuile[3]));
                } else if (tuile[0].equalsIgnoreCase("tresor")) {
                    // CRISTAL
                    Item item = null;
                    if (tuile[3].equalsIgnoreCase("CristalMagique")) {
                        item = new CristalMagique(x, y);
                    } else if (tuile[3].equalsIgnoreCase("PotionVie")) {
                        item = new PotionVie(x, y);
                    } else {
                        item = new PotionForce(x, y);
                    }
                    range.add(new Tresor(x, y, item));
                } else if (tuile[0].equalsIgnoreCase("teleporteur")) {
                    range.add(new Teleporteur(x, y, Integer.parseInt(tuile[3]), Integer.parseInt(tuile[4])));
                }
                tuilesPotentielles.remove(tuile);
                return;
            }
        }

        range.add(new Plancher(x, y));
    }

    /**
     * Permet de jouer à The Legend of Adlez et de lire les commandes de
     * l'utilisateur.
     */
    public void jouer() {
        char commande;
        boolean quitter = false;
        Scanner scanner = new Scanner(System.in);

        Messages.afficherIntro();

        verifierSauvegarde(scanner);

        do {
            System.out.println("Vies: " + adlez.getPointVie() + "/" + Adlez.getVieMaximale() + "\t\t\tForce: " +
                    adlez.getPointForce() + "\t\t\tCrystaux: " + adlez.getNbreCristaux());
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
                    int persoX = adlez.getX();
                    int persoY = adlez.getY();
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
                            for (int[] casesInteragissable : CASES_INTERAGISSABLES) {
                                int interactionX = casesInteragissable[0] + persoX;
                                int interactionY = casesInteragissable[1] + persoY;
                                if (grille[interactionY][interactionX].peutInteragir()) {
                                    grille[interactionY][interactionX].action(adlez);
                                }
                            }
                            break;

                        case 'x':
                            for (int[] cases_interagissable : CASES_INTERAGISSABLES) {
                                int interactionX = cases_interagissable[0] + persoX;
                                int interactionY = cases_interagissable[1] + persoY;
                                for (int k = 0; k < monstres.size(); k++) {
                                    if (monstres.get(k).getX() == interactionX &&
                                            monstres.get(k).getY() == interactionY) {
                                        adlez.attaquer(monstres.get(k));
                                        if (monstres.get(k).getPointVie() <= 0) {
                                            monstres.remove(monstres.get(k));
                                        }
                                    }

                                }
                            }
                            break;

                        case 'q':
                            System.out.println("Voulez-vous sauvegarder la partie? o/n");

                            String reponse = scanner.nextLine();
                            if (reponse.length() > 0 && reponse.charAt(0) == 'o') {
                                sauvegarder();
                            }
                            quitter = true;
                            break;

                    }
                    if(grille[adlez.getY() + targetY][adlez.getX() + targetX].peutMarcherDessus())
                        adlez.bouger(targetX, targetY);

                    for(Monstre monstre : monstres) {
                        monstre.action(adlez, grille);
                    }

                    if (adlez.getPointVie() <= 0) {
                        Messages.afficherDefaite();
                        quitter = true;
                    }

                    if(adlez.getNbreCristaux() == niveau && !quitter) {
                    // AJOUTER CHECK POUR QUITTER
                        if (niveau < 6) {
                            System.out.println("Bravo! Vous avez trouvé le crystal magique! Vous passez au niveau " +
                                ++niveau + ".");
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

    /**
     * Détermine si un fichier de sauvegarde existe déjà et si c'est le cas,
     * demande à l'utilisateur s'il veut reprendre la partie dans le fichier de sauvegarde.
     * @param scanner pour prendre la réponse de l'utilisateur
     */
    private void verifierSauvegarde(Scanner scanner) {

        File sauvegarde = new File(FICHIER_SAUVEGARDE);

        if (sauvegarde.isFile()) {
            System.out.println("Voulez-vous continuer la dernière partie? o/n");

            String reponse = scanner.nextLine();

            if (reponse.length() > 0 && reponse.charAt(0) == 'o') {
                chargerSauvegarde();
            }
        }
    }

    /**
     * Afficher la grille de tuile en placant Adlez et les monstres aux bons endroits.
     */
    public void afficherGrille() {
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++) {
                boolean entiteDessus = false;
                if(adlez.getX() == j && adlez.getY() == i) {
                    System.out.print(adlez.getSymbole());
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

    /**
     * Sauvegarde le niveau.
     * Sérialise le niveau dans le fichier de sauvegarde <partie.sav>
     */
    public void sauvegarder() {
        try (FileOutputStream fos = new FileOutputStream(FICHIER_SAUVEGARDE)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde. ");
        }
    }

    /**
     * Charge la sauvegarde.
     * Set tous les paramètres à l'objet sérialiser dans le fichier <partie.sav>
     */
    public void chargerSauvegarde() {
        try (FileInputStream fis = new FileInputStream(FICHIER_SAUVEGARDE)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Niveau niveau = (Niveau) ois.readObject();
            this.niveau = niveau.niveau;
            this.adlez = niveau.adlez;
            this.monstres = niveau.monstres;
            this.grille = niveau.grille;

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde. ");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur, la classe demande est introuvable. ");
        }
    }
}
