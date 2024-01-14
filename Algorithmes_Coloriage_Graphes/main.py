"""
Implémentation orienté objet d'un graphe
"""

import os


"""---------------------------------------------------------------------------
 Classe des graphes
   ---------------------------------------------------------------------------
"""
class Graphe:
	def __init__(self):
		self.sommets=[]
	

	def Existe(self,etiquette):
		trouve=False
		i=0
		taille=len(self.sommets)
		while (not(trouve) and i<taille):
			trouve=(self.sommets[i].DonneEtiquette()==etiquette)
			i+=1
		return trouve
	

	# Cette fonction part du principe que le sommet recherché est bien dans le
	# graphe. Et le retourne s'il est trouvé
	def __DonneSommetDejaVu(self,etiquette):
		i=0
		while(self.sommets[i].DonneEtiquette()!=etiquette):
			i+=1
		return self.sommets[i]



	# Cette méthode permet d'ajotuer un nouveau sommet
	# au graphe connaissant son étiquette
	# Aucune vérification ne sera faite sur le fait
	# qu'un sommet du meme nom est deja présent
	def AjouteSommet(self,etiquette):
		retour=Sommet(etiquette)
		self.sommets.append(retour)
		return retour
	

	def __DonneSommet(self,etiquette):
		if not(self.Existe(etiquette)):
			# On cree le sommet s'il n'existe pas
			retour=self.AjouteSommet(etiquette)
		else:
			retour=self.__DonneSommetDejaVu(etiquette)
		return retour


	def AjouteArete(self,etiquette1,etiquette2):
		s1=self.__DonneSommet(etiquette1)
		s2=self.__DonneSommet(etiquette2)
		s1.AjouteAdjacent(s2)
		s2.AjouteAdjacent(s1)


	# Ajoute une arête orientée entre deux sommets
	# sommet1 vers sommet2 
	def AjouteAreteOriente(self,etiquette1,etiquette2):
		s1=self.__DonneSommet(etiquette1)
		s2=self.__DonneSommet(etiquette2)
		s1.AjouteAdjacent(s2)


	def AfficheToi(self):
		print("Liste des sommets : ", end="")
		for s in self.sommets:
			print(s," ",end="")
		print("\nLes liens :")
		# On va maintenant parcourir chaque sommet et afficher les sommets
		# qui lui sont adjacents
		for s in self.sommets:
			print(s," (",s.DonneDegre(),") : ",end="")
			s.AfficheAdjacents()
			print("\n")


	def DonneDegre(self,etiquette):
		if self.Existe(etiquette):
			s=self.__DonneSommet(etiquette)
			retour=s.DonneDegre()
		else:
			retour=0
		return retour


	def __ParcoursProfondeur(self,sommet,dejavu=[]):
		sommet.ParcoursProfondeurR(dejavu)


	def ParcoursProfondeur(self):
		dejavu=[]
		for s in self.sommets:
			self.__ParcoursProfondeur(s,dejavu)
		print("\nListe obtenue :")
		for s in dejavu:
			print(s)


	def ParcoursProfondeur2(self,etiquette):
		dejavu=[]
		self.__ParcoursProfondeur(self.__DonneSommet(etiquette),dejavu)
		print("\nListe obtenue :")
		for s in dejavu:
			print(s)


	def ParcoursLargeur(self,etiquette):
		dejavu=[]
		self.__DonneSommet(etiquette).ParcoursLargeur(dejavu)
		print("\nListe obtenue :")
		for s in dejavu:
			print(s)


	def SauveFichier(self,fichier="graphe.out"):
		fichier = open(fichier, 'w')
		# On sauvegarde les noms des sommets
		noms=""
		for s in self.sommets:
			noms+=s.DonneEtiquette()+" "
		fichier.write(noms+"\n")
		# Puis on sauvegarde les arêtes
		for s in self.sommets:
			s.SauveFichierArete(fichier)
		fichier.close()


	def color_init(self):
		coloriage=dict()
		for s in self.sommets:
			coloriage[s]=0
		return coloriage


	# La fonction de coloriage va retourner un dictionnaire qui va associer chaque sommet a une couleur


	def ColorieNaif(self):
		coloriage=dict()
		couleur_courante=0
		for s in self.sommets:
			coloriage[s]=0

		for s in self.sommets:
			couleur=s.Colorie(coloriage)
			coloriage[s] = couleur
		return coloriage
	
  
	def ColorieGlouton(self):
		self.sommets=sorted(self.sommets,key=lambda s:s.DonneDegre(),reverse=True)
		return self.ColorieNaif()


	def ColorieWelshPowell(self):
		L=list()
		self.sommets=sorted(self.sommets,key=lambda s:s.DonneDegre(),reverse=True)
		L=self.sommets
		
		coloriage = self.color_init()
		couleur_courante=0
		
		while len(L) > 0:
			couleur_courante+=1
			premier=L[0]
			coloriage[premier] = couleur_courante
			s=L.pop(0)			
			V=[]

			for b in s.Donneadjacents():
				V.append(b.DonneExtremite())

			for x in L:
				if x not in V:
					coloriage[x] = couleur_courante
					for k in x.Donneadjacents():
						V.append(k.DonneExtremite())
						
			F=list()
			for s in L:
				if coloriage[s]==0:
					F.append(s)
			L=F
		return coloriage 	

