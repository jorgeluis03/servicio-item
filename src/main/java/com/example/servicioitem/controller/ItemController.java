package com.example.servicioitem.controller;

import com.example.servicioitem.dao.ItemDao;
import com.example.servicioitem.dao.ProductDao;
import com.example.servicioitem.dao.ProductoRestFeign;
import com.example.servicioitem.dao.ServicioHeroesFeign;
import com.example.servicioitem.entity.Heroe;
import com.example.servicioitem.entity.Item;
import com.example.servicioitem.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class ItemController {

    final ProductDao productDao;
    final ItemDao  itemDao;
    final ProductoRestFeign productoRestFeign;
    final ServicioHeroesFeign servicioHeroesFeign;
    final CircuitBreakerFactory circuitBreakerFactory;

    public ItemController(ProductDao productDao, ItemDao itemDao, ProductoRestFeign productoRestFeign, ServicioHeroesFeign servicioHeroesFeign, CircuitBreakerFactory circuitBreakerFactory) {
        this.productDao = productDao;
        this.itemDao = itemDao;
        this.productoRestFeign = productoRestFeign;
        this.servicioHeroesFeign = servicioHeroesFeign;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("/items")
    public List<Item> listar(){
        List<Item> items = new ArrayList<>();

        for(Product p: productDao.listar()){
            Item item = new Item();
            item.setProduct(p);
            item.setCantidad(generarRandom0_9());
            items.add(item);
        }

        return items;
        //return itemDao.listar();
    }

    @GetMapping("/itemsF")
    public List<Item> listarFeign(){
        List<Item> items = new ArrayList<>();

        for(Product p: productoRestFeign.listar()){
            Item item = new Item();
            item.setProduct(p);
            item.setCantidad(generarRandom0_9());
            items.add(item);
        }

        return items;
    }

    @GetMapping("/items/{id}")
    public Item obtenerPorId(@PathVariable("id") int id){

        CircuitBreaker breaker = circuitBreakerFactory.create("items");

        return breaker.run(() -> {
            Product product = productoRestFeign.buscarPorId(id);

            Item item = new Item();
            item.setProduct(product);
            item.setCantidad(generarRandom0_9());
            return item;
        }, throwable -> {
            Item item = new Item();
            Product product = new Product();
            product.setProductName("Producto alternativo");
            item.setProduct(product);
            item.setCantidad(0);
            return item;
        });
    }

    @GetMapping("/sh")
    public Heroe listarHeroe(){
        return servicioHeroesFeign.listarHeroe();
    }

    public long generarRandom0_9(){
        return Math.round(Math.random() * 10);
    }
}
