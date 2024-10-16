package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalMarche) {
	    this.nom = nom;
	    villageois = new Gaulois[nbVillageoisMaximum];
	    this.marche = new Marche(nbEtalMarche); 
	}

	public class Marche {
		private Etal[] etals;

		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal >= 0 && indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			} else {
				System.out.println("L'indice saisi est incorrect");
			}
		}

		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			int nbEtalContientProduit = 0;

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalContientProduit++;
				}
			}

			Etal[] etalscontient = new Etal[nbEtalContientProduit];
			int index = 0;

			for (int j = 0; j < etals.length; j++) {
				if (etals[j].contientProduit(produit)) {
					etalscontient[index] = etals[j];
					index++;
				}
			}

			return etalscontient;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() != null && etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			return null;
		}

		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalVide++;
				}
			}
			if (nbEtalVide > 0) {
				chaine.append("Il reste ").append(nbEtalVide).append(" étals non utilisés.\n");
			}
			return chaine.toString();
		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public String installerVendeur(Gaulois vendeur , String produit , int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtalLibre = marche.trouverEtalLibre();
		if (indiceEtalLibre != -1) {
			marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
			chaine.append(vendeur.getNom());
			chaine.append(" cherche un endroit pour vendre ");
			chaine.append(nbProduit);
			chaine.append(" ");
			chaine.append(produit);
			chaine.append(".\n");
			chaine.append("Le vendeur ");
			chaine.append(vendeur.getNom());
			chaine.append(" vend des ");
			chaine.append(produit);
			chaine.append(" à l'étal n°");
			chaine.append(indiceEtalLibre+1);
			chaine.append(".\n");
		}else {
			chaine.append("Aucune étal n'est disponible.\n");
		}
		return chaine.toString();	
		}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsAvecProduit = marche.trouverEtals(produit);
		
		if (etalsAvecProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des ");
	        chaine.append(produit);
	        chaine.append(" au marché.\n");
		} else {
			chaine.append("Les vendeurs qui proposent des ");
	        chaine.append(produit);
	        chaine.append(" sont :\n");
			for (int i = 0; i < etalsAvecProduit.length; i++) {
			    chaine.append("- ");
			    chaine.append(etalsAvecProduit[i].getVendeur().getNom());
			    chaine.append("\n");
			}

		}
		return chaine.toString();
	}
	
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder sortie = new StringBuilder();
		sortie.append(marche.trouverVendeur(vendeur).libererEtal());
		
		return sortie.toString();
	}
	
	public String afficherMarche() {
	    StringBuilder chaine = new StringBuilder();
	    chaine.append("Le marché du village \"");
	    chaine.append(nom);
	    chaine.append("\" possède plusieurs étals :\n");
	    chaine.append(marche.afficherMarche());
	    return chaine.toString();
	}

	
	


}
