from random import randint
import corrige_pendu 

#Question 0
#Compléter dans les chaînes ci-dessous, sans enlever les guillemets:
NOM = "Larbi"
PRENOM = "Nidal"
EMAIL = "nidal.larbi@universite-paris-saclay.fr"


#Question 1

def charge_mots(chemin):
    lecture=open(chemin,"r") #ouvre en lecture le fichier pris en argument
    mots=lecture.read().splitlines()# créer un tableau  mots contenant tous les mots du fichier   
    lmax=len(mots[0]) #on initialise une variable lmax contenant la longueur du premier élément de mot
    for elmt in mots: #pour  chaque mot de la liste mot 
        if lmax<len(elmt): #si la longueur du mot (lmax) est plus petite que celle du mot courant 
            lmax=len(elmt) # la longueur du mot courant  est stocké dans lmax 
    return mots,lmax # renvoie la liste mots contenant tous les mots et la taille du mot le plus long de la liste mot  
    
    #return corrige_pendu.charge_mots(chemin)
#Question 2
def test_charge_mots():
    mots,lmax=charge_mots("mots.txt") #appel de la première fonction , stockage du  tableau contenant tous les mots dans la variable mots et stockage dans lmax la longueur du plus long mot(lmax)
    assert len(mots)==59705# séries de test (renseignés dans l'énoncé du DM) :
    assert lmax==25
    assert mots[0]=="abaissa"
    assert mots[59704]=="zyeuter"

#Question 3

def mots_par_longueur(tab_mots,lmax):
    t=[]    #initialisation d'une liste t 
    for i in range(lmax+1): #création de sous tablaux au nombre de la longueur du mot (lmax) +1 dans la liste t
        t.append([]* 1)
    for g in range (lmax+1):
        for j in range(0,len(tab_mots)): #pour chaque mot j dans la liste tab_mots 
            if g == len(tab_mots[j]): # si il existe un  sous-tableau (g) dans t dont l'indice est égale à la longueur d'un mot (j) de tab_mots .
                t[g].append(tab_mots[j]) #ajouter dans le sous tableau (g)  d'indice égale à longueur de j le mot en question dans t. 
    return t # renvoie le tableau t
    #return corrige_pendu.mots_par_longueur(tab_mots,lmax)

#Question 4
def test_mots_par_longueur():
    assert(mots_par_longueur(['a','bonbon','code','dos','etre'],6)==[[],['a'],[],['dos'],['code', 'etre'],[],['bonbon']])
    return

#Question 5
def choix_mot(tab_mots_long, l):
    interest_word=tab_mots_long[l] # interest_word contient le sous tableau contenant les mots de longueur l 
    word_choice=randint(0,len(interest_word)-1) # word choice contient l'indice du mot tiré aléatoirement dans interest_word
    return interest_word[word_choice]#on renvoie  le mot tiré aléatoirement en fonction de l'indice trouvé précedemment 
    #return corrige_pendu.choix_mot(tab_mots_long, l)

#Question 6
def test_choix_mot():
    #NE PAS MODIFIER LES 2 LIGNES SUIVANTES
    tab_mots, lmax = corrige_pendu.charge_mots("mots.txt")
    tab_mots_long = corrige_pendu.mots_par_longueur(tab_mots, lmax)
    assert (choix_mot([[],['a'],[],['dos'],['code', 'etre'],[], ['bonbon']],4)=="code" or "etre")
    assert (choix_mot([[],[],["du"],["cou","vue","mat","vie"],["samu","ciel"],["noter","tenir"]],3)=="cou" or "vue" or "mat" or "vie")
    assert (choix_mot([[],[],["tu"],[],["mare","vase"],["verre","tuyau"],["cerise","pomme","pierre"]],6)=="cerise" or "pomme" or "pierre")

#Question 7
def init_probleme(mot):
    problem=[] # initialisation d'une liste problem
    for letter in mot: #pour chaque lettre du mot
        problem.append((letter,False) * 1) # on ajoute a la liste problem  un tuple contenant  la lettre courante  et le booléen False
    return problem #on renvoie la liste problem 
    #return corrige_pendu.init_probleme(mot)

#Question 8
def test_init_probleme():
    assert (init_probleme("ordinateur")==[("o",False),("r",False),("d",False),("i",False),("n",False),("a",False),("t",False),("e",False),("u",False),("r",False)])
    assert (init_probleme("souris")==[("s",False),("o",False),("u",False),("r",False),("i",False),("s",False)])
    return

#Question 9
def num_inconnues (probleme):
    letter_not_found=0 # on iniatilise le compteur letter_not_found à 0
    for elmt in probleme: # pour chaque  tuple du probleme
        if elmt[1]==False: # si le booléen associé à la lettre est égal à False
            letter_not_found+=1 #on incrémente notre compteur
    return letter_not_found# renvoie le nombre de lettres non trouvé dans le probleme
    #return corrige_pendu.num_inconnues(probleme)


# Question 10
def test_num_inconnues ():
    assert (num_inconnues([('c', False), ('o', False), ('d', False), ('e', False)])==4) 
    assert (num_inconnues([('c', True), ('o', True), ('d', True), ('e', True)])==0)
    assert (num_inconnues([('c', False), ('o', True), ('d', True), ('e', False)])==2)
    return

