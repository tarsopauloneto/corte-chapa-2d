import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
//    Chapa
    private int ChapaX;
    private int ChapaY;

//    quantidade de tipos, lista para armazenar as infos dos quadrados e das chapas
    private int quantTipos;
    private ArrayList<Piece> quadrados;
//    private ArrayList<EmptySpace> chapas;

//    quadrado
    private int QuadradoTipo;
    private int QuadradoX;
    private int QuadradoY;
    private int QuadradoQuant;

//    arquivo
    private String entrada;
    private String saida;


//    Construtor
    public Data() {
        this.quadrados = new ArrayList<Piece>();
        this.entrada = "entrada.txt";
        this.saida = "saida.txt";
        lerDados();
        sortPieces();
    }

    public int getChapaX() {
        return ChapaX;
    }

    public int getChapaY() {
        return ChapaY;
    }



    //    Método para a leitura de dados
    public void lerDados() {
        try {
            File file = new File(this.entrada);
            try (Scanner leitor = new Scanner(file)) {
				if (leitor.hasNextInt()) {
				    this.ChapaX = leitor.nextInt();
				    this.ChapaY = leitor.nextInt();
				} else {
   //            argumento inválido ou inadequado -> não tem linha para ler
				    throw new IllegalArgumentException("Erro: Dimensões da chapa ausentes no arquivo.");
				}

				if (leitor.hasNextInt()) {
				    this.quantTipos = leitor.nextInt();
				} else {
   //            argumento inválido ou inadequado -> não tem linha para ler
				    throw new IllegalArgumentException("Erro: Quantidade de tipos ausente no arquivo.");
				}

				quadrados.clear();
				for (int i = 0; i < this.quantTipos; i++) {
				    if (leitor.hasNextInt()) {
				        this.QuadradoTipo = leitor.nextInt();
				        this.QuadradoX = leitor.nextInt();
				        this.QuadradoY = leitor.nextInt();
				        this.QuadradoQuant = leitor.nextInt();
				        quadrados.add(new Piece(QuadradoX, QuadradoY, QuadradoTipo, QuadradoQuant));
				    } else {
   //            argumento inválido ou inadequado -> não tem linha para ler
				        throw new IllegalArgumentException("Erro: Dados incompletos para o tipo de quadrado " + (i + 1));
				    }
				}
				leitor.close();
			}

        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortPieces() {
        quadrados.sort((a, b) -> {return -1 * a.compareTo(b); } );
    }


    public ArrayList<Piece> getQuadrados() {
        return quadrados;
    }

    public void setQuadrados(ArrayList<Piece> quadrados) {
        this.quadrados = quadrados;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ChapaX=" + ChapaX +
                ", ChapaY=" + ChapaY +
                ", quantTipos=" + quantTipos +
                ", quadrados=" + quadrados +
                ", entrada='" + entrada + '\'' +
                ", saida='" + saida + '\'' +
                '}';
    }

    public void escritaDados(ArrayList<String> output) {
        File f = new File(saida);

        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < output.size(); i++) {
                fw.write(output.get(i) + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Erro de escrita");
        }
    }


}
