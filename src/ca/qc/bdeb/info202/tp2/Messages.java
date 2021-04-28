package ca.qc.bdeb.info202.tp2;

import java.util.Scanner;

/**
 * Affiche des messages pré-déterminés
 */
public class Messages {

    public static void afficherIntro() {
        System.out.println(
                "                        /=\\\n" +
                        "                        |||\n" +
                        "                        |||\n" +
                        "                       =====\n" +
                        " _   _              //=======\\\\\n" +
                        "| |_| |__   ___    /// |   | \\\\\\\n" +
                        "| __| '_ \\ / _ \\  ///  |   |  \\\\\\\n" +
                        "| |_| | | |  ___ ///   |   |   \\\\\\            _\n" +
                        " \\__|_| |_|\\__| |    __|  _| _  ___ _ __   __| |\n" +
                        "              | |   / _ \\/ _` |/ _ \\ '_ \\ / _` |\n" +
                        "              | |__|  __/ (_| |  __/ | | | (_| |\n" +
                        "              |_____\\___|\\__, |\\___|_| |_|\\__,_|\n" +
                        "                       | |___/\n" +
                        "                      _|   |\n" +
                        "                ___  / _|  |   :##:    #####:    ##        ########  ########\n" +
                        "               / _ \\| |_   |    ##     #######   ##        ########  ########\n" +
                        "              | (_) |  _|  |   ####    ##  :##:  ##        ##            .###\n" +
                        "               \\___/|_||   |   ####    ##   :##  ##        ##            ###\n" +
                        "                       |   |  :#  #:   ##   .##  ##        ##           :##:\n" +
                        "                       |   |   #::#    ##    ##  ##        #######      ###\n" +
                        "                       |   |  ##  ##   ##    ##  ##        #######     ###\n" +
                        "                       |   |  ######   ##   .##  ##        ##         :##:\n" +
                        "                       |   | .######.  ##   :##  ##        ##         ##\n" +
                        "                       |   | :##  ##:  ##  :##:  ##        ##        ###\n" +
                        "                       |   | ###  ###  #######   ########  ########  ########\n" +
                        "                       |   | ##:  :##  #####:    ########  ########  ########\n" +
                        "                       \\   /\n" +
                        "                   Ocarina of Time.sleep(1000);\n" +
                        "                         V\n");
        System.out.println("Appuyez sur Enter pour commencer");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    public static void afficherVictoire() {
        System.out.println("          Félicitations! Vous avez tous les cristeaux magiques!");
        System.out.println("                        Le monde est sauvé!");
        System.out.println("                           Δ");
        System.out.println("                          Δ Δ");
        System.out.println("                         Δ Δ Δ");
        System.out.println("                          \\o/");
        System.out.println("                           |");
        System.out.println("                          / \\");
    }

    public static void afficherDefaite() {
        System.out.println("          Nooooon! Adlez est morte avant d'avoir collecté les");
        System.out.println("                       cristeaux magiques...");
        System.out.println();
        System.out.println("              Meilleure chance la prochaine fois!");
        System.out.println("                           o ");
        System.out.println("                          /|\\");
        System.out.println("                          / \\");
    }
}
