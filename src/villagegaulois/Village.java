// Source code is decompiled from a .class file using FernFlower decompiler.
package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
   private String nom;
   private Chef chef;
   private Gaulois[] villageois;
   private int nbVillageois = 0;
   private Marche marche;

   public Village(String nom, int nbVillageoisMaximum, int etals) {
      this.nom = nom;
      this.villageois = new Gaulois[nbVillageoisMaximum];
      this.marche = new Marche(etals);
   }

   public String getNom() {
      return this.nom;
   }

   public void setChef(Chef chef) {
      this.chef = chef;
   }

   public void ajouterHabitant(Gaulois gaulois) {
      if (this.nbVillageois < this.villageois.length) {
         this.villageois[this.nbVillageois] = gaulois;
         ++this.nbVillageois;
      }

   }

   public Gaulois trouverHabitant(String nomGaulois) {
      if (nomGaulois.equals(this.chef.getNom())) {
         return this.chef;
      } else {
         for(int i = 0; i < this.nbVillageois; ++i) {
            Gaulois gaulois = this.villageois[i];
            if (gaulois.getNom().equals(nomGaulois)) {
               return gaulois;
            }
         }

         return null;
      }
   }

   public String afficherVillageois() throws VillageSansChefException {
      if (this.chef == null) {
         throw new VillageSansChefException("Le village " + this.nom + " n'a pas de chef.\n");
      } else {
         StringBuilder chaine = new StringBuilder();
         if (this.nbVillageois < 1) {
            chaine.append("Il n'y a encore aucun habitant au village du chef " + this.chef.getNom() + ".\n");
         } else {
            chaine.append("Au village du chef " + this.chef.getNom() + " vivent les l\u00e9gendaires gaulois :\n");

            for(int i = 0; i < this.nbVillageois; ++i) {
               Gaulois var10001 = this.villageois[i];
               chaine.append("- " + var10001.getNom() + "\n");
            }
         }

         return chaine.toString();
      }
   }

   public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
      StringBuilder chaine = new StringBuilder();
      chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
      int etalLibre = this.marche.trouverEtalLibre();
      this.marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
      ++etalLibre;
      chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " \u00e0 l'\u00e9tal n\u00b0" + etalLibre + ".\n");
      return chaine.toString();
   }

   public String rechercherVendeursProduit(String produit) {
      Etal[] etalsProposantLeProduit = this.marche.trouverEtals(produit);
      StringBuilder chaine = new StringBuilder();
      switch (etalsProposantLeProduit.length) {
         case 0:
            chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au march\u00e9.\n");
            break;
         case 1:
            chaine.append("Seul le vendeur " + etalsProposantLeProduit[0].getVendeur().getNom() + " propose des fleurs au march\u00e9.\n");
            break;
         default:
            chaine.append("Les vendeurs qui proposent des fleurs sont :\n");

            for(int i = 0; i < etalsProposantLeProduit.length; ++i) {
               chaine.append("- " + etalsProposantLeProduit[i].getVendeur().getNom() + "\n");
            }
      }

      return chaine.toString();
   }

   public Etal rechercherEtal(Gaulois vendeur) {
      for(int i = 0; i < this.marche.etals.length; ++i) {
         Etal etal = this.marche.etals[i];
         if (etal.isEtalOccupe() && etal.getVendeur().equals(vendeur)) {
            return etal;
         }
      }

      return null;
   }

   public String partirVendeur(Gaulois vendeur) {
      StringBuilder chaine = new StringBuilder();
      Etal etalDuVendeur = this.rechercherEtal(vendeur);
      chaine.append(etalDuVendeur.libererEtal());
      return chaine.toString();
   }

   public String afficherMarche() {
      StringBuilder chaine = new StringBuilder();
      chaine.append("Le march\u00e9 du village " + this.nom + " poss\u00e8de plusieurs \u00e9tals :\n");
      chaine.append(this.marche.afficherMarche());
      return chaine.toString();
   }
}
