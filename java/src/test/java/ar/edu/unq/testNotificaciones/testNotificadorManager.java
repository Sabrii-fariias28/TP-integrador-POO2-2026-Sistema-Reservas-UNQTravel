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
    private Notificador iNotificador;
    private NotificadorEmail mailNotificador;
    private Fidelizacion fidelizacion;
    private GeneradorVoucher generadorVoucher;
    private Estado estado;

    @BeforeEach
    public void setUp() throws Exception {
        // DOC : mock
        iNotificador = mock(Notificador.class);
        mailNotificador = mock(NotificadorEmail.class);
        fidelizacion = mock(Fidelizacion.class);
        generadorVoucher = mock(GeneradorVoucher.class);
        notificadoresList = new ArrayList<>();
        estado = new Estado();

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
        notificadorManager.quitarNotificador(mailNotificador);
        assertTrue(notificadorManager.getNotificadores().isEmpty());
    }

    @Test
    public void testNotificarEstados(){
        notificadorManager.agregarNotificador(mailNotificador);
        notificadorManager.agregarNotificador(fidelizacion);
        notificadorManager.actualizarEstado(estado, estado);
        verify(mailNotificador).actualizarEstado(estado , estado);
        verify(fidelizacion).actualizarEstado(estado, estado);
    }
}
