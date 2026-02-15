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
        
function runSim() {
    const tires = Array.from(document.querySelectorAll(".tire-select")).map(s => s.value);

    const data = {
        driverName: document.getElementById("MADriverName").value.trim(),
        trackName: document.getElementById("MATrackName").value.trim(),
        team: document.getElementById("MATeamName").value.trim(),
        weather: document.getElementById("Weather").value.trim(),
        year: parseInt(document.getElementById("MAyear").value),       // number
        pitStops: parseInt(document.getElementById("Pit").value),     // number
        tires: tires
    };

    console.log("Sending to backend:", data);

    fetch("/simulater", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => document.getElementById("result").innerText = result)
    .catch(error => console.error("Error:", error));
}

/*function runSim() {

    const tires = [];

    const selects = document.querySelectorAll(".tire-select");

    selects.forEach(select => {
        tires.push(select.value);
    });

    console.log(tires);
}
    */

