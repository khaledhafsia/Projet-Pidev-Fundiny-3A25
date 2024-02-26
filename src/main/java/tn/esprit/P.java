package tn.esprit;

public class P {
    private static P instance;
    private P(){

    }
    public static P getInstance(){
        if (instance == null)
            instance = new P();
        return instance;
    }
}
