package com.example.prueba_tecnica.ui.formats

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatFecha(fechaISO: String): String {
    val fecha = LocalDateTime.parse(fechaISO, DateTimeFormatter.ISO_DATE_TIME)
    val formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    return fecha.format(formatoSalida)
}