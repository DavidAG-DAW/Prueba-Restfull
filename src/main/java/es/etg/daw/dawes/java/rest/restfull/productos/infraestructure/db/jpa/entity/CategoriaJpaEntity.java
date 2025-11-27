package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "CATEGORIAS")
public class CategoriaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Evita recursión infinita
    private List<ProductoJpaEntity> productos = new ArrayList<>();

    // --- Constructores ---
    public CategoriaJpaEntity() {}

    public CategoriaJpaEntity(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos auxiliares para sincronizar productos y categorías
    public void addProducto(ProductoJpaEntity producto) {
        this.productos.add(producto);
        producto.setCategoria(this);
    }

    public void removeProducto(ProductoJpaEntity producto) {
        this.productos.remove(producto);
        producto.setCategoria(null);
    }
}
