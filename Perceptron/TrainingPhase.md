# Algoritmo de treinamento do neurônio (perceptron)  

Obter o conjunto de amostras X (cada amostra possui as características necessárias)  
Associar a saída desejada D para cada amostra  
Inicializar os pesos W com valores aleatórios (valores pequeno)  
Definir a taxa de aprendizagem n (valor pequeno)  
ERRO = verdadeiro  

enquanto(ERRO)

	ERRO = falso  
	para todos os pares de treinamento X e D faca  
    calcular o V = sum(xi*wi) com i de 0 a N  
    Y = funçãoDeAtivação(V)  
    se Y != D então  
      ERRO = verdadeiro  
      //calcula o novo W  
      para i=0 até quantidade de características faça  
        W[i] = W[i] + n * (D - Y) * X[i]  
      fimpara  
    fimse  
  fimpara  
fimenquanto  
