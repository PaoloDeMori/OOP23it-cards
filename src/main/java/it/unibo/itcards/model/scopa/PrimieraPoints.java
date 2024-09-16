package it.unibo.itcards.model.scopa;

import java.util.HashMap;
import java.util.Map;

public class PrimieraPoints {
    public static Map<Integer, Integer> scorePrimiera;

    public PrimieraPoints() {
        scorePrimiera = new HashMap<>();
        scorePrimiera.put(7, 21);
        scorePrimiera.put(6, 18);
        scorePrimiera.put(1,16);
        scorePrimiera.put(5,15);
        scorePrimiera.put(4, 14);
        scorePrimiera.put(3, 13);
        scorePrimiera.put(2, 12);
        scorePrimiera.put(10, 10);
        scorePrimiera.put(9, 10);
        scorePrimiera.put(8,10);
    }

    public Map<Integer, Integer> getPrimieraScoreMap(){
        return scorePrimiera;
    }
}
