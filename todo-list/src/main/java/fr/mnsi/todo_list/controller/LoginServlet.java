package fr.mnsi.todo_list.controller;

import fr.mnsi.todo_list.model.PersonDB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet pour la connexion des utilisateurs.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Gère la requête POST pour la connexion des utilisateurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            if (PersonDB.validateCredentials(username, password)) {
                AuthentificationPersonne.setUserLoggedIn(req, true);

                resp.sendRedirect("ListServlet");
            } else {
                resp.sendRedirect("LoginServlet");

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("LoginServlet");
        }
    }

    /**
     * Gère la requête GET pour afficher le formulaire de connexion.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Affichage du formulaire de connexion
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

}