def donne_adjacents(self):
	V=list()
	for k in x.Donneadjacents():
		V.append(k.DonneExtremite())


def AfficheListeSommet(self):
	for s in self.sommets:
		print(s," ",end="")
				
def Donnepetitchemin(self,etiquetteD,etiquetteA):
	sommetDepart=self.__DonneSommet(etiquetteD)
	sommetArrive=self.__DonneSommet(etiquetteA)
	return self.__DonnePlusPetitChemin(sommetDepart,sommetArrive)


def __DonnePlusPetitCheminProfondeur(self,sommetcourant,cible,chemin=list(),pcc=None):
	#inserer le sommet courant dans le chemin
	AfficheListeSommets(chemin)
	AfficheListeSommet(pcc)
	chemin.append(sommet_courant)
	if sommet_courant==cible:
		if pcc==None:
			pcc=chemin.copy()
		if len(chemin)<len(pcc):
			retour=chemin.copy(pcc)
		else:
			retour=pcc
	
	retour=pcc
	voisins=sommetcourant.donne_adjacents()
	




			
	


	#le chemin est un chemin possible

	#si plus petit chemin trouver retrouver le parcours


		


"""---------------------------------------------------------------------------
Classe des aretes
-----------------------------------------------------------------------------
"""
class Arete:
	def __init__(self,sommet1,sommet2,poids=1):
		self.origine=sommet1
		self.extremite=sommet2


	def AfficheExtremite(self):
		print(self.extremite," ",end="")


	def __str__(self):
		return str(self.origine)+" --> "+str(self.extremite)


	def DonneOrigine(self):
		return self.origine
	

	def DonneExtremite(self):
		return self.extremite


""" ---------------------------------------------------------------------------
 Classe des Sommet
 ---------------------------------------------------------------------------
"""
class Sommet:
	def __init__(self,etiquette):
		self.valeur=etiquette
		self.adjacents=[]


	def __str__(self):
		return self.valeur


	# cette méthode va chercher si le sommet donné n'est pas deja
	# dans la liste des sommets adjacents
	def TestAdjacentDejavu(self,sommet):
		trouve=False
		i=0
		while not(trouve) and i<len(self.adjacents):
			trouve=sommet==self.adjacents[i].DonneExtremite()
			i+=1
		return trouve


	def AjouteAdjacent(self,adjacent):
		if not(self.TestAdjacentDejavu(adjacent)):
			arete=Arete(self,adjacent)
			self.adjacents.append(arete)


	def DonneEtiquette(self):
		return self.valeur
	

	def AfficheAdjacents(self):
		for adj in self.adjacents:
			adj.AfficheExtremite()

	def DonneDegre(self):
		return len(self.adjacents)


	# Parcours en profondeur depuis un sommet donné en version
	# recursive
	def ParcoursProfondeurR(self,dejavu):
		if self not in dejavu:
			dejavu.append(self)
			print(self," ",end="")
			for adj in self.adjacents:
				adj.DonneExtremite().ParcoursProfondeurR(dejavu)


	# Version iterative du parcours en profondeur 
	def ParcoursProfondeur(self,dejavu):
		Pile=[self]
		while len(Pile) > 0:
			sommet_a_traiter=Pile.pop(0)
			dejavu.append(sommet_a_traiter)
			for arete in sommet_a_traiter.adjacents:
				adj=arete.DonneExtremite()
				if adj not in dejavu:
					if adj not in Pile:
						Pile.insert(0,adj)
		



	# Version iterative du parcours en largeur 
	def ParcoursLargeur(self,dejavu):
		File=[self]
		while len(File) > 0:
			sommet_a_traiter=File.pop(0)
			dejavu.append(sommet_a_traiter)
			for arete in sommet_a_traiter.adjacents:
				adj=arete.DonneExtremite()
				if adj not in dejavu:
					if adj not in File:
						File.append(adj)
			

	# On sauvegarde les arêtes dans un fichier texte, une par une ...
	def SauveFichierArete(self,fichier):
		for adj in self.adjacents:
			textasauver=self.valeur+" "+adj.DonneExtremite().DonneEtiquette()+"\n"
			fichier.write(textasauver)
		
	def Colorie(self,coloriage):
		couleurs=[]
		adj=[]
		for b in self.adjacents:
			adj.append(b.extremite)

		for s in adj:
			if not(coloriage[s] in couleurs):
				couleurs.append(coloriage[s])

		couleurs.sort()
		couleur_compare=1
		trouve=False
		while trouve==False:
			if not(couleur_compare in couleurs):
				trouve=True
			else:
				couleur_compare+=1
		return couleur_compare
	
	def Donneadjacents(self):
		b=list()
		for adj in self.adjacents:
			b.append(adj)
		return b
		

