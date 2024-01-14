
from math import sqrt
from csv import DictReader
from sys import argv, exit
from types import new_class
import corrige_ray

## Question 0

NOM = "Larbi"
PRENOM = "Nidal"
EMAIL = "nidal.larbi@universite-paris-saclay.fr"
### Opérations sur les vecteurs
### (section 4.1 de l'énoncé)
### Un vecteur est simplement représenté par un triplet (x, y, z)


## Question 1


"""Addition de deux vecteurs"""


def add(v1, v2):
  v3=list()
  for i in range (len(v1)):
    result=v1[i]+v2[i]
    v3.append(result)
  return(tuple(v3))
  
  #return corrige_ray.add(v1, v2)


"""Différence de deux vecteurs"""


def sub(v1, v2):
  v3=list()
  for i in range (len(v2)):
    result=v1[i]-v2[i]
    v3.append(result)
  return(tuple(v3))
  
  #return corrige_ray.sub(v1, v2)


"""Multiplication par une constante"""


def mul(k, v):
  v3=list()
  for i in range (len(v)):
    result=k*v[i]
    v3.append(result)
  return (tuple(v3))
  
  #return corrige_ray.mul(k, v)


"""Produit scalaire de deux vecteurs"""


def dot(v1, v2):
  result=0
  for i in range (len(v1)):
    result+=v1[i]*v2[i]
  return result
  #return corrige_ray.dot(v1, v2)


"""Norme d'un vecteur """


def norm(v):
  v3=list()
  result=0
  for i in range (len(v)):
    result+=v[i]**2
  result=sqrt(result)
  return result
  
  #return corrige_ray.norm(v)

def normalize(v):
  v3=list()
  for i in range (len(v)):
    result=((v[i])*1/norm(v))
    v3.append(result)
  return (tuple(v3))
  
  #return corrige_ray.normalize(v)

## Question 2


"""Fonction de test pour add"""


def test_add():
  assert(add((3,5,7),(2,3,1))==(5,8,8))
  assert(add((1,1,1),(1,1,1))==(2,2,2))
  assert(add((1,2,3),(4,5,6))==(5,7,9))

test_add()


"""Fonction de test pour sub"""


def test_sub():
  assert(sub((5,5,5),(3,3,3))==(2,2,2))
  assert(sub((1,2,3),(4,5,6))==(-3,-3,-3))
  assert(sub((3,2,4),(7,10,2))==(-4,-8,2))

test_sub()


"""Fonction de test pour mul"""


def test_mul():
  assert(mul(2,(5,5,5))==(10,10,10))
  assert(mul(-1,(1,2,3))==(-1,-2,-3))
  assert(mul(4,(3,2,4))==(12,8,16))

test_mul()


"""Fonction de test pour dot"""


def test_dot():
  assert(dot((3,5,7),(2,3,1))==28)
  assert(dot((1,1,1),(1,1,1))==3)
  assert(dot((1,2,3),(4,5,6))==32)

test_dot()


"""Fonction de test pour norm"""


def test_norm():
  assert(norm((2,2,1))==3.0)
  assert(norm((4,4,2))==6.0)
  assert(norm((8,8,4))==12.0)

test_norm()


"""Fonction de test pour normalize"""


def test_normalize():
  assert(normalize((2,2,1))==(0.6666666666666666, 0.6666666666666666, 0.3333333333333333))
  assert(normalize((4,4,2))==(0.6666666666666666, 0.6666666666666666, 0.3333333333333333))
  assert(normalize((8,8,4))==(0.6666666666666666, 0.6666666666666666, 0.3333333333333333))


test_normalize()


### Opérations sur les images
### (section 4.2 de l'énoncé)
### Une image est représentée par un triplet (i, w, h) où :
### - i est un bytearray de taille w * h * 3
### - w est la largeur de l'image (en pixels)
### - h est la hauteur de l'image (en pixels)



## Question 3


"""Initialise une image de w pixels de large et h pixel de haut"""


def init_image(w, h):
  return tuple([bytearray(w*h*3),w,h])
  #return corrige_ray.init_image(w, h)


"""Met le pixel au coordonnées (x, y) à la couleur c. C'est un est
  triplet (r, v, b) de valeurs. Les valeurs supérieures à 1 (resp. inférieures à
  0) sont mises à 1 (resp. 0).
  """

#(0.3,0.6,0.7)
## Question 4
def set_pixel(img, x, y, c):
  indice=(x*3)+(y * img[1] * 3)
  for i in range (0,len(c)):
    component=c[i].real
    if component<0:
      component=0
    elif component>1:
      component=1
    img[0][indice+i]=int(component*255)
  #return corrige_ray.set_pixel(img, x, y, c)

### Fonction donnée, ne pas modifier
def save_image(chemin, img):
  """Écrit l'image img dans le fichier dont le chemin est donné. Si
  le fichier existe, il est supprimé. L'image est stockée au format PPM"""
  buff, w, h = img
  with open(chemin, "wb") as f:
    f.write(b'P6\n')
    f.write(str(w).encode())
    f.write(b' ')
    f.write(str(h).encode())
    f.write(b'\n255\n')
    f.write(buff)



## Question 5

"""Test des fonctions set_pixel et init_image"""


def test_img():
  r=init_image(100,100)
  save_image("black100.ppm",r)
  t=init_image(200,200)
  for i in range (75,125):
    for j in range(75,125):
        set_pixel(t,i,j,(0.564,0.933,0.564))
  for b in range(0,50):
    for c in range(175,200):
        set_pixel(t,b,c,(0.6784,0.8470,0.9019))
  save_image("test_img.ppm",t)


