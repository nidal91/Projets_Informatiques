package fr.mnsi.todo_list.model;
import java.util.*;

import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Une classe représentant une tâche.
 */
public class Task {

    private String nomTask;
    private boolean done;
    private boolean expired;
    private Date dateTask;
    private int id;
    private String descTask;

    /**
     * Obtient le nom de la tâche.
     *
     * @return le nom de la tâche
     */
    public String getNomTask() {
        return nomTask;
    }

    /**
     * Définit le nom de la tâche.
     *
     * @param nomTask le nom de la tâche à définir
     */
    public void setNomTask(String nomTask) {
        this.nomTask = nomTask;
    }

    /**
     * Vérifie si la tâche est terminée.
     *
     * @return true si la tâche est terminée, false sinon
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Définit l'état de la tâche (terminée ou non).
     *
     * @param done l'état de la tâche à définir
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Vérifie si la date de la tâche est dépassée.
     *
     * @return true si la date de la tâche est dépassée, false sinon
     */
    public boolean isExpired() {
        Date currentDate = new Date();
        return dateTask.before(currentDate);
    }

    /**
     * Obtient l'identifiant de la tâche.
     *
     * @return l'identifiant de la tâche
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la tâche.
     *
     * @param id l'identifiant de la tâche à définir
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définit la date de la tâche à la date actuelle.
     */
    public void setDateTask() {
        dateTask = new Date();
    }

    /**
     * Obtient la date de la tâche.
     *
     * @return la date de la tâche
     */
    public Date getDateTask() {
        return dateTask;
    }

    /**
     * Obtient la date de la tâche au format de chaîne de caractères.
     *
     * @return la date de la tâche au format de chaîne de caractères
     */
    public String getDateTask2() {
        return dateTask.toLocaleString();
    }

    /**
     * Définit la description de la tâche.
     *
     * @param descTask la description de la tâche à définir
     */
    public void setDescTask(String descTask) {
        this.descTask = descTask;
    }

    /**
     * Obtient la description de la tâche.
     *
     * @return la description de la tâche
     */
    public String getDescTask() {
        return descTask;
    }

    /**
     * Constructeur de la classe Task.
     *
     * @param nomTask  le nom de la tâche
     * @param dateTask la date de la tâche
     */
    public Task(String nomTask, Date dateTask) {
        this.nomTask = nomTask;
        this.descTask = "";
        this.dateTask = dateTask;
        this.done = false;
        this.expired = false;
        this.id = 0;
    }

    /**
     * Comparateur de tâches basé sur la date de la tâche.
     */
    public static Comparator<Task> ComparatorDate = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.getDateTask().compareTo(o2.getDateTask());
        }
    };

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la tâche.
     *
     * @return la représentation de la tâche sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        return "Task " + id + " : " + " TaskName = " + nomTask + " || Done = " + done + " || Expired = " + expired + " || dateTask = " + dateTask;
    }
}
