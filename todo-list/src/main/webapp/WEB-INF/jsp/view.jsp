<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List, fr.mnsi.todo_list.model.*" %>
<%@ page import="java.text.DecimalFormat" %>


<!DOCTYPE html>
<html>
<head>
    <title>Liste des tâches</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="navbar">
    <a hreg=""><b>ToDo<i>List</i></b></a>
    <a href="LogoutServlet"><b>Log out</b></a>
</div>
<div>
    <br>
    <br>
    <h1 align="center"><font face="Arial">Notre TODO list!</font></h1>
    <h2 align="center">Ici apparaitront vos taches a réaliser</h2>
</div>
<div align="center">
    <ul style="list-style-type: none">
        <%
        List<Task> tm = (List<Task>) request.getAttribute("tasks");
        for (Task t:tm){
            System.out.println(t.isDone());
            String taskStyle = t.isDone() ? "text-decoration: line-through;" : "";

            out.println("<li><span class='left' style='" + taskStyle + "'><b>" + t.getNomTask() + "</b></br>"
                    + t.getDescTask() + "</span><span class='right'>"
                    + "<a href='DoneServlet?id=" + t.getId() + "'> &#9989;</a>"
                    + "<a href='DeleteServlet?id=" + t.getId() + "'> &#10060; </a></br>"
                    + t.getDateTask2()+
                    "</span></li>");
        }
        %>
    </ul>
</div>

<div id=userInput>
    <form method="get" action="FindContactByNameServlet">
        <div>
            <input type="text" name="nomTache" placeholder="Veuillez ajouter ici vos tâches à accomplir" style="width: 80%"/>
            <input type="date" name="dateTache"/><br/>
            <input type="text" name="descTache" placeholder="Veuillez ajouter ici la description de la tâche à accomplir" style="width: 80%" />
            <button type="submit" class="button">Ajouter</button>
        </div>
    </form>
</div>

<div align="center" id=stats>


    <%
    double progression = (double) request.getAttribute("progression");
    DecimalFormat df = new DecimalFormat("#");
    String formattedProgression = df.format(progression);
    %>
    <!--<p>Progression : <%= df.format(progression) %>%</p>-->
    <p><b><u>Votre progres :  </b></u></p>

    <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="--value: <%= formattedProgression %>"></div>
    <form method="post" action="SortTasksServlet">
        <button type="submit" class="button">Trier par date</button>
    </form>
</div>

</body>
</html>
