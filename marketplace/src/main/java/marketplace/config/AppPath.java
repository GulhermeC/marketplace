package marketplace.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import marketplace.controller.ProdutoController;

import java.util.Set;

@ApplicationPath("/api")
public class AppPath extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(ProdutoController.class);
    }
}