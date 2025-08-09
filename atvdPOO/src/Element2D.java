public abstract class Element2D {

    private int largura;
    private int altura;

    public Element2D(int largura, int altura) {

        this.largura = largura;
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int calculaArea() {
        return getLargura() * getAltura();
    }
}
