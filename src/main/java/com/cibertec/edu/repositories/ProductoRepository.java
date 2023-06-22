package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cibertec.edu.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
