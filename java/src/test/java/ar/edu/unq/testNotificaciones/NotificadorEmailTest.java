package ar.edu.unq.testNotificaciones;
import ar.edu.unq.cicloDeLaReserva.*;
import ar.edu.unq.notificadores.Fidelizacion;
import ar.edu.unq.notificadores.MailSender;
import ar.edu.unq.notificadores.NotificadorEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class NotificadorEmailTest {

    private NotificadorEmail notificadorEmail;
    private Confirmada estadoNuevo;
    private Finalizada estadoAnterior;
    private MockedStatic<MailSender> mailSenderMockedStatic;

    @BeforeEach
    public void setUp() {
        notificadorEmail = new NotificadorEmail();
        estadoNuevo = mock(Confirmada.class);
        estadoAnterior = mock(Finalizada.class);
        mailSenderMockedStatic = mockStatic(MailSender.class);
    }

    @Test
    public void testActualizarEstadoCuandoRequiereNotificacionEnviaMail() {
        when(estadoNuevo.requiereNotificacionEmail()).thenReturn(true);
        notificadorEmail.actualizarEstado(estadoAnterior, estadoNuevo);
        mailSenderMockedStatic.verify(() -> MailSender.enviarMail(
                eq("correoCliente@gmail.com"),
                eq("Actualizacion de reserva"),
                contains("Su reserva se actualizo al estado"),
                isNull()
        ), times(1));
    }

    @Test
    public void testActualizarEstadoCuandoNoRequiereNotificacionNoEnviaMail() {
        when(estadoNuevo.requiereNotificacionEmail()).thenReturn(false);
        notificadorEmail.actualizarEstado(estadoAnterior, estadoNuevo);
        mailSenderMockedStatic.verify(() -> MailSender.enviarMail(
                anyString(), anyString(), anyString(), any()
        ), times(0));
    }
}