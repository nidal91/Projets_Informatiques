<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="styles.css">
</head>
<body align="center">
    <div class="navbar">
        <a><b>ToDo<i>List</i></b></a>
    </div>
    <br><br>
    <h1>Inscription</h1>
    <form method="post" action="RegistrationServlet">
        <label for="username">Nom d'utilisateur:</label><br>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Mot de passe:</label><br>
        <input type="password" id="password" name="password" required><br>
        <label for="email">Adresse e-mail:</label><br>
        <input type="email" id="email" name="email" required><br>
        <button type="submit" class="button">S'inscrire</button>
    </form>
    <form method="get" action="LoginServlet">
        <button type="submit" class="button">Se connecter</button>
    </form>
</body>
</html>
