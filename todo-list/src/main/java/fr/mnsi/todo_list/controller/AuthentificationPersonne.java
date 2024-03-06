package fr.mnsi.todo_list.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Utilitaire pour gérer l'authentification des utilisateurs.
 */
public class AuthentificationPersonne {

    public static final String SESSION_ATTRIBUTE_LOGGED_IN = "loggedIn";
    public static final String SESSION_ATTRIBUTE_USERNAME = "username";
    public static final String SESSION_ATTRIBUTE_PASSWORD = "password";

    /**
     * Définit l'état de connexion de l'utilisateur.
     *
     * @param request  la requête HTTP
     * @param loggedIn l'état de connexion (true si connecté, false sinon)
     */
    public static void setUserLoggedIn(HttpServletRequest request, boolean loggedIn) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ATTRIBUTE_LOGGED_IN, loggedIn);
    }

    /**
     * Vérifie si l'utilisateur est connecté.
     *
     * @param request la requête HTTP
     * @return true si l'utilisateur est connecté, false sinon
     */
    public static boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(SESSION_ATTRIBUTE_LOGGED_IN) != null
                && (boolean) session.getAttribute(SESSION_ATTRIBUTE_LOGGED_IN);
    }

    /**
     * Obtient le nom d'utilisateur de l'utilisateur connecté.
     *
     * @param request la requête HTTP
     * @return le nom d'utilisateur de l'utilisateur connecté, ou null s'il n'est pas connecté
     */
    public static String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(SESSION_ATTRIBUTE_LOGGED_IN) != null
                && (boolean) session.getAttribute(SESSION_ATTRIBUTE_LOGGED_IN)) {
            return (String) session.getAttribute(SESSION_ATTRIBUTE_USERNAME);
        }
        return null;
    }

    /**
     * Obtient le mot de passe de l'utilisateur connecté.
     *
     * @param request la requête HTTP
     * @return le mot de passe de l'utilisateur connecté, ou null s'il n'est pas connecté
     */
    public static String getLoggedInPassword(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(SESSION_ATTRIBUTE_LOGGED_IN) != null
                && (boolean) session.getAttribute(SESSION_ATTRIBUTE_LOGGED_IN)) {
            return (String) session.getAttribute(SESSION_ATTRIBUTE_PASSWORD);
        }
        return null;
    }

    /**
     * Définit le nom d'utilisateur de l'utilisateur connecté.
     *
     * @param request  la requête HTTP
     * @param username le nom d'utilisateur de l'utilisateur connecté
     */
    public static void setLoggedInUsername(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ATTRIBUTE_USERNAME, username);
    }

    /**
     * Définit le mot de passe de l'utilisateur connecté.
     *
     * @param request  la requête HTTP
     * @param password le mot de passe de l'utilisateur connecté
     */
    public static void setLoggedInPassword(HttpServletRequest request, String password) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ATTRIBUTE_PASSWORD, password);
    }
}
