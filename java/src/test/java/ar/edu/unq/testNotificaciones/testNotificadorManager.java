package ar.edu.unq.testNotificaciones;
import ar.edu.unq.Estado;
import ar.edu.unq.notificadores.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class testNotificadorManager {
    private NotificadorManager notificadorManager;
    private ArrayList<Notificador> notificadoresList;
    private NotificadorEmail mailNotificador;
    private Fidelizacion fidelizacion;
    private EnCurso enCurso;

    @BeforeEach
    public void setUp() throws Exception {
        // DOC : mock
        mailNotificador = mock(NotificadorEmail.class);
        fidelizacion = mock(Fidelizacion.class);
        notificadoresList = new ArrayList<>();
        enCurso = mock(EnCurso.class);

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
        notificadorManager.actualizarEstado(enCurso, fidelizacion);
        verify(mailNotificador).actualizarEstado(enCurso , fidelizacion);
        verify(fidelizacion).actualizarEstado(enCurso, fidelizacion);
    }
}
