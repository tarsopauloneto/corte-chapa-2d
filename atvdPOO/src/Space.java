import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Space extends Element2D{

    private boolean[][] occupied;

    private double areaOcupada;

    public double getAreaOcupada() {
        return areaOcupada;
    }

    public void setAreaOcupada(double areaOcupada) {
        this.areaOcupada = areaOcupada;
    }

    public Space(int largura, int altura) {
        super(largura, altura);
        areaOcupada = 0;
        this.occupied = new boolean[largura][altura];
    }

    public ArrayList<String> placePieces(ArrayList<Piece> pieces) {
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < pieces.size(); i++) {
//            variável para controlar a quantidade de peças de cada tipo
//            ela é zerada sempre que o for de cima começar a percorrer outra peça
            int pieceCount = 0;
//            for para percorrer o eixo x da chapa
//            X desse for -> próxima posição X que uma peça pode ser alocada.
//            se a posição X for <= a largura da chapa - largura da peça, da para alocar
            for (int y = 0; y <= getAltura() - pieces.get(i).getAltura(); y++) {

                for (int x = 0; x <= getLargura() - pieces.get(i).getLargura(); x++) {

//                    ver se da para alocar naquela posição
                    if (canPlacePiece(x, y, pieces.get(i)) && pieces.get(i).getQuantidade() > 0) {

//                       ocupar um espaço livre com as proporções daquela peça
                        fillSpace(x, y, pieces.get(i));
//                        adicionar ao ArrayList de saidas
                        output.add(pieces.get(i).getTipo() + " " + x + " " + y);
//                        incrementar o valor auxiliar que conta as peças
                        pieceCount++;
//                        incrementar o valor da área ocupada
                        setAreaOcupada((areaOcupada + pieces.get(i).calculaArea()));
//                        System.out.println(areaOcupada);

                        if (pieceCount == pieces.get(i).getQuantidade()) {
//                            System.out.println("Peça do tipo " + pieces.get(i).getTipo() + "se esgotou!");
                            pieces.get(i).setQuantidade(0);
//                            System.out.println(pieces.get(i).getQuantidade());
                            break;
                        }
                    }

                }
                if (pieceCount == pieces.get(i).getQuantidade()) {
//                    System.out.println(pieces.get(i).getQuantidade());
//                    System.out.println("Peça do tipo " + pieces.get(i).getTipo() + "se esgotou!");
                    pieces.get(i).setQuantidade(0);
                    break;
                }
            }
            if (pieces.get(i).getQuantidade() > 0) {
//                System.out.println("Entrou na condicional do último for");
                pieces.get(i).setQuantidade(pieces.get(i).getQuantidade() - pieceCount);
            }

        }
        tryFillRemainingSpaces(pieces, output);
        return output;
    }

    public boolean canPlacePiece(int startX, int startY, Piece piece) {

//        loop irá percorrer a partir das posições atuais de X e de Y.
//        ele vai checar se uma peça ocupa tal posição

//        uma peça 10x10, posicionada em (0, 0) vai ocupar todas as posições de 0 a 10 tanto no eixo X quanto no eixo Y
//        se tentar alocar outra peça em algum espaço entre {0 <= X >= 10 e 0 <= Y >= 10} esse método retornará false
        for (int x = startX; x < startX + piece.getLargura(); x++) {
            for (int y = startY; y < startY + piece.getAltura(); y++) {
                if (occupied[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillSpace(int startX, int startY, Piece piece) {
        for (int x = startX; x < startX + piece.getLargura(); x++) {
            for (int y = startY; y < startY + piece.getAltura(); y++) {
                occupied[x][y] = true;
            }
        }
    }

    public void tryFillRemainingSpaces(ArrayList<Piece> pieces, ArrayList<String> output) {
        for (int i = 0; i < pieces.size(); i++) {
            for (int x = 0; x < getLargura(); x++) {
                for (int y = 0; y < getAltura(); y++) {

                    if (x + pieces.get(i).getLargura() <= getLargura() && y + pieces.get(i).getAltura() <= getAltura() && canPlacePiece(x, y, pieces.get(i)) && pieces.get(i).getQuantidade() > 0) {

                        fillSpace(x, y, pieces.get(i));
                        output.add(pieces.get(i).getTipo() + " " + x + " " + y);
                        pieces.get(i).setQuantidade(pieces.get(i).getQuantidade() - 1);

                        if (pieces.get(i).getQuantidade() == 0) {
                            break;
                        }
                    }
                }
                if (pieces.get(i).getQuantidade() == 0) {
                    break;
                }
            }
        }
    }

    public double calculaEficiencia() {
        return areaOcupada / (getLargura() * getAltura()) * 100;
    }




    public void desenharChapa(ArrayList<String> output, Data dados) {
        // Cria um JFrame (janela principal) para exibir o layout da chapa.
        JFrame frame = new JFrame("Representação Gráfica da Chapa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lista para armazenar as peças já desenhadas.
        ArrayList<String> drawnPieces = new ArrayList<>();

        // Cria um JPanel para desenhar as peças e define seu layout.
        JPanel panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Obtém as dimensões da janela.
                int windowWidth = getWidth();
                int windowHeight = getHeight();

                // Calcula o fator de escala com base nas dimensões da janela e da chapa.
                double scaleX = (double) windowWidth / dados.getChapaX();
                double scaleY = (double) windowHeight / dados.getChapaY();

                // Desenha o contorno da chapa.
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, windowWidth - 1, windowHeight - 1);

                // Desenha todas as peças que já foram desenhadas.
                for (String item : drawnPieces) {
                    desenharPeca(g, item, dados, scaleX, scaleY, windowHeight);
                }
            }

            private void desenharPeca(Graphics g, String item, Data dados, double scaleX, double scaleY, int windowHeight) {
                // Divide as informações de cada linha: tipo, posição X e posição Y.
                String[] data = item.split(" ");
                int tipo = Integer.parseInt(data[0]);
                int posX = Integer.parseInt(data[1]);
                int posY = Integer.parseInt(data[2]);

                // Define a cor baseada no tipo da peça.
                switch (tipo) {
                    case 1 -> g.setColor(Color.RED);
                    case 2 -> g.setColor(Color.BLUE);
                    case 3 -> g.setColor(Color.GREEN);
                    case 4 -> g.setColor(Color.MAGENTA);
                    case 5 -> g.setColor(Color.YELLOW);
                    case 6 -> g.setColor(Color.ORANGE);
                    case 7 -> g.setColor(Color.PINK);
                    case 8 -> g.setColor(Color.GRAY);
                    default -> g.setColor(Color.GRAY); // Cor padrão para tipos desconhecidos.
                }

                // Recupera as dimensões da peça para desenhá-la.
                Piece peca = dados.getQuadrados().stream()
                        .filter(p -> p.getTipo() == tipo)
                        .findFirst()
                        .orElse(null);

                if (peca != null) {
                    // Ajusta as coordenadas e dimensões das peças de acordo com a escala.
                    int x = (int) (posX * scaleX);
                    int y = (int) (posY * scaleY);

                    int largura = (int) (peca.getLargura() * scaleX);
                    int altura = (int) (peca.getAltura() * scaleY);

                    // Ajusta a posição vertical para refletir a origem no canto inferior esquerdo.
                    int adjustedY = windowHeight - y - altura;

                    // Desenha a peça na posição e dimensões especificadas.
                    g.fillRect(x, adjustedY, largura, altura);

                    // Desenha as arestas da peça (contorno).
                    g.setColor(Color.BLACK); // Cor da aresta para preto.
                    g.drawRect(x, adjustedY, largura, altura);
                }
            }
        };

        // Configura o painel e adiciona ao frame.
        panel.setPreferredSize(new Dimension(dados.getChapaX(), dados.getChapaY()));
        frame.add(panel);

        // Define a janela para ser exibida em tela cheia.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Exibe em tela cheia.
        frame.setUndecorated(false); // Define como decorado, para manter a barra de título (opcional).

        // Cria um Timer para animar o desenho das peças.
        Timer timer = new Timer(350, e -> {
            if (drawnPieces.size() < output.size()) {
                // Adiciona a próxima peça à lista de peças desenhadas.
                drawnPieces.add(output.get(drawnPieces.size()));
                panel.repaint(); // Atualiza o painel para redesenhar com a nova peça.
            } else {
                // Para o Timer quando todas as peças forem desenhadas.
                ((Timer) e.getSource()).stop();
            }
        });

        // Exibe a janela e inicia a animação.
        frame.setVisible(true); // Torna a janela visível.
        timer.start(); // Inicia o Timer para começar a animação.
    }



}
