var url = "http://localhost:8080/jogo-da-forca/services/palavras/listaPalavras";
var palavraSorteada;

// função para buscar na api a lista de palavras do banco
async function sortear(){
	await axios.get(url)
	.then(response => {
		console.log(response.data);
		
  		let index =  Math.floor(Math.random() * response.data.length);
		
        palavraSorteada = response.data[index].palavra;
        console.log(palavraSorteada)
        
        mostrarEspacos();
        return palavraSorteada;
	})
	.catch(error => {
		console.error(error);
	})
}
sortear();

function mostrarEspacos(){
	console.log(palavraSorteada + "aaaaaaaa")
}