package fr.mnsi.todo_list.test;

import fr.mnsi.todo_list.model.MemTaskManager;
import fr.mnsi.todo_list.model.Task;
import fr.mnsi.todo_list.model.TaskManager;
import java.text.DecimalFormat;

import java.util.Date;
import java.util.HashMap;

public class TestMemTaskManager  {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static <collection> void main(String[] args) {
        Task a= new Task("ranger sa chambre",new Date(2023-1900 ,03,6,10,50,12));
        System.out.println(a.getNomTask());
        System.out.println("expiré : "+a.isExpired());
        a.setDone(false);
        System.out.println("tache éffectué : "+a.isDone());
        a.setNomTask("faire à manger");
        System.out.println(a.getNomTask());
        System.out.println(a.getDateTask());
        TaskManager tm = new MemTaskManager();








         //Scénarios de tests
         Task a1 = new Task("Faire ses devoirs", new Date(2023-1900, 8, 6, 14 , 50));
         Task a2 = new Task("Ranger les courses ", new Date(2023-1900, 9, 6, 11 , 50));
         Task a3 = new Task("Rendez-vous chez le dentiste ", new Date(2023-1900, 6, 23, 3 , 45));
         TaskManager nidalliste= new MemTaskManager();
         a1.setDone(true);
         nidalliste.addTask(a1);
         nidalliste.addTask(a2);
         nidalliste.addTask(a3);
        System.out.println(nidalliste);
         nidalliste.trier();



         double testpourcent=nidalliste.progression();
         System.out.println("progression de vos tâches : "+df.format(testpourcent)+"%");
         System.out.println(nidalliste);



    }
}
