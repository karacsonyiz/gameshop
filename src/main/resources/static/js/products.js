window.onload = function() {
    updateTable();
};

function updateTable() {
fetch("api/products")
    .then(function(request) {
        return request.json();
    })
    .then(function(jsonData) {
    console.log(jsonData);
    fillDivs(jsonData);
    });
}

function fillDivs(jsonData) {
        let gamesDiv = document.querySelector("#gamesdiv");
        for(let i in jsonData) {
            let productDiv = document.createElement("div");
            productDiv.setAttribute("class","col-3 clickable-div border rounded");
            let name = document.createElement("p");
            let namenode = document.createTextNode(jsonData[i].name);
            name.appendChild(namenode);
            let price = document.createElement("p");
            let pricenode = document.createTextNode(jsonData[i].price + " Ft");
            price.appendChild(pricenode);
            productDiv.appendChild(name);
            productDiv.appendChild(price);
            productDiv.onclick = function() {
                                            window.location = "http://localhost:8080/product.html?product=" + jsonData[i].productId;
                                       };
            gamesDiv.appendChild(productDiv);

        }
    }