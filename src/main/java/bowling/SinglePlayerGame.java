package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

        
        public int score = 0;
        public int tourprécédent = 1;
        public int tourrestant = 20;
        
        public int scoretemp = 0;
        
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
            if(tourrestant > 0) {
                if(tourprécédent == 1) {
                    if(nombreDeQuillesAbattues == 10) {
                        strike();
                    } else {
                        scoretemp = nombreDeQuillesAbattues;
                        tourprécédent = 0;
                    }
                    
                } else if(tourprécédent == 0) {
                    if(scoretemp + nombreDeQuillesAbattues == 10) {
                        spare();
                    } else {
                        score += scoretemp + nombreDeQuillesAbattues;
                        tourprécédent = 1;
                    }
                } else if(tourprécédent == 3) {
                    if( nombreDeQuillesAbattues == 10) {
                        strike();
                    } else {
                        scoretemp = nombreDeQuillesAbattues;
                        tourprécédent = 2;
                    }
                } else {
                    if( nombreDeQuillesAbattues == 10) {
                        strike();
                    } else {
                        scoretemp = nombreDeQuillesAbattues;
                        tourprécédent = 1;
                    }   
                }
            }else {
                
            }
        }

        public void strike() {
            if (tourprécédent == 3 || tourprécédent == 2) {
                score += 10*2;   
            } else {
                score += 10;   
            }
            tourprécédent = 3;
        }
        
        public void spare() {
            if (tourprécédent == 3 || tourprécédent == 2) {
                score += 10*2;   
            } else {
                score += 10;   
            }
            tourprécédent = 2;   
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
