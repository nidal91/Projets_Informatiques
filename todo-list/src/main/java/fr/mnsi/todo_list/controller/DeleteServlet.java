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
 * Servlet pour supprimer une tâche.
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    /**
     * Gère la requête POST pour supprimer une tâche.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Appeler la méthode doGet pour gérer la requête POST
        doGet(req, resp);
    }

    /**
     * Gère la requête GET pour supprimer une tâche.
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

        // Récupérer l'ID de la tâche à supprimer à partir des paramètres de la requête
        String taskId = req.getParameter("id");

        try {
            // Convertir l'ID en entier
            int id = Integer.parseInt(taskId);

            // Supprimer la tâche du gestionnaire de tâches
            tm.removeTask(id);

        } catch (NullPointerException | NumberFormatException e) {
            // Gérer les exceptions éventuelles (par exemple, ID non valide)
        }

        // Rediriger vers la servlet "ListServlet" pour afficher la liste des tâches mise à jour
        RequestDispatcher rd = req.getRequestDispatcher("/ListServlet");
        rd.forward(req, resp);
    }
}
