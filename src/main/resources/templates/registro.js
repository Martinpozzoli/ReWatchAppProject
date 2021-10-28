$(document).ready(function(){
    //on ready
})

async function registrarUsuario(){
    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'appication/json',
            'Content-Type': 'application/json'
        }
    })
}