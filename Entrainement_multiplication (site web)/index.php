<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Multiplication</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    <script src="script.js" defer></script>
</head>
<body>
    <header>
        <button onclick="toggleDarkMode()">Mode sombre</button>
    </header>

    <form id="table-form" onsubmit="event.preventDefault(); displayMultiplicationTable();">
        <fieldset>
            <legend>Votre table de multiplication :</legend>
            <input type="number" id="fname" name="varnum" value="0" min="0">
            <input type="submit" value="Afficher">
        </fieldset>
    </form>
    
    <section id="multiplicationGrid">
    </section>

    <section id="history">
    </section>

    <section id="quiz">
        <h3>Quiz</h3>
        <p id="quizQuestion"></p>
        <input type="number" id="userAnswer" name="userAnswer" value="">
        <input type="hidden" id="correctAnswer" name="correctAnswer" value="">
        <button onclick="checkAnswer()">Vérifier</button>
    </section>

    <script>
        // Générer une question de quiz au chargement de la page
        generateQuiz();
    </script>
</body>
</html>
