package fr.mnsi.todo_list.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet pour la page d'accueil.
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {

    /**
     * Gère la requête POST pour la page d'accueil.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Appeler la méthode doGet pour gérer la requête POST
        doGet(req, resp);
    }

    /**
     * Gère la requête GET pour la page d'accueil.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!fr.mnsi.todo_list.controller.AuthentificationPersonne.isUserLoggedIn(req)) {
            // Vérifier si l'utilisateur est connecté, sinon le rediriger vers la page de connexion
            resp.sendRedirect("LoginServlet");
            return;
        }

        // Obtenir la date actuelle
        Date d = new Date();

        // Ajouter la date en tant qu'attribut de requête
        req.setAttribute("date", d);

        // Rediriger vers la page d'accueil avec le dispatcher
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        rd.forward(req, resp);

    }
}
