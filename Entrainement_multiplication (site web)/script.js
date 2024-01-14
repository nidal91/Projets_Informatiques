let darkMode = false;

function toggleDarkMode() {
    darkMode = !darkMode;
    document.body.classList.toggle("dark-mode");
}

async function displayMultiplicationTable() {
    const number = document.getElementById("fname").value;
    const response = await fetch(`multiplication_table.php?number=${number}`);
    const table = await response.text();
    document.getElementById("multiplicationGrid").innerHTML = table;
}

function checkAnswer() {
    const userAnswer = parseInt(document.getElementById("userAnswer").value, 10);
    const correctAnswer = parseInt(document.getElementById("correctAnswer").value, 10);

    if (userAnswer === correctAnswer) {
        alert("Bonne réponse!");
    } else {
        alert("Mauvaise réponse. Essayez encore!");
    }

    generateQuiz();
}

function generateQuiz() {
    const num1 = Math.floor(Math.random() * 10) + 1;
    const num2 = Math.floor(Math.random() * 10) + 1;
    const correctAnswer = num1 * num2;

    document.getElementById("quizQuestion").innerText = `${num1} x ${num2} = `;
    document.getElementById("correctAnswer").value = correctAnswer;
}
