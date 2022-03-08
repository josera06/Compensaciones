package mx.com.jrc.Compensaciones.util;

public class CorreoElectronicoTest {
    public static void main(String[] args) {
        CorreoElectronico correo = new CorreoElectronico();
        correo.enviarConGMail("jramon208@gmail.com","HolaMundo","Prueba de javaMail");
        System.out.println("Correo enviado correctamente");
    }
}
