package fr.mnsi.todo_list.model;

import fr.mnsi.todo_list.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une liste de personnes.
 */
public class PersonList {
    private List<Person> persons;

    /**
     * Constructeur de la liste de personnes.
     */
    public PersonList() {
        persons = new ArrayList<>();
    }

    /**
     * Ajoute une personne à la liste.
     *
     * @param person personne à ajouter
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Récupère la liste de toutes les personnes.
     *
     * @return liste de personnes
     */
    public List<Person> getAllPersons() {
        return persons;
    }

    /**
     * Récupère une personne en fonction de son nom d'utilisateur.
     *
     * @param username nom d'utilisateur
     * @return personne correspondante ou null si non trouvée
     */
    public Person getPersonByUsername(String username) {
        for (Person person : persons) {
            if (person.getUsername().equals(username)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Supprime une personne de la liste.
     *
     * @param person personne à supprimer
     * @return true si la personne a été supprimée avec succès, sinon false
     */
    public boolean removePerson(Person person) {
        return persons.remove(person);
    }
}
