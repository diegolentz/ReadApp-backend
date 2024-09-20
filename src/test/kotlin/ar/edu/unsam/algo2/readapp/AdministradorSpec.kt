package ar.edu.unsam.algo2.readapp

import LibroBuilder
import ar.edu.unsam.algo2.readapp.administrador.Administrador
import ar.edu.unsam.algo2.readapp.administrador.ProcesoActualizadorLibros
import ar.edu.unsam.algo2.readapp.administrador.ProcesoAgregarAutores
import ar.edu.unsam.algo2.readapp.builders.ProcesosBuilder
import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import ar.edu.unsam.algo2.readapp.builders.AutorBuilder
import ar.edu.unsam.algo2.readapp.centrosDeLectura.CentroDeLectura
import ar.edu.unsam.algo2.readapp.centrosDeLectura.Particular
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import stubs.MailSenderStub
import stubs.ServiceLibroStub
import java.time.LocalDate

class AdministradorSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest //ojo sin esto. Algunos estados quedan guardados SIN isolationMode
    describe("Dado un administrador de sistema") {
        var stubMailSender: MailSenderStub = MailSenderStub()
        var sysAdmin = Administrador(stubMailSender)

        val libro1 = LibroBuilder().build()
        val libro2 = LibroBuilder().build()
        val repositorioUsuario = Repositorio<Usuario>()
        val usuarioA = UsuarioBuilder().build()
        val usuarioB = UsuarioBuilder().build()

        val particularExpirado = Particular(1)
        val particularNoExpirado = Particular(2000)
        particularNoExpirado.fechasDeLectura.add(LocalDate.now().plusDays(25))
        val repoCentroLecura = Repositorio<CentroDeLectura>()

        var repositorioAutores = Repositorio<Autor>()
        var autor1: Autor = AutorBuilder().lenguaNativa(Lenguaje.RUSO).build()
        var autor2: Autor = AutorBuilder().lenguaNativa(Lenguaje.ESPANIOL).build()
        var autor3: Autor = AutorBuilder().lenguaNativa(Lenguaje.JAPONES).build()
        var autor4: Autor = AutorBuilder().lenguaNativa(Lenguaje.ARABE).build()
        var autor5: Autor = AutorBuilder().lenguaNativa(Lenguaje.FRANCES).build()
        var autores: List<Autor> = listOf(autor1, autor2, autor3)
        var autores2: List<Autor> = listOf(autor4, autor5)

        
        describe("Puede crear, configurar y ejecutar procesos cuando sea necesario") {

            it("CREAR") {
                sysAdmin.listaProcesos.isEmpty() shouldBe (true)
                sysAdmin.agregarProcesos(
                    ProcesosBuilder()
                        .borrarUsuariosInactivos(repositorioUsuario)
                        .build()
                )
                sysAdmin.listaProcesos.size shouldBeEqual 1
            }

            it("CONFIGURAR") {

            }

            it("EJECUTAR") {

            }
        }

        describe("Borrar usuarios inactivos") {
            repositorioUsuario.apply {
                create(usuarioA)
                create(usuarioB)
            }
            sysAdmin.agregarProcesos(
                ProcesosBuilder()
                    .borrarUsuariosInactivos(repositorioUsuario)
                    .build()
            )
            repositorioUsuario.objetosEnMemoria.shouldContain(usuarioA)
            repositorioUsuario.objetosEnMemoria.shouldContain(usuarioB)
            it("Usuario A y B son inactivos, se limpia el repo") {
                sysAdmin.ejecutarListaProcesos()
                repositorioUsuario.objetosEnMemoria.shouldBeEmpty()
            }
            it("Usuario A creo al menos una recomendacion. Usuario B inactivo") {
                usuarioA.apply {
                    leer(libro1)
                    crearRecomendacion("titulo", mutableSetOf(libro1), "contenido", true)
                }
                sysAdmin.ejecutarListaProcesos()
                repositorioUsuario.objetosEnMemoria.shouldContain(usuarioA)
                repositorioUsuario.objetosEnMemoria.shouldNotContain(usuarioB)
            }
            it("Usuario A crea una recomendacion, usuario B la valora. Ambos son activos") {
                usuarioA.apply {
                    leer(libro1)
                    crearRecomendacion("titulo", mutableSetOf(libro1), "contenido", true)
                }
                usuarioB.apply {
                    leer(libro1)
                    valorarRecomendacion(usuarioA.recomendaciones[0], 5, "comentario")
                }
                sysAdmin.ejecutarListaProcesos()
                repositorioUsuario.objetosEnMemoria.shouldContain(usuarioA)
                repositorioUsuario.objetosEnMemoria.shouldContain(usuarioB)
            }
            it("Usuario B considera amigo a usuario A. Usuario B es inactivo") {
                usuarioB.apply {
                    agregarAmigo(usuarioA)
                }
                sysAdmin.ejecutarListaProcesos()
                repositorioUsuario.objetosEnMemoria.shouldContain(usuarioA)
                repositorioUsuario.objetosEnMemoria.shouldNotContain(usuarioB)
            }
        }

//        ////////////////////////////////
//        /////   FALTA IMPLEMENTAR
//        ////////////////////////////////
//        describe("Actualizar libros") {
//
//        }

        describe("Borrar centros de lectura expirados") {
            repoCentroLecura.create(particularExpirado)
            repoCentroLecura.create(particularNoExpirado)

            sysAdmin.agregarProcesos(
                ProcesosBuilder()
                    .borrarCentros(repoCentroLecura)
                    .build()
            )
            sysAdmin.ejecutarListaProcesos()
            it("El centro expirado fue eliminado") {
                repoCentroLecura.objetosEnMemoria.shouldNotContain(particularExpirado)
            }

            it("El centro NO expirado no fue eliminado") {
                repoCentroLecura.objetosEnMemoria shouldContain particularNoExpirado
            }
        }

        it("Agregar autores en grandes cantidades") {
            sysAdmin.agregarProcesos(
                ProcesosBuilder()
                    .agregarAutores(autores, repositorioAutores)
                    .build()
            )
            sysAdmin.ejecutarListaProcesos()
            repositorioAutores.objetosEnMemoria shouldContain autor1
            repositorioAutores.objetosEnMemoria shouldContain autor2
            repositorioAutores.objetosEnMemoria shouldContain autor3
        }

        it("Y cualquier combinacion de procesos") {
            repoCentroLecura.create(particularExpirado)
            sysAdmin.agregarProcesos(
                ProcesosBuilder()
                    .borrarCentros(repoCentroLecura)
                    .agregarAutores(autores, repositorioAutores)
                    .agregarAutores(autores2, repositorioAutores)
                    .build()
            )
            sysAdmin.ejecutarListaProcesos()

            repositorioAutores.objetosEnMemoria shouldContain autor1
            repositorioAutores.objetosEnMemoria shouldContain autor4
            repoCentroLecura.objetosEnMemoria.shouldNotContain(particularExpirado)

        }

        it("Se verifica el envio de mails con stub") {

            sysAdmin.agregarProcesos(
                ProcesosBuilder()
                    .agregarAutores(autores, repositorioAutores)
                    .build()
            )
            stubMailSender.mailsEnviados.size shouldBeEqual 0

            sysAdmin.listaProcesos.size shouldBeEqual 1
            sysAdmin.ejecutarListaProcesos()
            repositorioAutores.objetosEnMemoria shouldContain autor1
            repositorioAutores.objetosEnMemoria shouldContain autor2
            repositorioAutores.objetosEnMemoria shouldContain autor3

            stubMailSender.mailsEnviados.size shouldBeEqual 1

            stubMailSender.mailsEnviados[0].asunto shouldBeEqual "Se realizó el proceso: ${ProcesoAgregarAutores::class}"
        }

        describe("proceso de actualización de libros") {
            val servicioLibro = ServiceLibroStub()

            servicioLibro.json = """
        [
         {
           "id": 1,
           "ediciones": 255,
           "ventasSemanales": 500
         },
         {
           "id": 2,
           "ediciones": 300,
           "ventasSemanales": 600
         }
        ]
    """.trimIndent()

            val repositorioLibros = Repositorio<Libro>()

            repositorioLibros.apply {
                create(libro1)
                create(libro2)
            }

            sysAdmin.agregarProceso(ProcesoActualizadorLibros(servicioLibro, repositorioLibros))
            sysAdmin.ejecutarListaProcesos()

            it("El libro 1 tiene que tener 255 ediciones y 500 ventasemanales"){
                repositorioLibros.getByID(1).ediciones shouldBe 255
                repositorioLibros.getByID(1).ventasSemanales shouldBe 500
            }

            it("El libro 2 tiene que tener 300 ediciones y 600 ventasemanales"){
                repositorioLibros.getByID(2).ediciones shouldBe 300
                repositorioLibros.getByID(2).ventasSemanales shouldBe 600
            }
        }
    }
})

