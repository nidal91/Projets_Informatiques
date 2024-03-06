package fr.mnsi.todo_list.controller;

import fr.mnsi.todo_list.model.MemTaskManager;
import fr.mnsi.todo_list.model.Task;
import fr.mnsi.todo_list.model.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Servlet pour ajouter une tâche.
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {

    /**
     * Gère la requête POST pour ajouter une tâche.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer le gestionnaire de tâches à partir de l'attribut de contexte de la servlet
        TaskManager tm = (TaskManager) getServletContext().getAttribute("tm");

        if (tm == null) {
            // Si le gestionnaire de tâches n'existe pas dans l'attribut de contexte, le créer et le sauvegarder dans l'attribut de contexte
            tm = new MemTaskManager();
            getServletContext().setAttribute("tm", tm);
        }

        // Récupérer les informations de la nouvelle tâche à partir des paramètres de la requête
        String nomTask = request.getParameter("nomTask");
        Task newTask = new Task(nomTask, new Date(2023-1900, 8, 6, 14 , 50));

        // Ajouter la nouvelle tâche au gestionnaire de tâches
        tm.addTask(newTask);



        // Rediriger vers la page "view.jsp"
        response.sendRedirect("view.jsp");
    }
}
