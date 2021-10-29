/** $(document).ready(function(){
	//on ready
});

async function registrarUsuario(){
	let datos = {};
	datos.nombre = document.getElementById('txtName').value;
	datos.edad = document.getElementById('numAge').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;
	const request = await fetch('api/usuarios',{
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	});
}*/