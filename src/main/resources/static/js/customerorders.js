window.onload = function() {
    updateTable();
};

function updateTable() {
    fetch("api/myorders")
        .then(function(request) {
            return request.json();
        })
        .then(function(jsonData) {
        fillTable(jsonData);
        });
}

function fillTable(jsonData) {
    let tbody = document.querySelector("#product-tbody");
        console.log(jsonData);
        for(let i in jsonData) {
            let tr = document.createElement("tr");
            let idTd = document.createElement("td");
            idTd.innerHTML = jsonData[i].id
            tr.appendChild(idTd);
            let dateTd = document.createElement("td");
            let formattedDate = jsonData[i].date.replace("T", " ");
            dateTd.innerHTML = formattedDate;
            tr.appendChild(dateTd);
            let showItemTd = document.createElement("td");
            let showItemButton = document.createElement("a");
            showItemButton.setAttribute("data-toggle","modal");
            showItemButton.setAttribute("data-target","#showOrderItem");
            showItemButton.href = "#";
            showItemButton.innerHTML = "Megnézem";
            showItemButton.addEventListener("click", function () {ShowOrderItem(this.parentElement.parentElement)});
            showItemTd.appendChild(showItemButton);
            tr.appendChild(showItemTd);
            tbody.appendChild(tr);
        }
}

function ShowOrderItem(data){
    let orderId = data.firstChild.innerHTML
    let modalbody = document.querySelector("#modalbody");
    fetch("api/myorderitems/" + orderId)
        .then(function(request) {
            return request.json();
        })
        .then(function(jsonData) {
            fillModal(jsonData);
        });
}

function fillModal(jsonData) {
    let modalTitle = document.querySelector("#modaltitle");
    modalTitle.innerHTML = "Rendelés azonosító : " + jsonData[0].orderId;
    let modalNameDiv = document.querySelector("#modalNameDiv");
    let modalPriceDiv = document.querySelector("#modalPriceDiv");
    modalNameDiv.innerHTML = "";
    modalPriceDiv.innerHTML = "";
    let modalfootertext = document.querySelector("#modalfootertext");
    let modalfooterPrice = document.querySelector("#modalfooterprice");
    let price = 0;
        for(i in jsonData) {
            price += jsonData[i].productPrice;
            modalNameDiv.innerHTML = modalNameDiv.innerHTML + jsonData[i].productName + "<br>";
            modalPriceDiv.innerHTML = modalPriceDiv.innerHTML + jsonData[i].productPrice + " Ft" + "<br>";
            }
        modalfootertext.innerHTML = "Termékek ára összesen :";
        modalfooterPrice.innerHTML = price + " Ft";
    }
