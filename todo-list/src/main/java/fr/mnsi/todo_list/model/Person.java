package fr.mnsi.todo_list.model;

import java.util.ArrayList;
import java.util.List;
import fr.mnsi.todo_list.model.MemTaskManager;
import fr.mnsi.todo_list.model.Task;
import fr.mnsi.todo_list.model.TaskManager;

/**
 * Classe représentant une personne.
 */
public class Person {
    private String username;
    private String password;
    private TaskManager taskList;

    /**
     * Constructeur de la personne.
     *
     * @param username nom d'utilisateur
     * @param password mot de passe
     */
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        this.taskList = new MemTaskManager();
    }

    /**
     * Récupère le nom d'utilisateur de la personne.
     *
     * @return nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Récupère le mot de passe de la personne.
     *
     * @return mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Récupère la liste de tâches de la personne.
     *
     * @return liste de tâches
     */
    public TaskManager getTaskList() {
        return taskList;
    }

    /**
     * Ajoute une tâche à la liste de tâches de la personne.
     *
     * @param task tâche à ajouter
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    /**
     * Ajoute toutes tâches à la liste de tâches de la personne.
     *
     * @param tasks tâche à ajouter
     */
    public void addTasks(TaskManager tasks) {
        for(Task t : tasks.allTasks()){
            taskList.addTask(t);
        }
    }

    /**
     * Remplace toutes tâches à la liste de tâches de la personne.
     *
     * @param tasks tâche à ajouter
     */
    public void setTasks(List<Task> tasks) {
        taskList.setCollection((ArrayList<Task>) tasks);
    }

    /**
     * Supprime une tâche de la liste de tâches de la personne.
     *
     * @param task tâche à supprimer
     */
    public void deleteTask(Task task) {
        taskList.removeTask(task.getId());
    }
}
