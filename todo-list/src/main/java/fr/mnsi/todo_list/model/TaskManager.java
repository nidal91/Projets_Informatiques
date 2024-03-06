package fr.mnsi.todo_list.model;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
   List<Task> allTasks();


    public void addTask( Task a );

    public void  removeTask( int id);

    public void  removeAllTask();

 public void  setCollection(List<Task> collections);

    public Task getTask(int id);


    /**
     * Méthode qui trie les tâches selon la date.
     */
    public void trier();
    public double progression();
    @Override
    public String toString();
    public void test();


}
