PersonsRefresh();

document.getElementById("PersonsRefresh").addEventListener("click", function () {
    PersonsRefresh();
});

document.getElementById("PersonAdd").addEventListener("click", function () {
    PersonAdd();
    document.getElementById("TablePersonsBody").innerHTML += "<tr><td>New</td><td>" + document.getElementById("PersonFName").value + "</td><td>" + document.getElementById("PersonLName").value + "</td><td>" + document.getElementById("PersonPhone").value + "</td></tr>";
});

document.getElementById("PersonEdit").addEventListener("click", function () {
    PersonEdit();
    PersonsRefresh();
});

//document.getElementById("PersonDelete").addEventListener("click", function () {
//    PersonDelete();
//    PersonsRefresh();
//});

function PersonsRefresh() {
    fetch("api/person/all", {method: "get"})
            .then(function (response) {
                if (!response.ok)
                {
                    var error = new Error(response.statusText);
                    error = response;
                    throw error;
                }
                return response.json();
            })
            .then(function (json) {
                document.getElementById("TablePersonsBody").innerHTML = "";

                var rows = "";

                for (var i in json)
                {
                    rows += '<tr>';
                    rows += '<td>' + json[i].id + '</td>';
                    rows += '<td>' + json[i].fName + '</td>';
                    rows += '<td>' + json[i].lName + '</td>';
                    rows += '<td>' + json[i].phone + '</td>';
                    rows += '<td><input type="button" onclick="PersonDelete(' + json[i].id + ')" value="Delete" /></td>';
                    rows += '<td><input type="button" onclick="PersonEdit(' + json[i].id + ')" value="Edit" /></td>';
                    rows += '</tr>';
                }

                document.getElementById("TablePersonsBody").innerHTML = rows;
            })
            .catch(function (error) {
                return error.json();
            })
            .then(function (json) {
                alert(json.description);
            });
}

function PersonAdd() {
    var person = {
        fName: document.getElementById("PersonFName").value,
        lName: document.getElementById("PersonLName").value,
        phone: Number(document.getElementById("PersonPhone").value)
    };

    fetch("api/person", {
        method: "post",
        body: JSON.stringify(person),
        headers: new Headers({'content-type': 'application/json'})
    })
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                alert("Person added!");
            })
            .catch(function (error) {
                alert("Person not added!");
            });
}
function PersonDelete(id) {
    fetch("api/person/" + id, {
        method: "delete"
    });
    PersonsRefresh();
}
function PersonEdit(id) {
    var person = {
        fName: document.getElementById("PersonFName").value,
        lName: document.getElementById("PersonLName").value,
        phone: Number(document.getElementById("PersonPhone").value)
    };
    fetch("api/person/" + id, {
        method: "put",
        body: JSON.stringify(person),
        headers: new Headers({'content-type': 'application/json'})
    })
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                alert("Person edited!");
            })
            .catch(function (error) {
                alert("Person not edited    !");
            });
    
}
