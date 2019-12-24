package com.example.konekdatabase

class ApiEndPoint {
    companion object {
        //alamat IP didapatkan melalui cmd dengan mengetik ipconfig
        private val SERVER = "http://10.110.137.135/universitaskotlin/"
        val CREATE = SERVER+"create_fakultas.php"
        val READ = SERVER+"read_fakultas.php"
        val UPDATE = SERVER+"update_fakultas.php"
        val DELETE = SERVER+"delete_fakultas.php"
    }
}