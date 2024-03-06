package fr.mnsi.todo_list.model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet pour récupérer le gestionnaire de tâches.
 */
@WebServlet("/GetTaskManager")
public class GetTaskManager extends HttpServlet {

    /**
     * Gère la requête GET pour récupérer le gestionnaire de tâches.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer le gestionnaire de tâches à partir du contexte de servlet
        TaskManager tm = (TaskManager) getServletContext().getAttribute("tm");

        if (tm == null) {
            // Si le gestionnaire de tâches n'est pas encore créé, le créer et l'initialiser
            tm = new MemTaskManager();
            tm.test(); // à supprimer plus tard
            getServletContext().setAttribute("tm", tm);
        }
    }
}