### Attention, si vous faites une génération d'image un peu coûteuse, vous pouvez
### commenter l'appel ci-dessous après avoir testé
"""
#Test de la fonction :
# Premier test: création d'une image de taille 100 par 100  enregistré dans un  fichier black100.ppm.
# Deuxième test: création d'une image de taille 200 par 200 enregistré dans un fichier test_img.ppm. 
  Cette image est composé d'un carré de couleur vert clair de taille 75 par 75 situé au centre de cette dernière et 
  d'un rectangle de taille 50 par 25 de couleur bleu clair  situé dans le coin  inférieur gauche de l'image. 
""" 

test_img()

### Fonctions de ray tracing
### Section 5 de l'énoncé


## Question 6

"""Convertit un pixel (px, py) en un point du plan."""

def pixel_to_point(w, h, xmin, xmax, ymin, ymax, px, py):
    x = xmin + (xmax - xmin) / w * px
    y = ymin +  (ymax - ymin) / h * py
    return (x,y)
    #return corrige_ray.pixel_to_point(w, h, xmin, xmax, ymin, ymax, px, py)

## Question 7


"""Calcule l'intersection entre une sphere de centre c (vecteur) et de rayon r
    (flottant) et une droite passant par v (vecteur) et de direction d (vecteur)
""" 

def sphere_intersect (c, r, v, d):

  determinent=pow(2 * dot(d, (sub(v,c))),2) - 4 * (pow(norm(sub(v,c)),2) - pow(r,2))
  if determinent>0:
    k2=(-2 * (dot(d,sub(v,c))) - sqrt(determinent))/2
    if k2!=None and k2>=0:
      return k2
    else:
      return None
  #return corrige_ray.sphere_intersect(c, r, v ,d)

INF = float('inf')


"""Renvoie la sphère la plus proche qui intersecte la droite partant de o dans
  la direction d, ainsi que la distance d'intersection depuis o. S'il n'y a pas
  d'intersection, renvoie (None, INF)"""


## Question 8

def nearset_intersection(objs,o, d):
  distance=(None,INF)
  for obj in objs:
    new_distance=sphere_intersect(obj["center"],obj["radius"],o,d)
    if new_distance!=None and new_distance<distance[1]:
      distance=(obj,new_distance)
  return distance
  #return corrige_ray.nearset_intersection(objs,v, d)

## Question 9

"""calcule la couleur du point v se trouvant à la surface de l'objet obj.
  n est le vecteur normal au point d'intersection et l le vecteur unitaire dans
  la direction de la source de lumière.
 """ 
def compute_color (obj, v, n, l):
  a=obj["ambiant"]
  d=obj["diffuse"]
  s=obj["specular"]
  alpha=obj["shininess"]
  color=add((mul((pow(dot(n,normalize(add(l,v))),alpha/4)),s)),add(mul(dot(l,n),d),a))
  return color
  #return corrige_ray.compute_color(obj,v, n, l)


## NE PAS MODIFIER LE CODE CI-DESSOUS
def trace(w, h, xmin, xmax, ymin, ymax, camera, light,objs):

  img = init_image(w, h)

  for py in range(h):
    for px in range(w):
      x, y = pixel_to_point(w, h, xmin, xmax, ymin, ymax, px, py)
      p = (x, y, 0)
      vp = sub(p, camera)
      d = normalize(vp)

      obj, dist = nearset_intersection(objs, camera, d)
      if obj is None:
        couleur = (0, 0, 0)
      else:
        x_point = add(camera, mul(dist, d))
        l = normalize(sub(light, x_point))
        obstacle, dist_obst = nearset_intersection(objs, x_point, l)
        if dist_obst < norm(sub(light, x_point)):
          couleur = (0, 0, 0)
        else:
          n = normalize (sub(x_point, obj["center"]))
          couleur = compute_color(obj, camera, n, l)

      set_pixel(img, px, h - py - 1, couleur)

  return img



### Lecture de description de scene
###

def read_vector(s):
  fields = s.split(",")
  if len(fields) != 3:
    raise ValueError("Erreur de chargement")
  return [ float (n) for n in fields ]

def load_scene (chemin):
  """Charge un fichier de description de scène. En cas d'erreur, la fonction
     lève une exception 'Exception("Erreur de chargement")'
  """
  try:
    with open (chemin, "r") as f:
      w = int (f.readline())
      h = int (f.readline())
      xmin = float(f.readline())
      xmax = float(f.readline())
      ymin = float(f.readline())
      ymax = float(f.readline())
      camera = read_vector(f.readline())
      light = read_vector(f.readline())
      objects = list(DictReader(f, delimiter=";"))
      for obj in objects:
        obj['center'] = read_vector(obj['center'])
        obj['radius'] = float(obj['radius'])
        obj['ambiant'] = read_vector(obj['ambiant'])
        obj['diffuse'] = read_vector(obj['diffuse'])
        obj['specular'] = read_vector(obj['specular'])
        obj['shininess'] = min(100, max(0, float(obj['shininess'])))
        obj['reflection'] = min(1, max(0, float(obj['reflection'])))
    return (w, h, xmin, xmax, ymin, ymax, camera, light, objects)
  except:
      raise ValueError("Erreur de chargement")

def usage():
  print(f"Usage: {argv[0]} <fichier.scene>")
  exit (1)

if __name__ == "__main__":
  if len (argv) != 2:
    usage()

  fichier = argv[1]
  if len(fichier) < 6 or fichier[-6:] != ".scene":
    usage()

  out = fichier[0:-6] + ".ppm"

  w, h, xmin, xmax, ymin, ymax, camera, lum, objs = load_scene(fichier)
  img = trace(w, h, xmin, xmax, ymin, ymax, camera, lum, objs)
  save_image(out, img)
