package com.nazmul.notesapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazmul.notesapp.db.NotesDao
import com.nazmul.notesapp.repository.NoteRepository
import com.nazmul.notesapp.repository.NotesRepositoryImpl
import com.nazmul.notesapp.utils.dispatcher.DefaultDispatchers
import com.nazmul.notesapp.utils.dispatcher.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RoomDatabase::class.java,
        "notes.db"
    ).build()


    @Provides
    @Singleton
    fun provideNoteRepository(dao : NotesDao) : NoteRepository {
        return NotesRepositoryImpl(dao)

    }

    @Singleton
    @Provides
    fun provideDispatchersProvider() : DispatchersProvider{
        return DefaultDispatchers()
    }


}