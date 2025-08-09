public class Piece extends Element2D {

    private int tipo;
    private int quantidade;

    public Piece(int largura, int altura, int tipo, int quantidade) {

        super(largura, altura);
        this.tipo = tipo;
        this.quantidade = quantidade;
    }
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void mostrarInfos() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Largura: " + getLargura());
        System.out.println("Altura: " + getAltura());
        System.out.println("Área: " + getArea());
    }

    public int compareTo(Piece piece) {
        if (getLargura() * getAltura() < piece.getLargura() * piece.getAltura()) {
            return -1;
        } else if (getLargura() * getAltura() > piece.getLargura() * piece.getAltura()) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getArea() {
        return getLargura() * getAltura();
    }



}