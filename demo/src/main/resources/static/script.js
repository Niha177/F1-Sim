function runSimulation() {
    const driverName = document.getElementById("driverName").value.trim();
    const trackName = document.getElementById("trackName").value.trim();
    const year = document.getElementById("year").value.trim();

    const url = `/simulate?driverName=${encodeURIComponent(driverName)}`
              + `&trackName=${encodeURIComponent(trackName)}`
              + `&year=${encodeURIComponent(year)}`;

    console.log(url);  

    fetch(url)
        .then(response => response.text())
        .then(data => {
            document.getElementById("result").innerText = data;
        })
        .catch(error => console.error("Error:", error));
}