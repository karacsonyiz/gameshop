window.onload = function() {
    updateTable();
    document.querySelector("#resetbutton").onclick = resetBasket;
    document.querySelector("#orderbutton").onclick = orderBasket;
};

function updateTable() {
fetch("api/basket")
    .then(function(request) {
        return request.json();
    })
    .then(function(jsonData) {
    console.log(jsonData);
    fillTable(jsonData);
    });
}

function fillTable(jsonData) {
    if(jsonData.length == 0){
            document.querySelector("#orderbutton").setAttribute("class","btn btn-secondary");
            document.querySelector("#orderbutton").setAttribute("disabled",true);
            document.querySelector("#resetbutton").setAttribute("class","btn btn-secondary");
            document.querySelector("#resetbutton").setAttribute("disabled",true);
        }
    let tbody = document.querySelector("#basketitems-tbody");
    tbody.innerHTML = "";
    for(i in jsonData){
        let tr = document.createElement("tr");
        let idTd = document.createElement("td");
        idTd.innerHTML = jsonData[i].productId;
        tr.appendChild(idTd);
        let nameTd = document.createElement("td");
        nameTd.innerHTML = jsonData[i].name;
        tr.appendChild(nameTd);
        let producerTd = document.createElement("td");
        producerTd.innerHTML = jsonData[i].producer;
        tr.appendChild(producerTd);
        let priceTd = document.createElement("td");
        priceTd.innerHTML = jsonData[i].price;
        tr.appendChild(priceTd);
        let deleteItemTd = document.createElement("td");
        let deleteItemButton = document.createElement("button");
        deleteItemButton.innerHTML = "Törlés";
        deleteItemButton.addEventListener("click", function () {clickingOnDeleteItemButton(this.parentElement.parentElement)});
        deleteItemTd.appendChild(deleteItemButton);
        tr.appendChild(deleteItemTd);
        tbody.appendChild(tr);
    }
}

function clickingOnDeleteItemButton(data){
    let productId = data.firstChild.innerHTML;
    let url = "api/basket/" + productId;
        if (confirm("Biztos szeretné törölni ezt az elemet a kosárból?")) {
            fetch(url, {
                method: "DELETE"
            }).then(function (response) {
                return response.json()
            }).then(responseJson => updateTable())
        }
}

function orderBasket() {
    if(confirm("Megrendeli a termékeket?")) {
        fetch("/api/myorders", {
        method: "POST"
        }).then(function (response) {
            return response.json()
        }).then(responseJson => window.location = "/customerorders.html")
    }
}

function resetBasket(){
    if (confirm("Biztos szeretné törölni az ÖSSZES elemet a kosárból?")) {
        fetch("api/basket", {
            method: "DELETE"
        }).then(function (response) {
            return response.json()
        }).then(responseJson => updateTable())
    }
}