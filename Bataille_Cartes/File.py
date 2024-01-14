import Liste

class File:
	def __init__(self):
		self.__valeur = Liste.Liste()
		self.__longueur = 0
		
		
	def Enfile(self,valeur):
		self.__valeur = self.__valeur.AjouteFin(valeur)
		self.__longueur += 1
	
	def Defile(self):
		donne = self.__valeur.DonneValeur(0)
		self.__valeur = self.__valeur.RetireDebut()
		self.__longueur -= 1
		return donne

	def Affiche(self):
		self.__valeur.AfficheToi()

	def Len(self):
		return self.__longueur

	def Vide(self):
		return self.__longueur == 0