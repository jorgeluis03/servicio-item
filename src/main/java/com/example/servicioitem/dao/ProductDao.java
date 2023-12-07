package com.example.servicioitem.dao;

import com.example.servicioitem.entity.Product;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {

    @Autowired
    RestTemplate clienteRest;

    public List<Product> listar(){
        Product[] productArr = clienteRest.getForObject("http://servicio-producto/productos", Product[].class);

        return Arrays.asList(productArr);
    }

    public Product obtenerPorId(int id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://servicio-producto/productos/" + id;
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }
}
