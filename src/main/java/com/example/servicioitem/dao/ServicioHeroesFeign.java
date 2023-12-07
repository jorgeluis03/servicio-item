package com.example.servicioitem.dao;

import com.example.servicioitem.entity.Heroe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "servicio-heroes")
public interface ServicioHeroesFeign {

    @GetMapping("/hola")
    Heroe listarHeroe();
}
