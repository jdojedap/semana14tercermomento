package com.example.prueba

import com.example.prueba.service.ProductoService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest {
    @Autowired
    lateinit var productService: ProductoService
    @Test
    fun multiplicacionWhenLessThanTen (){
        val response=productService.multiplicacion(1,7)
        Assertions.assertEquals(7,response)
    }

    @Test
    fun multiplicacionWhenMajorThanTen (){
        val response=productService.multiplicacion(2,7)
        Assertions.assertEquals(5,response)
    }

    @Test
    fun validarSumaNui(){
        val response=productService.sumaValores("0301707030")
        Assertions.assertEquals(20,response)
    }

    @Test
    fun validarDecenaSuperiorCuandoNoEsCero(){
        val response=productService.findDecenaSuperior(13)
        Assertions.assertEquals(7,response)
    }

    @Test
    fun validarDecenaSuperiorCuandoEsCero(){
        val response=productService.findDecenaSuperior(20)
        Assertions.assertEquals(0,response)
    }

    @Test
    fun validarCedulaValida(){
        val response=productService.findDecenaSuperior(20)
        Assertions.assertEquals(0,response)
    }

    @Test
    fun validarCedulaNoValida(){
        val response=productService.findDecenaSuperior(20)
        Assertions.assertEquals(0,response)
    }

    @Test
    fun ultimoDigito(){
        val response=productService.ultimoDigito("0105881478")
        Assertions.assertEquals(8,response)
    }

    @Test
    fun cedulaValida(){
        val response=productService.validarTotal("0105881478")
        Assertions.assertEquals(true,response)
    }
    @Test
    fun cedulaInvalida(){
        val response=productService.validarTotal("0105881476")
        Assertions.assertEquals(false,response)
    }


}