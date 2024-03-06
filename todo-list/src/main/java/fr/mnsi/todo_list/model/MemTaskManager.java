package fr.mnsi.todo_list.model;

import java.util.*;
import java.text.DecimalFormat;

/**
 * Une implémentation de l'interface TaskManager qui stocke les tâches en mémoire.
 */
public class MemTaskManager implements TaskManager {

    static int compteur = 0; // identifiant de la tâche.
    static HashMap<String, ArrayList<Task>> categorie = new HashMap<>();

    private ArrayList<Task> collection = new ArrayList<>();

    /**
     * Définit la collection de tâches.
     *
     * @param collections la liste de tâches à définir
     */
    public void setCollection(List<Task> collections) {
        this.collection = new ArrayList<Task>(collections);
    }

    @Override
    public List<Task> allTasks() {
        return new ArrayList<Task>(collection);
    }

    /**
     * Ajoute une tâche à la collection.
     *
     * @param a la tâche à ajouter
     */
    public void addTask(Task a) {
        if (a.isExpired()) {
            a.setDateTask();
        }
        int i = compteur;
        a.setId(i);
        this.collection.add(a);
        compteur++;
    }

    /**
     * Supprime une tâche de la collection en fonction de son identifiant.
     *
     * @param id l'identifiant de la tâche à supprimer
     */
    public void removeTask(int id) {
        for (int i = 0; i < collection.size(); i++) {
            Task t = collection.get(i);
            if (t.getId() == id) {
                collection.remove(i);
                break;
            }
        }
    }

    /**
     * Supprime toutes les tâches de la collection.
     */
    public void removeAllTask() {
        for (int i = 0; i < collection.size(); i++) {
            removeTask(i);
        }
    }

    @Override
    public Task getTask(int id) {
        for (int i = 0; i < collection.size(); i++) {
            Task t = collection.get(i);
            if (t.getId() == id) {
                return collection.get(i);
            }
        }
        return collection.get(0);
    }

    /**
     * Calcule la progression en pourcentage des tâches terminées dans la collection.
     *
     * @return la progression en pourcentage
     */
    public double progression() {
        double cpt = 0;
        for (Task a : this.collection) {
            if (a.isDone()) {
                cpt++;
            }
        }
        return cpt / this.collection.size() * 100;
    }

    /**
     * Trie la collection de tâches par date.
     */
    public void trier() {
        Collections.sort(this.collection, Task.ComparatorDate);
    }

    @Override
    public String toString() {
        for (Task a : collection) {
            System.out.println(a);
        }
        return "";
    }

    /**
     * Méthode de test pour ajouter quelques tâches à la collection.
     */
    public void test() {
        Task a1 = new Task("Faire ses devoirs", new Date(2023 - 1900, 8, 6, 14, 50));
        Task a2 = new Task("Ranger les courses ", new Date(2023 - 1900, 9, 6, 11, 50));
        a1.setDescTask("en bdd");
        a2.setDescTask("dans la cuisine");
        addTask(a1);
        addTask(a2);
    }

}
