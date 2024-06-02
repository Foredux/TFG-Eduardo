package com.example.ejercicioApartado2punto7.Service;

import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.users.Dto.JwtUserResponse;
import com.example.ejercicioApartado2punto7.users.model.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(Socio user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registro exitoso!");
        mailMessage.setText("Para confirmar tu correo electrónico, por favor haz clic aquí : "
                +"http://localhost:9000/confirm-account/"+user.getEmail());

        mailSender.send(mailMessage);
    }

    public void registroAdmin(Administrador user, String contra) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registro exitoso!");
        mailMessage.setText("Para iniciar sesion como admin visita esta pagina. Esta es tu contraseña:"+contra+" : "
                +"http://localhost:9000/auth/login/admin");

        mailSender.send(mailMessage);
    }
}
