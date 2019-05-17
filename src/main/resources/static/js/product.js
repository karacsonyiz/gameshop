window.onload = function(){
        updateTable();
}
function handleErrors(response){
    if (!response.ok) {
        throw Error(response.statusText);
        }
    return response;
    }

function updateTable() {
    let productIdFromUrl = window.location.href.split('=')[1];
    let productToFetch = "api/products/" + productIdFromUrl ;
     fetch(productToFetch)
     .then(handleErrors)
     .then(function(request) {
        return request.json();
     })
     .then(function(jsonData) {
     console.table(jsonData);
     fillData(jsonData);
     })
     .catch(function(error){
        console.log("failed to retrieve data!",error);
        showProductNotFound();
     });
}

function fillData(jsonData){
    let nameHeader =  document.querySelector("#product-name");
    nameHeader.innerHTML = jsonData.name;
    let tbody = document.querySelector("#product-tbody");
    let tr = document.createElement("tr");
    let producerTd = document.createElement("td");
    producerTd.innerHTML = jsonData.producer;
    tr.appendChild(producerTd);
    let priceTd = document.createElement("td");
    priceTd.innerHTML = jsonData.price;
    tr.appendChild(priceTd);
    let quantityTd = document.createElement("td");
    quantityTd.innerHTML = jsonData.quantity;
    tr.appendChild(quantityTd);
    let basketTd = document.createElement("td");
    basketTd.setAttribute("id","baskettd");
    let basketButton = document.createElement("button");
    basketButton.type = "button";
    basketButton.className = "btn btn-link px0 py0";
    basketButton.addEventListener("click", function () {addingToBasket(jsonData.productId)});
    let icon = document.createElement("img");
    icon.setAttribute("id","carticon");
    icon.setAttribute("src","icon/glyph-iconset-master/svg/si-glyph-trolley-2.svg");
    basketButton.appendChild(icon);
    basketTd.appendChild(basketButton);
    tr.appendChild(basketTd);
    tbody.appendChild(tr);
}

function addingToBasket(productId) {
    let collapseWarning = document.querySelector("#collapsewarning");
    let collapseWarningMessage = document.querySelector("#collapsewarningmessage");
    fetch("api/basket/" + productId, {
        method: "POST"
        }).then(function(response) {
            return response.json();
        }).then(function(jsonData) {
        console.log(jsonData);
        console.log(jsonData.message);
        collapseWarningMessage.innerHTML = jsonData.message;
        let color = jsonData.validRequest ? "#5cb85c" : "#d9534f";
        console.log(color);
        //collapseWarning.setAttribute("style","background-color : " + color);
        collapseWarning.setAttribute("style","display:block ; background-color :" + color)
        document.querySelector("#closebutton").onclick = function(){collapseWarning.setAttribute("style","dislay:none");};
        //setInterval(function() { collapseWarning.setAttribute("style","display:none");}, 3000);
        });
        return false;
}

function showProductNotFound(){
    let errorHeader = document.querySelector("#product-name");
    errorHeader.innerHTML = "Sorry, product not found!";
    let tableToHide = document.querySelector("#producttable");
    tableToHide.style.display = "none";
    }
