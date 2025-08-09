import java.util.ArrayList;

public class Algorithm {

//    Data
    private Data data;

//    saida
    private ArrayList<String> output;

    public Algorithm() {
        this.data = new Data();
    }

    public void run() {
//        armazena as infos dos quadrados
        ArrayList<Piece> pieces = data.getQuadrados();

        Space space = new Space(data.getChapaX(), data.getChapaY());

        output = space.placePieces(pieces);

        data.escritaDados(output);


        System.out.println("\nÁrea preenchida: "+ space.calculaEficiencia() + "%\n");

        space.desenharChapa(output, data);

    }


}
