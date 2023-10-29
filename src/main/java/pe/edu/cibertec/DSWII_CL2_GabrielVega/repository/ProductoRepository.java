package pe.edu.cibertec.DSWII_CL2_GabrielVega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.model.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByNombreContainingIgnoreCase(String filtro);

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:filtro%")
    List<Producto> filtrarProductosPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM Producto WHERE nombre LIKE %:filtro%",
            nativeQuery = true)
    List<Producto> filtrarProductosPorNombreSQL(@Param("filtro") String filtro);

    @Query("SELECT p FROM Producto p WHERE p.cantidad > :minCantidad AND p.cantidad < :maxCantidad")
    List<Producto> filtrarProductosEnRango(@Param("minCantidad") Integer minCantidad, @Param("maxCantidad") Integer maxCantidad);

}
