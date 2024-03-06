package fr.mnsi.todo_list.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet pour la déconnexion des utilisateurs.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    /**
     * Gère la requête GET pour la déconnexion des utilisateurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer la session utilisateur
        HttpSession session = req.getSession(false);
        if (session != null) {
            // Détruire la session utilisateur
            session.invalidate();
        }

        // Rediriger vers la page de connexion
        resp.sendRedirect("LoginServlet");
    }
}
