import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("rpruiz33@gmail.com"); // Configura correctamente el remitente
            message.setTo("planexia.sa@gmail.com"); // Cambia esto si necesitas múltiples destinatarios
            message.setSubject("Consulta desde el formulario de contacto");
            message.setText("Nombre: " + emailRequest.getName() + "\n"
                    + "Email: " + emailRequest.getEmail() + "\n"
                    + "Mensaje: " + emailRequest.getMessage());
            mailSender.send(message);
            return ResponseEntity.ok("Correo enviado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar correo: " + e.getMessage());
        }
    }
}
