package it.unibo.itcards.view;


import it.unibo.itcards.commons.Observer;


public interface View extends Observer{
    void show();

    void start();

    void stop();

    public void aiCanPlay();

    public void playerCanPlay();
}
