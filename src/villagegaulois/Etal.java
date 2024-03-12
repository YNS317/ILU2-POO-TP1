// Source code is decompiled from a .class file using FernFlower decompiler.
package villagegaulois;

import personnages.Gaulois;

public class Etal {
   private Gaulois vendeur;
   private String produit;
   private int quantiteDebutMarche;
   private int quantite;
   private boolean etalOccupe = false;

   public Etal() {
   }

   public boolean isEtalOccupe() {
      return this.etalOccupe;
   }

   public Gaulois getVendeur() {
      return this.vendeur;
   }

   public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
      this.vendeur = vendeur;
      this.produit = produit;
      this.quantite = quantite;
      this.quantiteDebutMarche = quantite;
      this.etalOccupe = true;
   }

   public String libererEtal() {
      if (!this.etalOccupe) {
         throw new IllegalStateException("L'\u00e9tal n'est pas occup\u00e9.");
      } else {
         this.etalOccupe = false;
         StringBuilder chaine = new StringBuilder("Le vendeur " + this.vendeur.getNom() + " quitte son \u00e9tal, ");
         int produitVendu = this.quantiteDebutMarche - this.quantite;
         if (produitVendu > 0) {
            chaine.append("il a vendu " + produitVendu + " parmi " + this.produit + ".\n");
         } else {
            chaine.append("il n'a malheureusement rien vendu.\n");
         }

         return chaine.toString();
      }
   }

   public String afficherEtal() {
      if (this.etalOccupe) {
         String var10000 = this.vendeur.getNom();
         return "L'\u00e9tal de " + var10000 + " est garni de " + this.quantite + " " + this.produit + "\n";
      } else {
         return "L'\u00e9tal est libre";
      }
   }

   public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
      try {
         if (acheteur == null) {
            throw new NullPointerException("L'acheteur ne peut pas \u00eatre null.");
         } else if (quantiteAcheter < 1) {
            throw new IllegalArgumentException("La quantit\u00e9 \u00e0 acheter doit \u00eatre positive.");
         } else if (!this.etalOccupe) {
            throw new IllegalStateException("L'\u00e9tal doit \u00eatre occup\u00e9 pour effectuer un achat.");
         } else {
            StringBuilder chaine = new StringBuilder();
            String var10001 = acheteur.getNom();
            chaine.append(var10001 + " veut acheter " + quantiteAcheter + " " + this.produit + " \u00e0 " + this.vendeur.getNom());
            if (this.quantite == 0) {
               chaine.append(", malheureusement il n'y en a plus !");
               quantiteAcheter = 0;
            }

            if (quantiteAcheter > this.quantite) {
               int var5 = this.quantite;
               chaine.append(", comme il n'y en a plus que " + var5 + ", " + acheteur.getNom() + " vide l'\u00e9tal de " + this.vendeur.getNom() + ".\n");
               quantiteAcheter = this.quantite;
               this.quantite = 0;
            }

            if (this.quantite != 0) {
               this.quantite -= quantiteAcheter;
               var10001 = acheteur.getNom();
               chaine.append(". " + var10001 + ", est ravi de tout trouver sur l'\u00e9tal de " + this.vendeur.getNom() + "\n");
            }

            return chaine.toString();
         }
      } catch (NullPointerException var4) {
         var4.printStackTrace(System.err);
         return "";
      }
   }

   public boolean contientProduit(String produit) {
      return this.produit.equals(produit);
   }
}
