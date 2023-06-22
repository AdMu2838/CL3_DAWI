package com.cibertec.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.edu.models.Producto;
import com.cibertec.edu.repositories.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    /*public byte[] generarReportePDF(Producto producto) {
        // Lógica para generar el reporte PDF utilizando una biblioteca como iText o Apache PDFBox
        // Devuelve el contenido del PDF como un arreglo de bytes
    }*/
}