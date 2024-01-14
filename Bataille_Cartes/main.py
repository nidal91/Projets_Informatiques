
import Pile
import File
import Liste
import random

"""
Scénario:
il faut un paquet de carte rangé (pile ou liste?)
le mélanger
distribuer une carte au joueur 1 puis une carte au joueur 2
sous forme de File
répéter jusqu'à la fin de la pile/Liste des cartes rangés
Le jeu commence
Chaque Joueur pose une carte en appuyant sur Enter (pour ajouter de l'interaction)
l'objet Bataille vérifit quelle carte a la plus grande valeur
les cartes sont données au vainqueur du round
si les cartes ont la même valeur chaque joueur stock une carte caché puis rejoue et celui à la plus grande valeur gagne toutes les cartes mises en jeu
à chaque tour ont vérifit si les 2 joueurs ont encore des cartes sinon celui qui n'en a plus perd
FIN

"""

#initialistion du paquet de carte:
dico_hauteur = {0:"2", 1:"3", 2:"4", 3:"5", 4:"6", 5:"7", 6:"8", 7:"9", 8:"10", 9:"Valet", 10:"Dame", 11:"Roi", 12:"As"}
dico_couleur = {0:"Coeur", 1:"Carreau", 2:"Pique", 3:"Trèfle"}

#_______________________________________	

class Carte:
	def __init__(self,couleur,hauteur):
		self.hauteur = hauteur
		self.couleur = couleur

	def Affiche(self):
		print(dico_hauteur[self.hauteur], " de ",dico_couleur[self.couleur])
	
	def __str__(self):
		return dico_hauteur[self.hauteur]+" de "+dico_couleur[self.couleur]
	
#_______________________________________	

class Bataille:
	def __init__(self):
		self.paquet = Liste.Liste()
		self.nombres=len(dico_couleur)*len(dico_hauteur)		
		for c in dico_couleur.keys():
			for h in dico_hauteur.keys():
				self.paquet=self.paquet.Ajoute(0,Carte(c,h))
		#self.paquet.AfficheToi()
	
	def Affiche(self):
		self.paquet.AfficheToi()

	def Len(self):
		return self.nombres

	def Melange(self):
		for i in range(10000):
			# On prend la première carte
			# On la retire du paquet
			# puis on l'insere au hasard ailleurs
			c=self.paquet.DonneValeur(0)
			self.paquet=self.paquet.RetireDebut()
			#print("Je prends la carte",end="")
			#c.AfficheToi()
			position=random.randint(1,self.nombres-2)
			#print("Je la place en :" ,position)
			self.paquet=self.paquet.Ajoute(position,c)

	
	def Distribue(self):
		deck1 = File.File()
		deck2 = File.File()
		turn = True

		for c in range(int(self.paquet.Longueur())):
			if (turn):
				deck1.Enfile(self.paquet.DonneValeur(c))
				turn = not(turn)
			else:
				deck2.Enfile(self.paquet.DonneValeur(c))
				turn = not(turn)
		
		return deck1,deck2

	def gagne(self,c1,c2):
		gagne = None
		if c1.hauteur > c2.hauteur:
			gagne = "J1"
		elif c1.hauteur < c2.hauteur:
			gagne = "J2"
		
		return gagne

	
	def Bataille(self,c1,c2):
		bataille = False
		if c1.hauteur == c2.hauteur:
			bataille = True
		return bataille


#_______________________________________
class Joueur:
	def __init__(self,paquet):
		#paquet doit etre une file
		self.paquet=paquet
	
	def AjouteCarte(self,carte):
		self.paquet.Enfile(carte)
	
	def DonneCarte(self):
		return self.paquet.Defile()
	
	def PossedeCartes(self):
		return self.paquet.Len()>0
		
	def DonneNbCartes(self):
		return self.paquet.Len()
	
	def AffichePaquet(self):
		p = self.paquet
		c = Carte(0,0)
		l=p.Len()

		print("/",end="")
		for i in range(l):
			c=p.Defile()
			print(c)

			print("/",end="")
			

#-----------------CODE PRINCIPALE-----------------------

#On initialise le paquet de carte puis on le mélange
jeu=Bataille()
jeu.Melange()

#On fait deux paquets avec les cartes
deck1,deck2 = jeu.Distribue()

#on initialise les 2 joueurs et on leur donne chacun un deck
j1 = Joueur(deck1)
j2 = Joueur(deck2)


while (j1.PossedeCartes() and j2.PossedeCartes()):
	#chaque joueur donne une carte
	c1 = j1.DonneCarte()
	c2 = j2.DonneCarte()

	#test pour creer une bataille
	#c1 = Carte(2,2)
	#c2 = Carte(1,2)

	#On affiche les cartes
	print("Joueur 1 pose la carte : ",end="")
	c1.Affiche()
	print("Joueur 2 pose la carte : ",end="")
	c2.Affiche()
	

	#On commence par verifier quel joueur gagne la manche s'il y a pas de vainqueur il y a bataille

	if (jeu.gagne(c1,c2) == "J1"):
		print("\nJoueur 1 gagne cette manche !\n")
		j1.AjouteCarte(c1)
		j1.AjouteCarte(c2)
	elif (jeu.gagne(c1,c2) == "J2"):
		print("\nJoueur 2 gagne cette manche !\n")
		j2.AjouteCarte(c1)
		j2.AjouteCarte(c2)
	else:
		
		#La file des cartes qui seront en jeu
		deck = File.File()
		while c1.hauteur == c2.hauteur and j1.PossedeCartes() and j2.PossedeCartes():
			print("BATAAAAAAIIIIILLEEE.exe")
			#On stock les cartes jouées puis on en stock une de plus pour chaque joueur
			deck.Enfile(c1)
			deck.Enfile(c2)
			deck.Enfile(j1.DonneCarte())
			deck.Enfile(j2.DonneCarte())
			#On sort une nouvelle carte pour chaque joueur
			c1 = j1.DonneCarte()
			c2 = j2.DonneCarte()
			deck.Enfile(c1)
			deck.Enfile(c2)

			#On affiche ces cartes
			print("Joueur 1 pose la carte : ",end="" )
			c1.Affiche()
			print("Joueur 2 pose la carte : ",end="")
			c2.Affiche()
			print()

			if c1==None or c2==None:
				print("c fini")
				break

			#On verifit quel jour a posé la carte la plus haute s'il y a encore bataille on recommence
			if (jeu.gagne(c1,c2) == "J1"):
				print("Joueur 1 gagne cette manche et recupere :\n")
				for c in range(deck.Len()):
					carte = deck.Defile()
					carte.Affiche()
					j1.AjouteCarte(carte)
			elif (jeu.gagne(c1,c2) == "J2"):
				print("Joueur 2 gagne cette manche et recupere:\n")
				for c in range(deck.Len()):
					carte = deck.Defile()
					carte.Affiche()
					j2.AjouteCarte(carte)
	


			
		
		
if j1.PossedeCartes():
	print("Bravo Joueur 1 c'etait un match incroyable!!!")
else:
	print("Excelente partie Joueur 2 nous avons tous hate de te retrouver au prochain tournoi")