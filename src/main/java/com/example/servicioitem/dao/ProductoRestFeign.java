package com.example.servicioitem.dao;

import com.example.servicioitem.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="servicio-producto")
public interface ProductoRestFeign {

    @GetMapping("/productos")
    List<Product> listar();

    @GetMapping("/productos/{id}")
    Product buscarPorId(@PathVariable int id);
}
