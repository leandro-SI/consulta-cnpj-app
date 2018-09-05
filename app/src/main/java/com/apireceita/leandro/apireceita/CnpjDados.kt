package com.apireceita.leandro.apireceita

class CnpjDados {

    lateinit var dataSituacao: String
    lateinit var nome: String
    lateinit var uf: String
    lateinit var telefone: String
    lateinit var status: String

    constructor(dataSituacao: String, nome: String, uf: String, telefone: String, status: String) {
        this.dataSituacao = dataSituacao
        this.nome = nome
        this.uf = uf
        this.telefone = telefone
        this.status = status
    }
}

