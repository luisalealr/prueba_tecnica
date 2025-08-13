package com.example.prueba_tecnica

import org.junit.Test
import org.junit.Assert.assertEquals
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FormatFechaTest {

    @Test
    fun `formatea fecha ISO a formato ddMMyyyy HHmm`() {
        val fechaISO = "2025-08-13T14:30:00"
        val resultado = formatFechaTestVersion(fechaISO)
        assertEquals("13/08/2025 14:30", resultado)
    }
}

//copia de la funcion para formatear fecha sin la anotacion @RequiresApi(Build.VERSION_CODES.O)
fun formatFechaTestVersion(fechaISO: String): String {
    val fecha = LocalDateTime.parse(fechaISO, DateTimeFormatter.ISO_DATE_TIME)
    val formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    return fecha.format(formatoSalida)
}
