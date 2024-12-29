package org.projet_integre.online_book.models;

import java.util.Random;

public class ISBNGenerator {
    public static String generateISBN() {
        // Préfixe standard pour ISBN-13
        String prefix = "978";
        Random random = new Random();

        // Génération aléatoire pour les autres parties
        int group = random.nextInt(10); // Par exemple, une région de 0 à 9
        int publisher = random.nextInt(1000); // Identifiant éditeur
        int title = random.nextInt(100000); // Identifiant ouvrage

        // Concaténation des parties sans chiffre de contrôle
        String isbnWithoutCheckDigit = String.format("%s%d%03d%05d", prefix, group, publisher, title);

        // Calcul du chiffre de contrôle
        int checkDigit = calculateCheckDigit(isbnWithoutCheckDigit);

        // Retourner l'ISBN complet
        return isbnWithoutCheckDigit + checkDigit;
    }

    private static int calculateCheckDigit(String isbnWithoutCheckDigit) {
        int sum = 0;
        for (int i = 0; i < isbnWithoutCheckDigit.length(); i++) {
            int digit = Character.getNumericValue(isbnWithoutCheckDigit.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int remainder = sum % 10;
        return (remainder == 0) ? 0 : 10 - remainder;
    }
}

