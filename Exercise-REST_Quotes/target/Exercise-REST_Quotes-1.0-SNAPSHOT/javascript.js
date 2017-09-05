fetch("api/quote/random", {method: "get"})
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            document.getElementById("quote").innerHTML = 
                    data.quote;
});

function newQuote() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("quote").innerHTML = 
                    this.responseText;
        }
    };
    xhttp.open("GET", "api/quote/randomText", true);
    xhttp.send();
};



