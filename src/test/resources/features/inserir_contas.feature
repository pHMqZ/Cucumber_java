#language: pt
@funcionais
Funcionalidade: Cadastro de contas

Como um usuário 
Gostaria de cadastrar contas
Para que eu possa distribuir meu dinheiro de uma forma mais organizada

Contexto:
	Dado que estou acessando a aplicação
	Quando informo o usuário "phillipmarq@hotmail.com"
	E a senha "Alceu1228!"
	E seleciono entrar
	Então visualizo a página inicial
	Quando seleciono Contas
	E seleciono Adicionar

Esquema do Cenário: Deve validar regras de cadastro
	Quando informar a conta "<conta>"
	E selecionar Salvar
	Então recebo a mensagem "<mensagem>"

Exemplos:
	| conta   | mensagem |
	| Conta 1 | Conta adicionada com sucesso! |
	|         | Informe o nome da conta |
	| Conta 1 | Já existe uma conta com esse nome! |