package com.example.japanstudy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_progress")
data class WordProgressEntity(
  @PrimaryKey val wordId: Int,
  val isFavorite: Boolean = false,
  val isMastered: Boolean = false,
  val practiceCount: Int = 0,
  val correctCount: Int = 0,
  val lastPracticedTime: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  val score: Int,
  val totalQuestions: Int,
  val quizType: String,
  val timestamp: Long = System.currentTimeMillis()
)
