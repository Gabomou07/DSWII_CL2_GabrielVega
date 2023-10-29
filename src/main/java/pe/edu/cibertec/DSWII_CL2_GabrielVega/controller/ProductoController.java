package pe.edu.cibertec.DSWII_CL2_GabrielVega.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.model.Producto;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {

    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productoList = new ArrayList<>();
        productoService.listarProductos()
                .forEach(productoList::add);
        if(productoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerCategoria(
            @PathVariable("id") Integer id){
        Producto producto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> registrarProducto(
            @RequestBody Producto producto
    ){
        return new ResponseEntity<>(
                productoService.guardar(producto), HttpStatus.CREATED
        );
    }

//    @GetMapping("/productos-en-rango")
//    public List<Producto> obtenerProductosEnRango(
//            @RequestParam("minCantidad") Integer minCantidad,
//            @RequestParam("maxCantidad") Integer maxCantidad
//    ) {
//        return productoService.filtrarProductosEnRango(minCantidad, maxCantidad);
//    }
//
//    @GetMapping("/productos-por-anio-vencimiento")
//    public List<Producto> obtenerProductosPorAnioVencimiento(
//            @RequestParam("anio") Integer anio
//    ) {
//        return productoService.filtrarProductosPorAnioVencimiento(anio);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProductos(
            @PathVariable("id") Integer id,
            @RequestBody Producto producto
    ){
        Producto oldProducto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Producto con el Id Nro. "+
                        id + " no existe."));
        oldProducto.setNombre(producto.getNombre());
        oldProducto.setDescripcion(producto.getDescripcion());
        oldProducto.setCantidad(producto.getCantidad());
        oldProducto.setFechavencimiento(producto.getFechavencimiento());
        return new ResponseEntity<>(
                productoService.guardar(oldProducto), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable("id") Integer id) {
        try {
            productoService.eliminarProductoId(id);
            return new
                    ResponseEntity<>("Producto : " + id, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return new
                    ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new
                    ResponseEntity<>("Error producto ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
