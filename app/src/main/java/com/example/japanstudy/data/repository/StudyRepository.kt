package com.example.japanstudy.data.repository

import com.example.japanstudy.data.KanaItem
import com.example.japanstudy.data.KanaType
import com.example.japanstudy.data.VocabularyCategory
import com.example.japanstudy.data.VocabularyData
import com.example.japanstudy.data.VocabularyItem
import com.example.japanstudy.data.local.QuizResultEntity
import com.example.japanstudy.data.local.StudyDao
import com.example.japanstudy.data.local.WordProgressEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class WordWithProgress(
  val item: VocabularyItem,
  val isFavorite: Boolean = false,
  val isMastered: Boolean = false,
  val practiceCount: Int = 0,
  val correctCount: Int = 0
)

class StudyRepository(private val studyDao: StudyDao) {

  val allWords: List<VocabularyItem> = VocabularyData.vocabularyList

  val hiraganaList: List<KanaItem> = VocabularyData.hiraganaBasicList
  val katakanaList: List<KanaItem> = VocabularyData.katakanaBasicList

  val wordProgressList: Flow<List<WordProgressEntity>> = studyDao.getAllWordProgress()
  val masteredCount: Flow<Int> = studyDao.getMasteredCount()
  val favoriteCount: Flow<Int> = studyDao.getFavoriteCount()
  val quizResults: Flow<List<QuizResultEntity>> = studyDao.getAllQuizResults()

  fun getWordsWithProgress(): Flow<List<WordWithProgress>> {
    return studyDao.getAllWordProgress().map { progressList ->
      val progressMap = progressList.associateBy { it.wordId }
      allWords.map { word ->
        val progress = progressMap[word.id]
        WordWithProgress(
          item = word,
          isFavorite = progress?.isFavorite ?: false,
          isMastered = progress?.isMastered ?: false,
          practiceCount = progress?.practiceCount ?: 0,
          correctCount = progress?.correctCount ?: 0
        )
      }
    }
  }

  suspend fun toggleFavorite(wordId: Int) {
    val current = studyDao.getWordProgressDirect(wordId)
    if (current == null) {
      studyDao.insertOrUpdateWordProgress(
        WordProgressEntity(wordId = wordId, isFavorite = true)
      )
    } else {
      studyDao.insertOrUpdateWordProgress(
        current.copy(isFavorite = !current.isFavorite)
      )
    }
  }

  suspend fun toggleMastered(wordId: Int) {
    val current = studyDao.getWordProgressDirect(wordId)
    if (current == null) {
      studyDao.insertOrUpdateWordProgress(
        WordProgressEntity(wordId = wordId, isMastered = true)
      )
    } else {
      studyDao.insertOrUpdateWordProgress(
        current.copy(isMastered = !current.isMastered)
      )
    }
  }

  suspend fun recordPractice(wordId: Int, isCorrect: Boolean) {
    val current = studyDao.getWordProgressDirect(wordId)
    if (current == null) {
      studyDao.insertOrUpdateWordProgress(
        WordProgressEntity(
          wordId = wordId,
          practiceCount = 1,
          correctCount = if (isCorrect) 1 else 0,
          isMastered = isCorrect,
          lastPracticedTime = System.currentTimeMillis()
        )
      )
    } else {
      val newPractice = current.practiceCount + 1
      val newCorrect = current.correctCount + (if (isCorrect) 1 else 0)
      val newMastered = current.isMastered || (newCorrect >= 2)
      studyDao.insertOrUpdateWordProgress(
        current.copy(
          practiceCount = newPractice,
          correctCount = newCorrect,
          isMastered = newMastered,
          lastPracticedTime = System.currentTimeMillis()
        )
      )
    }
  }

  suspend fun saveQuizResult(score: Int, total: Int, quizType: String) {
    studyDao.insertQuizResult(
      QuizResultEntity(
        score = score,
        totalQuestions = total,
        quizType = quizType
      )
    )
  }
}
