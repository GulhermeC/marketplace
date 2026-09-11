package marketplace.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import marketplace.controller.ProdutoController;
import marketplace.controller.UtilizadorController;
import marketplace.controller.EncomendaController;

import java.util.Set;

@ApplicationPath("/api")
public class AppPath extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(ProdutoController.class, UtilizadorController.class, EncomendaController.class);
    }
}