
package com.example.myapplication.ui.notifications

import android.app.Application
import com.example.myapplication.ui.notifications.data.ItemRoomDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InventoryApplication  : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
