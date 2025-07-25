package ar.edu.unsam.algo2.readapp.repositorios

import excepciones.NotFoundException
import org.springframework.stereotype.Component


//Hace referencia a los objetos los cuales van a ser instancias en cada repositorio
//Libro, Usuario, Recomendacion,  Usuario,
//Esto es para no tener un repositorio de, e.g, Valoraciones

interface AvaliableInstance {
    var id: Int

    fun cumpleCriterioBusqueda(texto: String): Boolean

}

@Component
class Repositorio<T : AvaliableInstance> {
    var objetosEnMemoria: MutableSet<T> = mutableSetOf()



    fun create(objeto: T): Boolean {
        this.asignarID(objeto)
        return objetosEnMemoria.add(objeto) //SI es Set devuelve true o false, si es list siempre devuelve true
    }

    //void
    fun delete(objeto: T) {
        validarExistencia(objeto.id)
        objetosEnMemoria.removeIf { objeto.id == it.id }//
    }

    private fun validarExistencia(id: Int) {
        if (!existeElemento(id)) {
            throw NotFoundException("No existe objeto con ID: $id")
        }
    }


    private fun existeElemento(id: Int) = objetosEnMemoria.any { it.id == id }

    //void
//terminar implementacion
    fun update(objeto: T) {
        delete(objeto)
        objetosEnMemoria.add(objeto)
    }

    fun getByID(objectID: Int): T {
        validarExistencia(objectID)
        return objetosEnMemoria.first { it.id == objectID } //devuelve elemento o null
    }

    fun search(texto: String): List<T> = objetosEnMemoria.filter { it.cumpleCriterioBusqueda(texto.lowercase()) }

    private fun asignarID(objeto: T) {
        objeto.id = objetosEnMemoria.size + 1
    }

    fun getAll(): Set<T> = objetosEnMemoria

    fun clearAll() = objetosEnMemoria.clear()

}
