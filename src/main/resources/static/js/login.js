$(document).ready(function(){
	//on ready
});

async function loginUsuario(){
	let datos = {};
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;
	
	const request = await fetch('api/login',{
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	});
	
	const respuesta = await request.text();
	if(respuesta != 'FAIL'){
		localStorage.token = respuesta;
		localStorage.email = datos.email;
		alert("Credenciales correctas");
		window.location.href = 'bienvenido.html'
	}else{
		alert("Credenciales incorrectas, intenta nuevamente");
	}
}