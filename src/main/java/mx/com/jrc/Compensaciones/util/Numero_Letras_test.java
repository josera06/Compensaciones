package mx.com.jrc.Compensaciones.util;

public class Numero_Letras_test {
    public static void main(String[] args) {
        Numero_Letras numero_letras = new Numero_Letras();
        System.out.println(numero_letras.Convertir("1.32",true));
        System.out.println(TareasProgramacion.cantidadConLetra("1.32"));
    }
}
