Statistiques détaillées pour chaque numéro Euromillion :

état de « forme » d’un numéro :
Écart actuel		: nb tirages où le numéro n'est pas sortie
Écart ponctuel		: écart moyen récent sur ses 20 dernières sorties.

Sorties				: nb de fois sortie depuis la création
Fréquence de sortie	: nb de fois sorti sur nb tirage
Écart max			: nb de tirage max pendant lequel  le numéro s'est fait attendre

L’état de forme des numéros :
Forme récente		: nb de fois où le numéro est sortie sur les 10 derniers tirages
Forme générale		: nb de fois où le numéro est sortie sur les 30 derniers tirages

Les numéros les moins fréquents du moment	: Les 10 numéros les plus « froid » des dernières semaines.
Les numéros les plus attendus : les dix numéros qui ont été tirés au sort il y a le plus longtemps. 
		Ainsi, certains numéros peuvent ne jamais avoir été tirés au sort pendant plusieurs semaines, 
		ils se retrouvent dans ce pronostic.
Les numéros les moins attendus : les tout derniers numéros qui ont été tirés au sort. 
		Il s’agit des numéros « chaud », ils peuvent être analysés comme des numéros à fort potentiel, 
		ou bien à l’inverse comme des numéros qui ne tomberont plus, selon votre superstition.
top 3 des numéros et des étoiles les plus souvent sorties (numéros + nb de fois sortie)
top 3 des numéros Euromillions et des étoiles les moins souvent tombés

Le fichier d'entrée est construit comme suit:
----------------|-------------------------------------------------------------------------------|---------------------------|
Nom du champ 	|Description 																	|Format						|
----------------|-------------------------------------------------------------------------------|---------------------------|
NUM 			|Numéro d'ordre du tirage 														|Numérique					|
DATE 			|Date du tirage 																|Année-Mois-Jour			|
JACKPOT 		|Montant du jackpot annoncé (en euros) 											|Numérique sans décimale	|
N1 à N5 		|Les 5 numéros du tirage (en ordre croissant ou dans l'ordre de sortie) 		|Numérique					|
E1 et E2 		|Les 2 étoiles du tirage (en ordre croissant ou dans l'ordre de sortie) 		|Numérique					|
NBJEU 			|Nombre de combinaisons jouées en Europe 										|Numérique					|
EU1 à EU12 		|Le nombre de gagnants en Europe sur les rangs de 1 à 12 						|Numérique					|
NB1 à NB12 		|Le nombre de gagnants du pays demandé sur les rangs de 1 à 12					|Numérique					|
MT1 à MT12 		|Le montant unitaire des gains pour le pays demandé (et dans la devise du pays) |Numérique avec 2 décimales	|
----------------|-------------------------------------------------------------------------------|---------------------------|
Nota : NB1 à NB12 sont disponible uniquement pour la France, la Belgique, l'Irlande, le Portugal et l'Espagne 