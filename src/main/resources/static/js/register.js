window.onload = function() {
    let form = document.querySelector(".form-detail");
    form.onsubmit = handleSubmit;
}

function handleSubmit() {
    let name = document.querySelector("#user-name").value;
    let email = document.querySelector("#user-email").value;
    let password = document.querySelector("#user-password").value;

    let user = {
                   "name": name,
                   "password": password,
                   "email": email
               }
     fetch("api/createuser", {
                 method: "POST",
                 headers: {
                     "Content-Type": "application/json; charset=utf-8"
                         },
                 body: JSON.stringify(user)
        }).then(function(response) {
                   return response.json();
               })
               .then(function(jsonData) {
                   console.log(jsonData);
               })
               .catch(error => console.log(error));
           return false;
}
