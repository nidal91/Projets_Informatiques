package fr.mnsi.todo_list.controller;

import fr.mnsi.todo_list.model.PersonDB;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet pour l'inscription des utilisateurs.
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    /**
     * Gère la requête POST pour l'inscription des utilisateurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            if (!PersonDB.userExists(username)) {
                PersonDB.addUser(username, password);
                AuthentificationPersonne.setUserLoggedIn(req, true);
                AuthentificationPersonne.setLoggedInPassword(req,password);
                AuthentificationPersonne.setLoggedInUsername(req,username);


                resp.sendRedirect("ListServlet");
            } else {
                resp.sendRedirect("RegistrationServlet");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("RegistrationServlet");
        }
    }

    /**
     * Gère la requête GET pour afficher le formulaire d'inscription.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Affichage du formulaire d'inscription
        request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
    }
}
