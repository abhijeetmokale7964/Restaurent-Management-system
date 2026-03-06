document.getElementById("loginForm").addEventListener("submit", function(e){

e.preventDefault();

let email = document.getElementById("email").value;
let password = document.getElementById("password").value;

fetch("/api/auth/login",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
email:email,
password:password
})
})
.then(res => res.json())
.then(data => {

if(!data.token){
alert("Login Failed");
return;
}

localStorage.setItem("token",data.token);
localStorage.setItem("role",data.role);
localStorage.setItem("email",email);

if(data.role === "ADMIN"){
window.location.href="/admin/dashboard";
}else{
window.location.href="/staff/dashboard";
}

})
.catch(err=>{
alert("Login Error");
console.log(err);
});

});