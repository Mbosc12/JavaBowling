package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

        //score final
        public int score = 0;
        //etat du tour ( 0, 1, 2 ou 3)
        public int touretat = 1;
        //etat du tour précédent (0, 1, 2 ou 3)
        public int touravant = 0;
        //nb du tour actuel 
        public int touractuel = 0;
        //variable du nombre de Quilles tombées lors de la première boule du lancé
        public int tempQuille = 0;
        
        
	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
            
	}
        
	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            //On appelle chaque fonction par rapport à l'état du tour ( [1] 1ere boule du tour, [0] 2eme boule du tour, [2] spare ou [3] strike)
            if (touretat == 0) {
                firstetat(nombreDeQuillesAbattues, tempQuille);
            } else if (touretat == 1) {
                secondetat(nombreDeQuillesAbattues);
            } else if (touretat == 2) {
                thirdetat(nombreDeQuillesAbattues);
            } else {
                fourthetat(nombreDeQuillesAbattues);
            }
        }
        
        // etat 0 : spare possible ou tir + x possible
        public void firstetat(int nombreDeQuillesAbattues, int tempQuille) {
            // Spare donc + 10 au score + passage etat 2 (spare)
            if (tempQuille + nombreDeQuillesAbattues == 10) {
                spare();
            // Sinon on additionne le score
            } else {
                score += tempQuille + nombreDeQuillesAbattues;
                touretat = 1;
            }
            //on reinitialise
            tempQuille = 0;
        }
        
        // etat 1 : strike possible, spare possible ( si touravant = 2 ), tir possible
        public void secondetat(int nombreDeQuillesAbattues) {
            if (nombreDeQuillesAbattues == 10) {
                // on verifie si c'est un spare ou un strike 
                // si c'était une deuxième boule jouée spare sinon strike
                if(touravant == 2) {
                    spare();
                } else {
                    strike();                    
                }
            } else {
                //Si c'était une boule jouée on additionne + c'était un état spare avant donc *2
                if(touravant == 2) {
                    score += tempQuille * 2 + nombreDeQuillesAbattues;
                } else {
                //Sinon on sauvegarde la boule jouée
                    tempQuille = nombreDeQuillesAbattues;
                    touretat = 0;   
                }
                touravant = 1;
            }
        }
        
        // etat 2 : strike possible, spare possible ( si touravant = 3), tir possible
        public void thirdetat(int nombreDeQuillesAbattues) {
            if(nombreDeQuillesAbattues == 10) {
                // on verifie si c'est un spare ou un strike 
                // si c'était une deuxième boule jouée spare sinon strike
                if (touretat == 3) {
                    spare();
                } else {
                    strike();
                }
            } else {
                // Si avant c'était un strike il faut multiplier par 2 le score actuelle et réinitialiser le jeu temporaire
                if(touravant == 3) {
                    score += nombreDeQuillesAbattues * 2;
                    tempQuille = 0;
                // Sinon on sauvegarde le jeu temporaire
                } else {
                    tempQuille = nombreDeQuillesAbattues;
                }
                
                touretat = 1;
                touravant = 2;
            }
        }
        
        // etat 3 : strike possible, tir possible
        public void fourthetat(int nombreDeQuillesAbattues) {
            if(nombreDeQuillesAbattues == 10) {
                // Au cas ou c'est un jeu parfait 
                if(touractuel < 10) {
                    if(touravant == 3) {
                        score += 10*2;
                    }   
                }
                strike();
                
            } else {
            // etant donner qu'on est sur un strike on multiplie par 2 et on passe à un etat 2 ( equivalence 2eme lancée strike et spare).
                tempQuille = nombreDeQuillesAbattues;
                score += tempQuille * 2;
                touretat = 2;
            }
            touravant = 3;
            touractuel++;
        }
        
        public void strike() {
            score += 10;
            touretat = 3;
        }
        
        public void spare() { 
            score += 10;
            touretat = 2;
        }
        
        
                
	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
            return score;
	}
}
