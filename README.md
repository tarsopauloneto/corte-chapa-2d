# Corte de Chapa Bidimensional

Este projeto foi desenvolvido na disciplina de POO no 2º semestre. O objetivo era criar um algoritmo capaz de realizar **cortes em uma chapa bidimensional**, aplicando uma lógica de alocação de peças, onde cada peça representa um corte na chapa.

## 🔧 Funcionalidades
- Lê as dimensões da chapa e a lista de peças disponíveis.
- Posiciona as peças na chapa seguindo uma lógica sequencial de preenchimento e evitando sobreposição.
- Gera um arquivo `saida.txt` com as posições de cada peça alocada.
- Calcula a porcentagem de aproveitamento da chapa.
- Exibe uma **representação gráfica animada** do processo de alocação.

## 🧩 Lógica do Corte
- Ordena as peças pela área (maiores primeiro).
- Percorre a chapa linha a linha, procurando espaço para alocar cada peça.
- Se a peça couber, marca a área como ocupada.
- Repete até alocar todas as peças ou não haver mais espaço disponível.

## 📜 Arquivo de Entrada
O arquivo `entrada.txt` já está incluso no repositório, pronto para uso.
Ele define respectivamente:
1. As dimensões da chapa.
2. Quantos tipos diferentes de peças existem.
3. Para cada peça: tipo, largura, altura e quantidade.

## 🚀 Como Executar
1. Certifique-se de ter o **Java JDK** instalado.
2. Basta executar a `Main.java` para ver o resultado.
