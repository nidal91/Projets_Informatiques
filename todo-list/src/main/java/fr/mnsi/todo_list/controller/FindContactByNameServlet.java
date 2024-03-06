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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet pour trouver une tâche par son nom.
 */
@WebServlet("/FindContactByNameServlet")
public class FindContactByNameServlet extends HttpServlet {

    /**
     * Gère la requête POST pour trouver une tâche par son nom.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Appeler la méthode doGet pour gérer la requête POST
        doGet(req, resp);
    }

    /**
     * Gère la requête GET pour trouver une tâche par son nom.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!fr.mnsi.todo_list.controller.AuthentificationPersonne.isUserLoggedIn(req)) {
            // Vérifier si l'utilisateur est connecté, sinon le rediriger vers la page de connexion
            resp.sendRedirect("LoginServlet");
            return;
        }

        // Récupérer le gestionnaire de tâches à partir de l'attribut de contexte de la servlet
        TaskManager tm = (TaskManager) getServletContext().getAttribute("tm");
        if (tm == null) {
            // Si le gestionnaire de tâches n'existe pas dans l'attribut de contexte, le créer et le sauvegarder dans l'attribut de contexte
            tm = new MemTaskManager();
            tm.test(); // à supprimer plus tard
            getServletContext().setAttribute("tm", tm);
        }

        // Récupérer les paramètres de la requête (nom de la tâche, description de la tâche, date de la tâche)
        String descTask = req.getParameter("descTache");
        String nomTask = req.getParameter("nomTache");
        String dateStr = req.getParameter("dateTache");
        Date dateTask;

        try {
            if (dateStr == null || dateStr.isEmpty()) {
                // Vérifier si la date est nulle ou vide
                dateTask = new Date(); // Initialiser une nouvelle instance de Date avec la date actuelle
            } else {
                dateTask = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // Convertir la chaîne de caractères en Date
            }

            // Créer une nouvelle tâche avec les informations fournies
            Task add = new Task(nomTask, dateTask);
            add.setDescTask(descTask);

            // Ajouter la tâche au gestionnaire de tâches
            tm.addTask(add);
        } catch (ParseException | NullPointerException | NumberFormatException e) {
            // Gérer les exceptions éventuelles (par exemple, erreur de format de date)
        }

        // Rediriger vers la servlet "ListServlet" pour afficher la liste des tâches mise à jour
        resp.sendRedirect("ListServlet");

        // Rediriger vers la servlet "ListServlet" avec le dispatcher (optionnel)
        // RequestDispatcher rd = req.getRequestDispatcher("/ListServlet");
        // rd.forward(req, resp);
    }
}
