package main;

import connection.*;
import gestion.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException 
    {
        Connexion connexion = new Connexion();
        connexion.connect();
        Gestion gestion = new Gestion(connexion);
        gestion.gestionAcceuil();
        connexion.disconnect();
        
    }
}