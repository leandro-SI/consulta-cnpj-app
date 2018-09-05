package com.apireceita.leandro.apireceita

class CnpjDados {

    lateinit var dataSituacao: String
    lateinit var nome: String
    lateinit var uf: String
    lateinit var telefone: String
    lateinit var status: String

    lateinit var situacao: String
    lateinit var bairro: String
    lateinit var logradouro: String
    lateinit var numero: String
    lateinit var cep: String
    lateinit var municipio: String
    lateinit var abertura: String


    constructor(dataSituacao: String, nome: String, uf: String, telefone: String, status: String, situacao: String, bairro: String, logradouro: String, numero: String, cep: String, municipio: String, abertura: String) {
        this.dataSituacao = dataSituacao
        this.nome = nome
        this.uf = uf
        this.telefone = telefone
        this.status = status
        this.situacao = situacao
        this.bairro = bairro
        this.logradouro = logradouro
        this.numero = numero
        this.cep = cep
        this.municipio = municipio
        this.abertura = abertura
    }
}

