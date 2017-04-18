package nl.sogyo.mancalaweb;

import nl.sogyo.mancala.backend.Mancala;

public class MancalaSessionBean {

    public MancalaSessionBean() {
    }
    
    private Mancala mancala;
    
    public MancalaSessionBean(Mancala mancala) {
    	this.mancala = mancala;
    }
    
    public Mancala getMancala() {
    	return this.mancala;
    }
}
