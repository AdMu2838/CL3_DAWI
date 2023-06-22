package com.cibertec.edu.controllers;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.edu.models.Producto;
import com.cibertec.edu.services.ProductoService;

@Controller
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/login")
    public String mostrarPaginaLogin() {
        return "login"; // Nombre de la vista HTML para la página de login
    }

    @GetMapping("/registro")
    public String mostrarPaginaRegistro(Model model) {
        model.addAttribute("producto", new Producto());
        return "registro"; // Nombre de la vista HTML para la página de registro de productos
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        producto.setFechaRegistro(new Date()); // Asignar la fecha actual al producto
        productoService.guardarProducto(producto);
        return "redirect:/registro";
    }


    /*@GetMapping("/generar-constancia/{id}")
    public ResponseEntity<byte[]> generarConstancia(@PathVariable("id") Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        byte[] contenidoPDF = productoService.generarReportePDF(producto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").build());

        return new ResponseEntity<>(contenidoPDF, headers, HttpStatus.OK);
    }*/
}