# ---------------------------------------------------------------------------------
def LectureGrapheorienté(nom_fichier="graphe1.out"):
	# si le fichier existe
	if os.path.isfile(nom_fichier):
    # ouvrir le fichier
		fichier = open(nom_fichier, 'r')
	else:
		# afficher un message d'erreur
		print('Erreur : `' + nom_fichier + '` n\'existe pas ' +
		'ou n\'est pas un fichier.')
		# quitter le programme avec un code d'erreur
		exit(1)

	# récupérer les lignes du fichier dans une liste
	contenu = fichier.readlines()
	# fermer le fichier
	fichier.close()

	i=0
	nouveaugraphe=Graphe()
	# parcours de chaque élément de la liste de lignes
	for ligne in contenu:
		# incrémentation du numéro de ligne
		i += 1
		# suppression du retour chariot en fin de ligne
		ligne = ligne.rstrip().split(" ")
		if i==1:
			# C'est la ligne qui donne le nom des sommets
			for n in ligne:
				nouveaugraphe.AjouteSommet(n)
		else:
			# c'est une ligne qui indique une arete orienté
			nouveaugraphe.AjouteAreteOriente(ligne[0],ligne[1])
	return nouveaugraphe
	
	
def LectureGraphe(nom_fichier="graphe.out"):
		# si le fichier existe
	if os.path.isfile(nom_fichier):
    # ouvrir le fichier
		fichier = open(nom_fichier, 'r')
	else:
		# afficher un message d'erreur
		print('Erreur : `' + nom_fichier + '` n\'existe pas ' +
		'ou n\'est pas un fichier.')
		# quitter le programme avec un code d'erreur
		exit(1)

	# récupérer les lignes du fichier dans une liste
	contenu = fichier.readlines()
	# fermer le fichier
	fichier.close()

	i=0
	nouveaugraphe=Graphe()
	# parcours de chaque élément de la liste de lignes
	for ligne in contenu:
		# incrémentation du numéro de ligne
		i += 1
		# suppression du retour chariot en fin de ligne
		ligne = ligne.rstrip().split(" ")
		if i==1:
			# C'est la ligne qui donne le nom des sommets
			for n in ligne:
				nouveaugraphe.AjouteSommet(n)
		else:
			# c'est une ligne qui indique une arete orienté
			nouveaugraphe.AjouteArete(ligne[0],ligne[1])
	return nouveaugraphe
	







def AfficheColoriage(coloriage):
	print("Le coloriage du graphe est :")
	for s in coloriage:
		print(s.DonneEtiquette()," ",coloriage[s])


# ---------------------------------------------------------------------------------

"""
mongraphe=Graphe()


mongraphe.AjouteAreteOriente("A","B")
mongraphe.AjouteArete("B","C")
mongraphe.AjouteAreteOriente("E","D")
mongraphe.AjouteAreteOriente("E","A")
mongraphe.AjouteAreteOriente("B","E")
mongraphe.AjouteAreteOriente("F","C")

mongraphe.AfficheToi()

print("\nParcours en largeur :")
mongraphe.ParcoursLargeur("F")

mongraphe.SauveFichier()
"""
print("Premier graphe orienté: ","\n")
mongraphe=LectureGrapheorienté("grapheorienté.out")
mongraphe1=LectureGraphe("graphe1.out")
mongraphe2=LectureGraphe("graphe2.out")
mongraphe4=LectureGraphe("graphe4.out")

mongraphe.AfficheToi()


#print("Premier graphe (non orienté) :","\n")


mongraphe1.AfficheToi()
print("Deuxième graphe  :","\n")


mongraphe2.AfficheToi()
print("\n")

print("Troisième Graphe: ","\n")
mongraphe4.AfficheToi()
print("\n")



print("Partie 2 : Colorie Naif","\n")
print("Coloriage du premier graphe","\n")
AfficheColoriage(mongraphe1.ColorieNaif())
print("\n")


print("Coloriage du second graphe","\n")
AfficheColoriage(mongraphe2.ColorieNaif())
print("\n")

print("Partie 3 : Algo Glouton","\n")
print("Premier Graphe: ","\n")
AfficheColoriage(mongraphe1.ColorieGlouton())
print("\n")

print("Deuxième  Graphe: ","\n")
AfficheColoriage(mongraphe2.ColorieGlouton())
print("\n")


print("Partie 4 : Welsh Powell ","\n")
print("Premier Graphe: ", "\n")
AfficheColoriage(mongraphe2.ColorieWelshPowell())
print("\n")


print("Deuxième Graphe: ", "\n")
AfficheColoriage(mongraphe4.ColorieWelshPowell())
print("\n")



