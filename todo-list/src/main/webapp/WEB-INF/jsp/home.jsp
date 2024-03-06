<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO list</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="styles.css">

    </head>



    <body align="center">
    <div class="navbar">
              <a href="LoginServlet"><b>TODO list</b></a>
              <a href="/">Log out</a>
        </div>


        <h1 align="center"><font face="Arial">Notre TODO list!</font></h1>
        <h2>Ici aparaitront vos taches a realiser</h2>
        <!-- Ici il faudrait modifier quand on pourrait rejouter les taches de facon a lister automatiquement toutes les taches
         C'est a dire supprimer la deuxieme tache et ecrire quelque chose du type for qui va lister les taches-->
        <div>
            <ul style="list-style-type: none">

                <li align="center">
                    <b>Premiere tache</b><br/>
                    <a>Description de la premiere tache</a>
                </li><br/>
            </ul>

        </div>

        <form method="get" action="FindContactByNameServlet">
            <input type="text" name="s" placeholder="Veuillez ajoutez ici vos tâches à accomplir" style="width: 300px"/></input><br/><br/>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>