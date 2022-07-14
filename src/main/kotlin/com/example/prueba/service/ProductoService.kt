package com.example.prueba.service

import com.example.prueba.model.Producto
import com.example.prueba.repository.ProductoRepository
import org.jetbrains.annotations.TestOnly
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service


class ProductoService {
    @Autowired
    lateinit var productoRepository: ProductoRepository

    fun  list(): List<Producto>{
        return productoRepository.findAll()
    }

    fun save(producto: Producto):Producto{
        return productoRepository.save(producto)
    }

    fun update(producto: Producto): Producto {
        try {
            productoRepository.findById(producto.id) ?: throw Exception("El id ${producto.id} del producto no existe")

            return productoRepository.save(producto)
        }
        catch (ex:Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,ex.message,ex)
        }
    }

    fun updateName(producto: Producto): Producto{
        val response=productoRepository.findById(producto.id)
            ?:throw  Exception()
        response.descripcion=producto.descripcion
        return productoRepository.save(response)
    }
    fun delete (id:Long): Boolean{
        productoRepository.deleteById(id)
        return true
    }
    fun getById (id: Long?): Producto?{
        return productoRepository.findById(id)
    }

    fun multiplicacion (coeficiente: Int, digito: Int): Int{
        val response =coeficiente*digito
        if (response >= 10)
            return response - 9
        return response

    }

    //Uso de un if en la misma linea. Si i es par coeficiente es 2 caso contrario es 1
    fun sumaValores (nui: String): Long {
        var sum: Long=0
        for ( i in 0..8) {
            val coeficiente = if(i%2 ==0) 2 else 1
            sum  += multiplicacion(coeficiente,Integer.parseInt(nui[i].toString()) )
        }
        return sum
    }

    fun findDecenaSuperior (sum: Int): Int {
        val division: Int = sum / 10 //2
        val decenaSuperior: Int = (division + 1) * 10
        var response: Int = decenaSuperior - sum
        if (decenaSuperior == 10)
            response = 0
        return response

    }

    fun ultimoDigito(cedula: String): Int{

        val ultimo = cedula.last().toString()
        val response = Integer.parseInt(ultimo)

        return response



    }

    fun validarTotal(cedula: String):Boolean{

        val suma= sumaValores("0105881478")

        val resta = findDecenaSuperior(suma.toInt())

        val ultimo = ultimoDigito(cedula)

        return resta== ultimo




    }






}