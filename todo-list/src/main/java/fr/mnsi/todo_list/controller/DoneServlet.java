package fr.mnsi.todo_list.controller;

import fr.mnsi.todo_list.model.MemTaskManager;
import fr.mnsi.todo_list.model.Task;
import fr.mnsi.todo_list.model.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

/**
 * Servlet pour marquer une tâche comme terminée ou non terminée.
 */
@WebServlet("/DoneServlet")
public class DoneServlet extends HttpServlet {

    /**
     * Gère la requête POST pour marquer une tâche comme terminée ou non terminée.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Appeler la méthode doGet pour gérer la requête POST
        doGet(req, resp);
    }

    /**
     * Gère la requête GET pour marquer une tâche comme terminée ou non terminée.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Récupérer le gestionnaire de tâches à partir de l'attribut de contexte de la servlet
        TaskManager tm = (TaskManager) getServletContext().getAttribute("tm");
        if (tm == null) {
            // Si le gestionnaire de tâches n'existe pas dans l'attribut de contexte, le créer et le sauvegarder dans l'attribut de contexte
            tm = new MemTaskManager();
            getServletContext().setAttribute("tm", tm);
        }

        // Récupérer l'ID de la tâche à modifier à partir des paramètres de la requête
        String taskId = req.getParameter("id");

        try {
            // Convertir l'ID en entier
            int id = Integer.parseInt(taskId);

            // Récupérer la tâche correspondante du gestionnaire de tâches
            Task task = tm.getTask(id);

            // Inverser l'état "done" de la tâche (true -> false, false -> true)
            boolean isDone = task.isDone();
            task.setDone(!isDone);
        } catch (NullPointerException | NumberFormatException e) {
            // Gérer les exceptions éventuelles (par exemple, ID non valide)
        }

        // Rediriger vers la servlet "ListServlet" pour afficher la liste des tâches mise à jour
        RequestDispatcher rd = req.getRequestDispatcher("/ListServlet");
        rd.forward(req, resp);
    }
}
