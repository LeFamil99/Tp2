package ca.qc.bdeb.info202.tp2;

import ca.qc.bdeb.info202.tp2.tuiles.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Niveau {
    private Tuile[][] grille;
    private int niveau;

    public Niveau(int niveauACharger) {
        chargerNiveau(niveauACharger + ".txt");
    }

    public void afficherNiveau() {
        for(Tuile[] ligne : grille) {
            for(Tuile tuile : ligne) {
                if(tuile.getEntiteDessus() == null) {
                    System.out.print(tuile.getSymbole());
                } else {
                    System.out.print(tuile.getEntiteDessus()/*.getSymbole()*/);
                }
            }
            System.out.println();
        }
    }

    public void jouer() {
        afficherNiveau();
    }

    private void chargerNiveau(String fichier) {
        // Deal avec les monstres et les objets, ainsi que leurs parametres
        // ...
        ArrayList<Tuile[]> grille = new ArrayList<>();
        try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
            String ligne = lecteur.readLine();
            int y = 0;
            while (ligne != null) {
                ArrayList<Tuile> rangeDeTuille = new ArrayList<>();
                // Check pour savoir que c'est le layout du niveau commence
                if (ligne.charAt(0) == '#') {
                    int x = 0;
                    for (char tuile : ligne.toCharArray()) {
                        switch (tuile) {
                            case '#' -> rangeDeTuille.add(new Mur(x, y));
                            case '!' -> rangeDeTuille.add(new Pancarte(x, y));
                            case '*' -> rangeDeTuille.add(new Teleporteur(x, y));
                            case '$' -> rangeDeTuille.add(new Tresor(x, y));
                            default -> rangeDeTuille.add(new Plancher(x, y));
                        }

                        x++;
                    }
                    grille.add(rangeDeTuille.toArray(new Tuile[rangeDeTuille.size()]));
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
}
