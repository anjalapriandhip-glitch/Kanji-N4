package com.example.japanstudy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {
  @Query("SELECT * FROM word_progress")
  fun getAllWordProgress(): Flow<List<WordProgressEntity>>

  @Query("SELECT * FROM word_progress WHERE wordId = :wordId LIMIT 1")
  fun getWordProgress(wordId: Int): Flow<WordProgressEntity?>

  @Query("SELECT * FROM word_progress WHERE wordId = :wordId LIMIT 1")
  suspend fun getWordProgressDirect(wordId: Int): WordProgressEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrUpdateWordProgress(entity: WordProgressEntity)

  @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC LIMIT 20")
  fun getAllQuizResults(): Flow<List<QuizResultEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertQuizResult(entity: QuizResultEntity)

  @Query("SELECT COUNT(*) FROM word_progress WHERE isMastered = 1")
  fun getMasteredCount(): Flow<Int>

  @Query("SELECT COUNT(*) FROM word_progress WHERE isFavorite = 1")
  fun getFavoriteCount(): Flow<Int>
}
