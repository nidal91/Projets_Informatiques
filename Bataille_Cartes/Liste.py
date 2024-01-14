class Liste:
	"""docstring for Liste"""
	def __init__(self):
		self.valeur  = None
		self.suivant = None

	def Longueur(self):
		longueur=0
		p=self
		while not(p.TestFin()):
			longueur+=1
			p=p.GetSuivant()
		return longueur

	def AfficheToi(self):
		print("[",end="")
		p=self
		while (not(p.TestFin())):
			print(p.GetValeur()," ",end="")
			p=p.GetSuivant()
		print("]")

	def AjouteFin(self,valeur):
		if self.TestFin():
			nouveau=Liste()
			nouveau.SetValeur(valeur)
			nouveau.SetSuivant(self)
			return nouveau
		else:
			# Cas ou la liste a plus d'un element
			p=self
			while (not(p.GetSuivant().TestFin())):
				p=p.GetSuivant()
			nouveau=Liste()
			nouveau.SetValeur(valeur)
			nouveau.SetSuivant(p.GetSuivant())
			p.SetSuivant(nouveau)
			return self

	def RetireFin(self):
		if self.TestFin():
			# Il n'y a aucune valeur
			return self
		elif self.GetSuivant().TestFin():
			# Il y a qu'une valeur
			return self.GetSuivant()
		else:
			# Il y a plus de deux valeurs
			precedent=self
			p=precedent.GetSuivant()
			while not(p.GetSuivant().TestFin()):
				# On se déplace à la cellule suivante
				precedent,p=p,p.GetSuivant()
			# On est arrivé à l'avant derniere cellule
			precedent.SetSuivant(p.GetSuivant())					
			return self

	def GetValeur(self):
		return self.valeur

	def SetValeur(self,valeur):
		self.valeur=valeur

	def GetSuivant(self):
		return self.suivant

	def SetSuivant(self,suivant):
		self.suivant=suivant

	def Ajoute(self,position,valeur):
		if position==0:
			nouveau=Liste()
			nouveau.SetValeur(valeur)
			nouveau.SetSuivant(self)
			return nouveau
		else:
			p=self
			while (not(p.GetSuivant().TestFin()) and position>1):
				p=p.GetSuivant()
				position -=1
			if position ==1 :
				nouveau=Liste()
				nouveau.SetValeur(valeur)
				nouveau.SetSuivant(p.GetSuivant())
				p.SetSuivant(nouveau)
				return self
			else:
				# Pb la liste est trop courte
				# Dans ce cas, on ne fait rien
				return self
	
	def TestFin(self):
		return self.valeur==self.suivant == None
	
	def RetireDebut(self):
		if not(self.TestFin()):
			return self.GetSuivant()
		else:
			return self
	
	def DonneValeur(self,position):
		p=self
		while (not(p.TestFin()) and position>0):
			p=p.GetSuivant()
			position -=1
		if position > 0:
			return None
		else:
			return p.GetValeur()




