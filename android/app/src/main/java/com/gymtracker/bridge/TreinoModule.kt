package com.gymtracker.bridge

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableArray
import com.facebook.react.module.annotations.ReactModule
import com.gymtracker.data.AppDatabase
import com.gymtracker.repository.TreinoRepository
import com.gymtracker.data.entity.Treino
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@ReactModule(name = "TreinoModule")
class TreinoModule(reactContext: ReactApplicationContext) :
    NativeTreinoModuleSpec(reactContext) {

    private val repository = TreinoRepository(
        AppDatabase.getInstance(reactContext).treinoDao()
    )
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun ping(promise: Promise) {
        promise.resolve("pong")
    }

    override fun addTreino(name: String, type: String, classes: String, promise: Promise) {
        scope.launch {
            try {
                val id = repository.addTreino(name, type, classes)
                promise.resolve(id.toDouble())
            } catch (e: Exception) {
                promise.reject("ERR_ADD_TREINO", e.message, e)
            }
        }
    }

    override fun updateTreino(
        id: Double,
        name: String,
        type: String,
        classes: String,
        promise: Promise
    ) {
        scope.launch {
            try {
                repository.updateTreino(id = id.toLong(), name = name, type = type, classes = classes)
                promise.resolve(null)
            } catch (e: Exception) {
                promise.reject("ERR_UPDATE_TREINO", e.message, e)
            }
        }
    }

    override fun delete(id: Double, promise: Promise) {
        scope.launch {
            try {
                repository.deleteTreino(id = id.toLong())
                promise.resolve(null)
            } catch (e: Exception) {
                promise.reject("ERR_DELETE_TREINO", e.message, e)
            }
        }
    }

    override fun listClass(classes: String, promise: Promise) {
        scope.launch {
            try {
                promise.resolve(toWritableArray(repository.listClass(classes)))
            } catch (e: Exception) {
                promise.reject("ERR_LIST_CLASS", e.message, e)
            }
        }
    }

    override fun listType(type: String, promise: Promise) {
        scope.launch {
            try {
                promise.resolve(toWritableArray(repository.listType(type)))
            } catch (e: Exception) {
                promise.reject("ERR_LIST_TYPE", e.message, e)
            }
        }
    }

    private fun toWritableArray(treinos: List<Treino>): WritableArray {
        val array = Arguments.createArray()
        for (treino in treinos) {
            val map = Arguments.createMap()
            map.putDouble("id", treino.id.toDouble())
            map.putString("name", treino.name)
            map.putString("type", treino.type)
            map.putString("classes", treino.classes)
            array.pushMap(map)
        }
        return array
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}