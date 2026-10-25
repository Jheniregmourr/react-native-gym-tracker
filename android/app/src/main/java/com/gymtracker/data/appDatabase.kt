package com.gymtracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gymtracker.data.dao.TreinoDao
import com.gymtracker.data.entity.Treino

@Database(
    entities = [Treino::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun treinoDao(): TreinoDao

companion object {
    @Volatile // Faz as threads enxergarem o valor atualizado
    private var INSTANCE: AppDatabase? = null


    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) { //Deixa só uma thread por vez entrar
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "treinos.db"
            ).build().also {INSTANCE = it}
        }
    }
}
}