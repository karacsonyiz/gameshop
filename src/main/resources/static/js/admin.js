document.querySelector("#submitproduct").addEventListener("click", handleSubmit);

function handleSubmit() {
        let name = document.querySelector("#productname").value;
        let producer = document.querySelector("#productproducer").value;
        let price = document.querySelector("#productprice").value;
        let quantity = document.querySelector("#productquantity").value;
        let product = {"name": name,
                   "producer": producer,
                   "price": price,
                   "quantity": quantity
                   }
        console.log(product);

        fetch("api/products", {
                         method: "POST",
                         headers: {
                             "Content-Type": "application/json; charset=utf-8"
                                 },
                         body: JSON.stringify(product)
                }).then(function(response) {
                           return response.json();
                       })
                       .then(function(jsonData) {
                           handleActions(jsonData);
                           console.log(jsonData)
                       })
                       .catch(error => console.log(error));
                   return false;
}

function handleActions(jsonData){
        let warningtext = document.querySelector("#warningtext");
        jsonData.validRequest ? warningtext.setAttribute("class","p-1 bg-success text-white rounded") : warningtext.setAttribute("class","p-1 bg-danger text-white rounded");
        warningtext.innerHTML = jsonData.message;
        document.querySelector("#warningdiv").style = "display : block;";
}
