package ar.edu.unq.testNotificaciones;
import ar.edu.unq.cicloDeLaReserva.*;
import ar.edu.unq.notificadores.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class testNotificadorManager {
    private NotificadorManager notificadorManager;
    private NotificadorEmail mailNotificador;
    private Fidelizacion fidelizacion;
    private Pendiente pendiente;
    private EnCurso enCurso;

    @BeforeEach
    public void setUp() throws Exception {
        // DOC : mock
        mailNotificador = mock(NotificadorEmail.class);
        pendiente = mock(Pendiente.class);
        ArrayList<Notificador> notificadoresList = new ArrayList<>();
        enCurso = mock(EnCurso.class);
        fidelizacion = mock(Fidelizacion.class);

        // SUT
        notificadorManager = new NotificadorManager(notificadoresList);
    }

    @Test
    public void testAgregarNotificador() {
        notificadorManager.agregarNotificador(mailNotificador);
        assertTrue(notificadorManager.getNotificadores().contains(mailNotificador));
    }

    @Test
    public void testQuitarNotificador() {
        notificadorManager.agregarNotificador(mailNotificador);
        assertTrue(notificadorManager.getNotificadores().contains(mailNotificador));
        notificadorManager.quitarNotificador(mailNotificador);
        assertTrue(notificadorManager.getNotificadores().isEmpty());
    }

    @Test
    public void testNotificarEstados(){
        notificadorManager.agregarNotificador(mailNotificador);
        notificadorManager.agregarNotificador(fidelizacion);
        notificadorManager.actualizarEstado(enCurso, pendiente);
        verify(mailNotificador).actualizarEstado(enCurso , pendiente);
        verify(fidelizacion).actualizarEstado(enCurso, pendiente);
    }
}
