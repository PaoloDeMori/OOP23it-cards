package it.unibo.itcards.commons;

/**
 * Interface for an observable object.
 */
public interface Observable {

    /**
     * Adds an observer.
     * 
     * @param observer
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer.
     * 
     * @param observer
     */
    void deleteObserver(Observer observer);

    /**
     * Notifies all observers.
     */
    void notifyObserver();
}
