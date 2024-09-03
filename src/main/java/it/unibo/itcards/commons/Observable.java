package it.unibo.itcards.commons;

public interface Observable {

    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver();
    
}
