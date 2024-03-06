package fr.mnsi.todo_list.model;

import fr.mnsi.todo_list.model.Person;
import fr.mnsi.todo_list.model.PersonList;

import java.util.List;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe de gestion des utilisateurs.
 */
public class PersonDB {
    private static Map<String, Person> users = new HashMap<>();

    /**
     * Ajoute un utilisateur à la base de données.
     *
     * @param username nom d'utilisateur
     * @param password mot de passe
     */
    public static void addUser(String username, String password) {
        Person user = new Person(username, password);
        users.put(username, user);
    }

    /**
     * Valide les informations de connexion d'un utilisateur.
     *
     * @param username nom d'utilisateur
     * @param password mot de passe
     * @return true si les informations sont valides, sinon false
     */
    public static boolean validateCredentials(String username, String password) {
        Person user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * Vérifie si un utilisateur existe dans la base de données en fonction de son nom d'utilisateur.
     *
     * @param username nom d'utilisateur à vérifier
     * @return true si l'utilisateur existe, sinon false
     */
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }

    /**
     * Obtient un utilisateur par son nom d'utilisateur.
     *
     * @param username nom d'utilisateur
     * @return utilisateur correspondant, ou null s'il n'existe pas
     */
    public static Person getUserByUsername(String username) {
        return users.get(username);
    }
}
