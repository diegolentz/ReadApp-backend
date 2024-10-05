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
val RECOMMENDATIONS = listOf(
    Recomendacion(
        creador = users[0],
        librosRecomendados = mutableSetOf(books[0], books[3]),
        titulo = "Historias Cortas de Ciencia Ficción",
        contenido = "En un futuro donde las máquinas dominan el espacio exterior, un pequeño grupo de humanos lucha por sobrevivir. Esta colección reúne relatos que exploran la inteligencia artificial, los viajes intergalácticos y las fronteras de la ciencia moderna, presentando una visión fascinante del futuro.",
        publica = true,
        valoraciones = valorations
    ),
    Recomendacion(
        creador = users[1],
        librosRecomendados = mutableSetOf(books[4], books[1],books[5],books[8]),
        titulo = "La Filosofía del Zen",
        contenido = "Este libro profundiza en las enseñanzas milenarias del Zen, una rama del budismo que enfatiza la meditación y la comprensión intuitiva del ser. A través de metáforas y historias breves, el autor invita al lector a reflexionar sobre la naturaleza del ser, la vida y el equilibrio interno.",
        publica = true,
        valoraciones = valorations
    ),
    Recomendacion(
        creador = users[2],
        librosRecomendados = mutableSetOf(books[1], books[3], books[2]),
        titulo = "Viajes por Mundos Desconocidos",
        contenido = "Desde los océanos más profundos hasta los confines del espacio exterior, este libro lleva al lector a una travesía inolvidable. Explorando los límites de la realidad y la imaginación, el autor presenta una serie de aventuras que desafían las leyes de la física y de la mente humana.",
        publica = true,
        valoraciones = valorations
    ),
    Recomendacion(
        creador = users[3],
        librosRecomendados =  mutableSetOf(books[9] , books[10]),
        titulo = "El Arte de la Guerra Moderna",
        contenido = "Un análisis profundo de las estrategias militares contemporáneas, desde las guerras cibernéticas hasta las tácticas de guerrilla urbana. Este libro examina cómo las tecnologías avanzadas y los conflictos geopolíticos han transformado el campo de batalla moderno, ofreciendo una perspectiva única sobre el futuro de la guerra.",
        publica = true,
        valoraciones = valorations
    ),
    Recomendacion(
        creador = users[4],
        librosRecomendados =  mutableSetOf(books[10]),
        titulo = "El Universo en Expansión",
        contenido = "Este fascinante relato explora los últimos descubrimientos en cosmología, desde la teoría del Big Bang hasta la energía oscura. A través de una narrativa cautivadora, el autor nos lleva a comprender la inmensidad del universo y las incógnitas que aún desafían a los científicos de todo el mundo.",
        publica = true,
        valoraciones = mutableSetOf()
    ),
    Recomendacion(
        creador = users[5],
        librosRecomendados = mutableSetOf(books[11],books[5], books[7]),
        titulo = "Misterios de la Historia Antigua",
        contenido = "Un viaje a través de los enigmas más grandes de las civilizaciones antiguas: desde las pirámides de Egipto hasta los secretos de la Atlántida. Este libro examina los misterios no resueltos y teorías fascinantes sobre culturas que dejaron un legado duradero, pero aún ocultan verdades por descubrir.",
        publica = true,
        valoraciones = valorations
    )
)




