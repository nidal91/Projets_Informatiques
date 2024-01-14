import Liste


class Pile:
	def __init__(self):
		self.__valeur = Liste.Liste()
		self.__longueur = 0

	
	def Empile(self,valeur):
		self.__valeur=self.__valeur.Ajoute(0,valeur)
		self.__longueur+=1

	def Depile(self):
		donne = self.__valeur.DonneValeur(0)
		self.__valeur = self.__valeur.RetireDebut()
		self.__longueur -= 1
		return donne

	def Affiche(self):
		self.__valeur.AfficheToi()

	def Vide(self):
		return self.__longueur == 0
		
















		





		





