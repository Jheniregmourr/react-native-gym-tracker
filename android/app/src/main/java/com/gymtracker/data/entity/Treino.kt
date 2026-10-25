package com.gymtracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Treino")
data class Treino(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var name: String,
    var type: String, //Superiores ou Inferiores
    var classes: String //A, B, C...
    //Os exercicios propriamente ditos serao outra tabela
)