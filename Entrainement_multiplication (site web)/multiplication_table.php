<?php
if (isset($_GET["number"])) {
    $number = intval($_GET["number"]);

    echo "<table>";
    for ($i = 1; $i <= 10; $i++) {
        $result = $number * $i;
        echo "<tr><td>{$number} x {$i} = {$result}</td></tr>";
    }
    echo "</table>";
}
?>
