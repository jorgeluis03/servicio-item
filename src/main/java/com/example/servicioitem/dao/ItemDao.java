package com.example.servicioitem.dao;

import com.example.servicioitem.entity.Item;
import com.example.servicioitem.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemDao {

    final ProductDao productDao;

    public ItemDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Item> listar(){
        List<Item> items = new ArrayList<>();

        for(Product p: productDao.listar()){
            Item item = new Item();
            item.setProduct(p);
            item.setCantidad(Math.round(Math.random() * 10));
            items.add(item);
        }
        return items;
    }
}
