package com.cibertec.edu.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cibertec.edu.repositories.ProductoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.cibertec.edu.models.*;
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

    public InputStream generarReportePDF(Producto producto) throws JRException {
        try {
            ProductoReport productoReport = new ProductoReport(producto.getNombre(), producto.getDescripcion(), producto.getFechaRegistro());

            String reportFilePath = "jasper/constancia.jrxml";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(reportFilePath);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(productoReport));

            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, null, dataSource);

            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return new ByteArrayInputStream(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Error al generar el informe de producto", e);
        }
    }
    
    public Producto obtenerUltimoProducto() {
     
        Optional<Producto> ultimoProducto = productoRepository.findFirstByOrderByIdDesc();

        return ultimoProducto.orElse(null);
    }
}