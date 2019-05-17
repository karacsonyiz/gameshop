window.addEventListener('load', setUserName());

function setUserName() {
    fetch("api/user")
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonData) {
            console.log(jsonData);
            setProfileBarForRegistered(jsonData);
        })
        .catch(error => setProfileBarForGuest(error));
    }

function setProfileBarForRegistered(jsonData) {
    let userImage = document.querySelector("#userimage");
    userImage.setAttribute("src",jsonData.name === "admin" ? "img/admin.jpg" : "img/" + jsonData.name + ".png");
    userImage.setAttribute("onerror",'this.src="img/jar_jar.jpg"');
    let userName = document.querySelector("#username");
    userName.innerHTML = jsonData.name;
    let logoutButton = document.querySelector("#logbutton");
    logoutButton.innerHTML = "Kijelentkezés";
    logoutButton.setAttribute("href","/logout");
    let adminButton = document.querySelector("#adminsettingslink");
    //if(jsonData.role === "ROLE_ADMIN") {adminButton.setAttribute("display","block")};
    jsonData.role === "ROLE_ADMIN" ? adminButton.setAttribute("style","display:block;color:black;") : adminButton.setAttribute("style","display:none");
}

function setProfileBarForGuest(error) {
    let userImage = document.querySelector("#userimage");
    userImage.setAttribute("src","img/guest.png");
    let userName = document.querySelector("#username");
    userName.innerHTML = "Guest"
    let userList = document.querySelector("#userlist");
    userList.setAttribute("style", "display:none;")
    let basketLink = document.querySelector("#basketlink");
    basketLink.setAttribute("style", "display:none;")
    let loginButton = document.querySelector("#logbutton");
    loginButton.innerHTML = "Bejelentkezés";
    loginButton.setAttribute("href","/login.html")
}
