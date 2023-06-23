package com.cibertec.edu.controllers;
import org.apache.commons.io.IOUtils;
import java.time.LocalDate;
import org.springframework.http.MediaType;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.edu.models.*;
import com.cibertec.edu.services.ProductoService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.http.HttpHeaders;
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


    @GetMapping("/generar-constancia-ultimo")
    public ResponseEntity<byte[]> generarConstanciaUltimoProducto() {
        try {
            Producto ultimoProducto = productoService.obtenerUltimoProducto();

            if (ultimoProducto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            InputStream pdfStream = productoService.generarReportePDF(ultimoProducto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").build());

            byte[] pdfBytes = IOUtils.toByteArray(pdfStream);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
