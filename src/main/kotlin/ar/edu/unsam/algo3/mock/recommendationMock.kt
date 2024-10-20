package ar.edu.unsam.algo3.mock


import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion

import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.services.ServiceAutor
import ar.edu.unsam.algo3.services.ServiceLibros
import ar.edu.unsam.algo3.services.ServiceUser
import java.time.LocalDate
import java.util.*



val books = ServiceLibros.get()
val authors = ServiceAutor.get()


val users = ServiceUser.getAll()


private val valorations = mutableSetOf(
    Valoracion(5,"Muy buena recomendacion para aquellos que buscan enntretenerse un rato", users[0] ),
    Valoracion(1,"Una perdida de tiempo no la recomiendo, no derrochen papel asi", users[3]),
    Valoracion(3,"Mas o menos safa",users[5] ),
)
