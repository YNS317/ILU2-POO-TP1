// Source code is decompiled from a .class file using FernFlower decompiler.
package villagegaulois;

import personnages.Gaulois;

class Village$Marche {
   private Etal[] etals;

   private Village$Marche(int nombreEtals) {
      this.etals = new Etal[nombreEtals];

      for(int i = 0; i < this.etals.length; ++i) {
         this.etals[i] = new Etal();
      }

   }

   private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
      this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
   }

   private int trouverEtalLibre() {
      for(int i = 0; i < this.etals.length; ++i) {
         if (!this.etals[i].isEtalOccupe()) {
            return i;
         }
      }

      return -1;
   }

   private Etal[] trouverEtals(String produit) {
      int nbproduits = 0;

      for(int i = 0; i < this.etals.length; ++i) {
         if (this.etals[i].isEtalOccupe() && this.etals[i].contientProduit(produit)) {
            ++nbproduits;
         }
      }

      Etal[] etalsTrouve = new Etal[nbproduits];
      int j = 0;

      for(int i = 0; i < this.etals.length; ++i) {
         if (this.etals[i].isEtalOccupe() && this.etals[i].contientProduit(produit)) {
            etalsTrouve[j] = this.etals[i];
            ++j;
         }
      }

      return etalsTrouve;
   }

   private Etal trouverVendeur(Gaulois gaulois) {
      for(int i = 0; i < this.etals.length; ++i) {
         if (this.etals[i].getVendeur() == gaulois) {
            return this.etals[i];
         }
      }

      return null;
   }

   private String afficherMarche() {
      int nbEtalsNonUtilisés = 0;
      StringBuilder chaine = new StringBuilder();

      for(int i = 0; i < this.etals.length; ++i) {
         if (this.etals[i].isEtalOccupe()) {
            chaine.append(this.etals[i].afficherEtal());
         } else {
            ++nbEtalsNonUtilisés;
         }
      }

      if (nbEtalsNonUtilisés > 0) {
         chaine.append("Il reste " + nbEtalsNonUtilisés + " \u00e9tals non utilis\u00e9s dans le march\u00e9.");
      }

      return chaine.toString();
   }
}
