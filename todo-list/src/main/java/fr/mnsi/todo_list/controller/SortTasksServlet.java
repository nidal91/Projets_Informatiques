package fr.mnsi.todo_list.controller;

import fr.mnsi.todo_list.model.*;
import fr.mnsi.todo_list.controller.AuthentificationPersonne;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Servlet pour la liste des tâches triée.
 */
@WebServlet("/SortTasksServlet")
public class SortTasksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!AuthentificationPersonne.isUserLoggedIn(req)) {
            // L'utilisateur n'est pas connecté, redirigez-le vers la page de connexion
            resp.sendRedirect("LoginServlet");
            return;
        }

        // Récupérer le nom d'utilisateur de la personne connectée
        String username = AuthentificationPersonne.getLoggedInUsername(req);

        // Récupérer le mot de passe de la personne connectée
        String password = AuthentificationPersonne.getLoggedInPassword(req);

        // Récupérer la personne à partir de la base de données des personnes
        Person person = PersonDB.getUserByUsername(username);

        if (person == null) {
            // L'utilisateur n'existe pas dans la base de données, créez un nouvel utilisateur
            person = new Person(username, password);
            PersonDB.addUser(username,password);
        }

        TaskManager tm = (TaskManager) getServletContext().getAttribute("tm");
        if (tm == null) {
            tm = new MemTaskManager();
            getServletContext().setAttribute("tm", tm);
        }
        person.setTasks(tm.allTasks());
        List<Task> tasks = person.getTaskList().allTasks();
        // Trier les taches
        Collections.sort(tasks, Task.ComparatorDate);
        req.setAttribute("tasks", tasks);


        // Définir l'attribut "progression"
        double progression = tm.progression();
        req.setAttribute("progression", progression);



        // Transférer (forward) vers la page JSP "view.jsp"
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/view.jsp");
        rd.forward(req, resp);
    }


}

