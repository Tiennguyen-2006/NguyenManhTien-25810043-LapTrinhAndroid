package com.example.colorvalueroom.data

import kotlinx.coroutines.flow.Flow

class ColorRepository(private val colorDao: ColorDao) {
    val colors: Flow<List<ColorItem>> = colorDao.observeAll()

    suspend fun save(color: ColorItem) {
        colorDao.insert(color)
    }

    suspend fun remove(color: ColorItem) {
        colorDao.delete(color)
    }

    suspend fun addStarterColors() {
        if (colorDao.count() == 0) {
            colorDao.insert(ColorItem(name = "Bầu trời", hex = "#4D96FF"))
            colorDao.insert(ColorItem(name = "Lá cây", hex = "#39A96B"))
            colorDao.insert(ColorItem(name = "Hoàng hôn", hex = "#FF8C42"))
        }
    }
}
