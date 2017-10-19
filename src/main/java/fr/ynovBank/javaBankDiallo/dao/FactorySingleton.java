package fr.ynovBank.javaBankDiallo.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactorySingleton {
   private static String PERSISTENCE_UNIT_NAME = "javaBankDiallo";
   private static EntityManagerFactory factory;
   
   private FactorySingleton() {
      // Exists only to defeat instantiation.
   }
   
   public static EntityManagerFactory getInstance() {
      if(factory == null) {
    	  factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
      }
      return factory;
   }
   
}