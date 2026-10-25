package com.gymtracker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gymtracker.data.entity.Treino
// suspend fun = Impede que o app trave, ele tenta realizar a operacao em background e pode pausar ela momentaneamente e tentar dnv dps sem quebrar o app
// : Long retorna o id que foi gerado
@Dao
interface TreinoDao {
    
    @Insert
    suspend fun save(treino: Treino) : Long

    @Update
    suspend fun update(treino: Treino)

    @Query ("DELETE FROM treino WHERE id = :id")
    suspend fun deleteById(id: Long)
    
    @Query ("SELECT * FROM treino")
    suspend fun listAll(): List<Treino>

    @Query ("SELECT * FROM treino ORDER BY classes = :classes")
    suspend fun listByClass(classes: String): List<Treino>
    
    @Query ("SELECT * FROM treino WHERE type = :type")
    suspend fun listByType(type: String) : List<Treino>

    @Query ("SELECT * FROM treino WHERE id = :id")
    suspend fun searchById(id: Long): Treino?
}