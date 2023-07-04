package com.example.vigilancia

class VigilanceProvider {
    companion object{
        val vigilanceList = listOf<VigilanciasProgramadas>(
            VigilanciasProgramadas(
                institucion = "Une",
                direccion = "Av. Tonaltecas 332",
                clave = "001",
                photo = "https://tinyurl.com/4ytx6jm9"),
            VigilanciasProgramadas(
                institucion = "Uteg",
                direccion = "Av. Zapopan 2342",
                clave = "002",
                photo = "https://www.solili.mx/oficina/corporativo-mind-guadalajara-gdl/1862/"),
            VigilanciasProgramadas(
                institucion = "SICYT",
                direccion = "Av. Guadalajara 2350",
                clave = "003",
                photo = "https://assets.staging.bedu.org/UTEG/nosotros_planteles_campus_desk_d2d8935bc5.jpg"),
        )
    }
}