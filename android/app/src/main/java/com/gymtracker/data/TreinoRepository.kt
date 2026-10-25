package com.gymtracker.repository

import com.gymtracker.data.dao.TreinoDao
import com.gymtracker.data.entity.Treino

class TreinoRepository(private val treinoDao: TreinoDao) {

    suspend fun addTreino(name: String, type: String, classes: String): Long{
        validate(name, type, classes)
        return treinoDao.save(Treino(name = name.trim(), type = type.trim(), classes = classes.trim()))
    }

    suspend fun updateTreino(name: String, type: String, classes: String, id: Long) {
        require(name.isNotBlank()) {"O nome do treino deve ser preenchido"}
        require(type.isNotBlank()) {"O tipo do treino deve ser preenchido"}
        require(classes.isNotBlank()) {"A classe do treino deve ser preenchida"}

        val currentValue = treinoDao.searchById(id)
            ?: throw IllegalArgumentException("Treino $id não encontrado")

        treinoDao.update(
            currentValue.copy(name = name.trim(), type = type.trim(), classes = classes.trim())
        )
    }

    suspend fun listClass(classes: String):List<Treino> = treinoDao.listByClass(classes)

    suspend fun listType(type: String):List<Treino> = treinoDao.listByType(type)

    suspend fun deleteTreino(id: Long){
        treinoDao.deleteById(id)
    }
    
    suspend fun searchById(id:Long){
        treinoDao.searchById(id)
    }

    private fun validate(name: String, type: String, classes: String){
        require(name.isNotBlank()) {"O nome do treino deve ser preenchido"}
        require(type.isNotBlank()) {"O tipo do treino deve ser preenchido"}
        require(classes.isNotBlank()) {"A classe do treino deve ser preenchida"}
    }
}
