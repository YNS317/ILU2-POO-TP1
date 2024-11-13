package histoire;  // Assure-toi que c'est dans le bon package

import villagegaulois.Village;  // Importation de Village
import villagegaulois.VillageSansChefException;  // Importation de l'exception
import personnages.Gaulois;
import personnages.Chef;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        // Création d'un village sans chef
        Village village = new Village("Village des Gaulois", 10, 5);

        try {
            // Tentative d'affichage des villageois sans avoir de chef
            String villageois = village.afficherVillageois();
            System.out.println(villageois);
        } catch (VillageSansChefException e) {
            // Gestion de l'exception : affichage d'un message d'erreur et de la pile d'appels
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Fin du test");
    }
}
