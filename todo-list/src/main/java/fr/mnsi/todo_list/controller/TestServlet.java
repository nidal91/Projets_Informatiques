package fr.mnsi.todo_list.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet de test.
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

    /**
     * Gère la requête POST pour le servlet de test.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Appeler la méthode doGet pour gérer la requête POST
        doGet(req, resp);
    }

    /**
     * Gère la requête GET pour le servlet de test.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Rediriger vers la page de connexion avec le dispatcher
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        rd.forward(req, resp);

    }
}