# Question 11
def joue(probleme, lettre):
    for elmt in probleme: # pour chaque  tuples du probleme 
        if elmt[0]==lettre: #si la lettre est la meme que celle suposé par l'utilisateur dans un des  tuple
            indice=probleme.index(elmt) #on initialise indice qui contient  l'indice du tuple contenant la lettre trouvé 
            letter_found=list(elmt) # on transforme le tuple contenant la lettre trouvé en liste  
            letter_found[1]=True # on modifie dans la liste le booléen en le mettant à True 
            del probleme[indice] # on supprime le tuple contenant la lettre trouvé 
            probleme.insert(indice,tuple(letter_found)) # on insère au bon indice le nouveau tuple contenant la lettre trouvé et le booléen True 
    return probleme # on retourne le nouveau tableau 
    #return corrige_pendu.joue(probleme, lettre)


# Question 12
def test_joue():
    assert (joue([('c', False), ('o', False), ('d', False), ('e', False)],"d")==[('c', False), ('o', False), ('d', True), ('e', False)])
    assert (joue([('c', False), ('o', False), ('d', False), ('e', False)],"c")==[('c', True), ('o', False), ('d', False), ('e', False)]) 
    assert (joue([('c', False), ('o', False), ('d', False), ('e', False)],"o")==[('c', False), ('o', True), ('d', False), ('e', False)])
    return

# Question 13
def affiche_probleme(tab):
    guessed_letter="" #on initialise la chaine de caractères guessed letter 
    for elmt in tab: #pour chaque tuple  du tableau 
        if elmt[1]==True: #si une lettre est trouvé dans un tuple
            guessed_letter+=elmt[0] # on ajoute  la lettre trouvé à la chaine de caractère (guessed_letter)
        if elmt[1]==False: # si une lettre n'est pas trouvé dans un tuple
            guessed_letter+="." # on ajoute "." à la chaine de caractère (guessed_letter)
    print(guessed_letter) # on affiche la chaine de caractère guessed letter
    #corrige_pendu.affiche_probleme (tab)


PENDU = (
    '  ___ ',
    ' |   |',
    ' o   |',
    '/|\  |',
    '/ \  |',
    '     |')

# Question 14
def affiche_pendu(n):
    k=0 #on initialise k à 0
    for i in range (len(PENDU)): #on parcourt la chaine de caractère variable globale PENDU
        for c in PENDU[i]: # pour chaque caractère dans PENDU 
            if c==' ': # si le caractère est un espace 
                print(c,end='')# alors afficher l'espcae sans retour à la ligne
            elif k<n:# sinon si  k est inférieur à n 
                print(c,end="") # on affiche le caractère courant sans retour à la ligne
                k+=1 # on incrémente k
            else: #sinon on affiche un espace sans retour à la ligne
                print(' ',end='') 
        print() # affiche un retour à la ligne



# Question 15
def partie(mot):
    n=0 # on initiailise n à 0 
    problem=init_probleme(mot) #on transforme le mot en problème 
    while num_inconnues(problem)!=0 and n!=15: # tant qu'il reste des lettres à deviner et que le pendu n'est pas complétement dessiné 
        affiche_probleme(problem)# on affiche le problème
        a=str(input("Saisir un lettre entre a et z: ")) #on demande à l'utilisateur une lettre
        if a.isalpha()==False:# si ce n'est pas une lettre 
            print("Saisie invalide,  veuillez resaisir la lettre","\n")# on affiche un message d'erreur et on redemande une lettre
        if a.isalpha()==True:# si c'est une lettre
            t=num_inconnues(problem) # on initialise t  qui contient le nombre de lettres non trouvé par l'utilisateur avant le mouvement de jeu 
            joue(problem,a)#on effectue un monvement de jeu 
            s=num_inconnues(problem)#on initialise s qui  contient le nombre de lettres non trouvé par l'utilisateur après le mouvement de jeu 
            if s==t: # si le nombre de lettre non trouvées reste inchangé 
                n+=1 # on incrémente n (le nombre de trait du pendu)
                affiche_pendu(n)# on dessine le pendu 
    if num_inconnues(problem)==0: # si il n'y a plus de lettres à devinés 
        print("Vous avez gagné !")# afficher ("Vous avez gagné")
    else: #si le nombre de lettre à deviner est supérieur strictement à 0
        print("Vous avez perdu, le mot était: ",mot) # afficher ("Vous avez perdu") ainsi que le mot qui était à deviner 

def jeu():
    mots, lmax = charge_mots("mots.txt")  
    lmots = mots_par_longueur(mots, lmax)  
    while True:
        s = input("Saisir une longueur de mot ou q pour quitter: ")
        if s == 'q':
            print ("Au revoir.")
            return
        try:
            l = int(s)
            if l > lmax or l <= 0:
                print ("Longueur invalide")
                continue
            mot = choix_mot(lmots, l)
            partie (mot)
        except ValueError:
            print ("Saisie invalide")



jeu()
