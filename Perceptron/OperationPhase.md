# Algoritmo de operação do neurônio (perceptron)

Obter a amostra a ser classificada X  
Obter os valores de W da rede treinada  
Calcular o V = sum(xi*wi) com i de 0 a N  
Calcular Y = funçãoDeAtivação(V)  
//usando uma função de ativação heavyside  
se Y = 0 então  

	escreva("A amostra", X, " é da classe A")  
senão  

	escreva("A amostra", X, " é da classe B")  
fimse  

