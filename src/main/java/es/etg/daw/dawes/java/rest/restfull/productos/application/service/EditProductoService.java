package es.etg.daw.dawes.java.rest.restfull.productos.application.service;

import org.springframework.stereotype.Service;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.EditProductoCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.error.ProductoNotFoundException;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.ProductoRepository;

@Service
public class EditProductoService {

    private final ProductoRepository productoRepository;

    public Producto update(EditProductoCommand command) {
        Producto producto = productoRepository.findById(command.id())
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        producto.setNombre(command.nombre());
        producto.setPrecio(command.precio());

        return productoRepository.save(producto);
    }
}
