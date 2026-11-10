package ar.edu.unq.testNotificaciones;

import ar.edu.unq.Estado;
import ar.edu.unq.notificadores.MailSender;
import ar.edu.unq.notificadores.NotificadorEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class NotificadorEmailTest {

    private NotificadorEmail notificadorEmail;
    private Confirmada estadoNuevo;
    private Fidelizacion estadoAnterior;
    private MailSender iMailSender;

    @BeforeEach
    public void setUp() {
        notificadorEmail = new NotificadorEmail();
        estadoNuevo = mock(Confirmada.class);
        estadoAnterior = mock(Fidelizacion.class);
        iMailSender = mock(MailSender.class);
    }

    @Test
    public void testActualizarEstadoCuandoRequiereNotificacionEnviaMail() {
        when(estadoNuevo.requiereNotificacionEmail()).thenReturn(true);
        notificadorEmail.actualizarEstado(estadoAnterior, estadoNuevo);
        verify(iMailSender.enviarMail(
                "correoCliente@gmail.com",
                "Actualizacion de reserva",
                "Su reserva se actualizo al estado" + estadoNuevo,
                null), times(1));
    }

    @Test
    public void testActualizarEstadoCuandoNoRequiereNotificacionNoEnviaMail() {
        when(estadoNuevo.requiereNotificacionEmail()).thenReturn(false);
        notificadorEmail.actualizarEstado(estadoAnterior, estadoNuevo);
        verify(iMailSender.enviarMail(
                "correoCliente@gmail.com",
                "Actualizacion de reserva",
                "Su reserva se actualizo al estado" + estadoNuevo,
                null), times(0));
    }
}