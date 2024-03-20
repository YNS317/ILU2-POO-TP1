package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal(); 
		etal.libererEtal(); 
		System.out.println("Fin du test");
		
		Village village = new Village("le village des irréductibles", 10, 5);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.installerVendeur(bonemine, "fleurs", 20);
		
		try {
		etal.acheterProduit(2,assurancetourix);
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage())    ;
		}catch(IllegalStateException e) {
			System.out.println(e.getMessage()) ;
		}
	}

}
