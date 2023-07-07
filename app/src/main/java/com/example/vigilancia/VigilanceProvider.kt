package com.example.vigilancia

class VigilanceProvider {
    companion object{
        val vigilanceList = listOf<VigilanciasProgramadas>(
            VigilanciasProgramadas(
                institucion = "Une",
                direccion = "Av. Tonaltecas 332",
                clave = "001",
                photo = "https://cdn.milenio.com/uploads/media/2020/09/02/une-tampico_0_23_1200_745.jpg"),
            VigilanciasProgramadas(
                institucion = "Uteg",
                direccion = "Av. Zapopan 2342",
                clave = "002",
                photo = "https://sic.cultura.gob.mx/images/63159"),
            VigilanciasProgramadas(
                institucion = "SICYT",
                direccion = "Av. Guadalajara 2350",
                clave = "003",
                photo = "https://img.gruporeforma.com/imagenes/420x280/1/941/940342.jpg"),
        )
    }
}