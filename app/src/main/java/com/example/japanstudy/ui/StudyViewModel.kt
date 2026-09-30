package com.example.japanstudy.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.japanstudy.data.KanaItem
import com.example.japanstudy.data.KanaType
import com.example.japanstudy.data.VocabularyCategory
import com.example.japanstudy.data.VocabularyData
import com.example.japanstudy.data.local.AppDatabase
import com.example.japanstudy.data.local.QuizResultEntity
import com.example.japanstudy.data.repository.StudyRepository
import com.example.japanstudy.data.repository.WordWithProgress
import com.example.japanstudy.tts.JapaneseTtsHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class NavigationTab(val title: String) {
  HOME("Beranda"),
  VOCABULARY("Kosakata"),
  FLASHCARDS("Flashcard"),
  QUIZ("Kuis"),
  KANA("Kana"),
  STATS("Kemajuan")
}

enum class VocabFilterMode(val label: String) {
  ALL("Semua"),
  FAVORITES("Favorit"),
  MASTERED("Dikuasai"),
  LEARNING("Dipelajari")
}

enum class QuizType(val label: String, val description: String) {
  KANJI_TO_MEANING("Tebak Arti", "Lihat karakter Jepang, pilih arti bahasa Indonesia"),
  HIRAGANA_TO_ROMAJI("Tebak Romaji", "Lihat Hiragana/Katakana, pilih cara baca Romaji"),
  MEANING_TO_JAPANESE("Tebak Huruf Jepang", "Lihat arti bahasa Indonesia, pilih karakter Jepang")
}

data class QuizQuestion(
  val questionWord: WordWithProgress,
  val prompt: String,
  val subPrompt: String,
  val options: List<String>,
  val correctOptionIndex: Int
)

class StudyViewModel(application: Application) : AndroidViewModel(application) {

  private val repository: StudyRepository
  private val ttsHelper: JapaneseTtsHelper = JapaneseTtsHelper(application)

  init {
    val database = AppDatabase.getDatabase(application)
    repository = StudyRepository(database.studyDao())
  }

  // Navigation
  private val _currentTab = MutableStateFlow(NavigationTab.HOME)
  val currentTab: StateFlow<NavigationTab> = _currentTab.asStateFlow()

  // Repository Flows
  val wordsWithProgress: StateFlow<List<WordWithProgress>> = repository.getWordsWithProgress()
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = repository.allWords.map { WordWithProgress(it) }
    )

  val masteredCount: StateFlow<Int> = repository.masteredCount
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

  val favoriteCount: StateFlow<Int> = repository.favoriteCount
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

  val quizHistory: StateFlow<List<QuizResultEntity>> = repository.quizResults
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  val hiraganaList = repository.hiraganaList
  val katakanaList = repository.katakanaList

  // Search & Filters for Vocabulary
  val searchQuery = MutableStateFlow("")
  val selectedCategory = MutableStateFlow(VocabularyCategory.ALL)
  val selectedFilterMode = MutableStateFlow(VocabFilterMode.ALL)

  val filteredWords: StateFlow<List<WordWithProgress>> = combine(
    wordsWithProgress,
    searchQuery,
    selectedCategory,
    selectedFilterMode
  ) { words, query, category, filterMode ->
    words.filter { word ->
      val matchesQuery = query.isBlank() ||
          word.item.kanji.contains(query, ignoreCase = true) ||
          word.item.kana.contains(query, ignoreCase = true) ||
          word.item.romaji.contains(query, ignoreCase = true) ||
          word.item.meaningId.contains(query, ignoreCase = true) ||
          word.item.meaningEn.contains(query, ignoreCase = true)

      val matchesCategory = category == VocabularyCategory.ALL || word.item.category == category

      val matchesFilter = when (filterMode) {
        VocabFilterMode.ALL -> true
        VocabFilterMode.FAVORITES -> word.isFavorite
        VocabFilterMode.MASTERED -> word.isMastered
        VocabFilterMode.LEARNING -> !word.isMastered
      }

      matchesQuery && matchesCategory && matchesFilter
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  // Flashcards state
  private val _flashcardWords = MutableStateFlow<List<WordWithProgress>>(emptyList())
  val flashcardWords: StateFlow<List<WordWithProgress>> = _flashcardWords.asStateFlow()

  private val _currentFlashcardIndex = MutableStateFlow(0)
  val currentFlashcardIndex: StateFlow<Int> = _currentFlashcardIndex.asStateFlow()

  private val _isCardFlipped = MutableStateFlow(false)
  val isCardFlipped: StateFlow<Boolean> = _isCardFlipped.asStateFlow()

  private val _flashcardSessionComplete = MutableStateFlow(false)
  val flashcardSessionComplete: StateFlow<Boolean> = _flashcardSessionComplete.asStateFlow()

  // Quiz state
  private val _currentQuizType = MutableStateFlow(QuizType.KANJI_TO_MEANING)
  val currentQuizType: StateFlow<QuizType> = _currentQuizType.asStateFlow()

  private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
  val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

  private val _currentQuestionIndex = MutableStateFlow(0)
  val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

  private val _selectedQuizOption = MutableStateFlow<Int?>(null)
  val selectedQuizOption: StateFlow<Int?> = _selectedQuizOption.asStateFlow()

  private val _hasAnsweredCurrent = MutableStateFlow(false)
  val hasAnsweredCurrent: StateFlow<Boolean> = _hasAnsweredCurrent.asStateFlow()

  private val _quizScore = MutableStateFlow(0)
  val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

  private val _quizStreak = MutableStateFlow(0)
  val quizStreak: StateFlow<Int> = _quizStreak.asStateFlow()

  private val _isQuizCompleted = MutableStateFlow(false)
  val isQuizCompleted: StateFlow<Boolean> = _isQuizCompleted.asStateFlow()

  // Selected Item Dialogs
  val selectedWordForDetail = MutableStateFlow<WordWithProgress?>(null)
  val selectedKanaForDetail = MutableStateFlow<KanaItem?>(null)

  fun selectTab(tab: NavigationTab) {
    _currentTab.value = tab
    if (tab == NavigationTab.FLASHCARDS && _flashcardWords.value.isEmpty()) {
      startFlashcards()
    }
    if (tab == NavigationTab.QUIZ && _quizQuestions.value.isEmpty()) {
      startQuiz(_currentQuizType.value)
    }
  }

  fun toggleFavorite(wordId: Int) {
    viewModelScope.launch {
      repository.toggleFavorite(wordId)
    }
  }

  fun toggleMastered(wordId: Int) {
    viewModelScope.launch {
      repository.toggleMastered(wordId)
    }
  }

  fun speak(text: String) {
    ttsHelper.speak(text)
  }

  // --- Flashcard Logic ---
  fun startFlashcards(category: VocabularyCategory? = null) {
    val list = wordsWithProgress.value.let { all ->
      if (category != null && category != VocabularyCategory.ALL) {
        all.filter { it.item.category == category }
      } else {
        all
      }
    }.shuffled()

    _flashcardWords.value = if (list.isNotEmpty()) list else wordsWithProgress.value
    _currentFlashcardIndex.value = 0
    _isCardFlipped.value = false
    _flashcardSessionComplete.value = false
  }

  fun flipCard() {
    _isCardFlipped.value = !_isCardFlipped.value
  }

  fun markFlashcard(isMastered: Boolean) {
    val currentCard = _flashcardWords.value.getOrNull(_currentFlashcardIndex.value)
    if (currentCard != null) {
      viewModelScope.launch {
        repository.recordPractice(currentCard.item.id, isMastered)
      }
    }

    if (_currentFlashcardIndex.value + 1 < _flashcardWords.value.size) {
      _currentFlashcardIndex.value += 1
      _isCardFlipped.value = false
    } else {
      _flashcardSessionComplete.value = true
    }
  }

  fun previousFlashcard() {
    if (_currentFlashcardIndex.value > 0) {
      _currentFlashcardIndex.value -= 1
      _isCardFlipped.value = false
    }
  }

  // --- Quiz Logic ---
  fun startQuiz(type: QuizType, questionCount: Int = 10) {
    _currentQuizType.value = type
    val pool = wordsWithProgress.value.shuffled()
    if (pool.size < 4) return

    val questions = pool.take(questionCount).map { targetWord ->
      val distractors = pool.filter { it.item.id != targetWord.item.id }.shuffled().take(3)
      val allChoices = (distractors + targetWord).shuffled()

      when (type) {
        QuizType.KANJI_TO_MEANING -> {
          val correctIdx = allChoices.indexOf(targetWord)
          QuizQuestion(
            questionWord = targetWord,
            prompt = targetWord.item.kanji,
            subPrompt = "${targetWord.item.kana} (${targetWord.item.romaji})",
            options = allChoices.map { it.item.meaningId },
            correctOptionIndex = correctIdx
          )
        }
        QuizType.HIRAGANA_TO_ROMAJI -> {
          val correctIdx = allChoices.indexOf(targetWord)
          QuizQuestion(
            questionWord = targetWord,
            prompt = targetWord.item.kana,
            subPrompt = "Artinya: ${targetWord.item.meaningId}",
            options = allChoices.map { it.item.romaji },
            correctOptionIndex = correctIdx
          )
        }
        QuizType.MEANING_TO_JAPANESE -> {
          val correctIdx = allChoices.indexOf(targetWord)
          QuizQuestion(
            questionWord = targetWord,
            prompt = targetWord.item.meaningId,
            subPrompt = targetWord.item.meaningEn,
            options = allChoices.map { "${it.item.kanji} (${it.item.kana})" },
            correctOptionIndex = correctIdx
          )
        }
      }
    }

    _quizQuestions.value = questions
    _currentQuestionIndex.value = 0
    _selectedQuizOption.value = null
    _hasAnsweredCurrent.value = false
    _quizScore.value = 0
    _quizStreak.value = 0
    _isQuizCompleted.value = false
  }

  fun answerQuiz(optionIndex: Int) {
    if (_hasAnsweredCurrent.value) return
    _selectedQuizOption.value = optionIndex
    _hasAnsweredCurrent.value = true

    val currentQ = _quizQuestions.value.getOrNull(_currentQuestionIndex.value) ?: return
    val isCorrect = optionIndex == currentQ.correctOptionIndex

    if (isCorrect) {
      _quizScore.value += 10 + (_quizStreak.value * 2)
      _quizStreak.value += 1
      speak(currentQ.questionWord.item.kana)
    } else {
      _quizStreak.value = 0
    }

    viewModelScope.launch {
      repository.recordPractice(currentQ.questionWord.item.id, isCorrect)
    }
  }

  fun nextQuizQuestion() {
    if (_currentQuestionIndex.value + 1 < _quizQuestions.value.size) {
      _currentQuestionIndex.value += 1
      _selectedQuizOption.value = null
      _hasAnsweredCurrent.value = false
    } else {
      _isQuizCompleted.value = true
      viewModelScope.launch {
        repository.saveQuizResult(
          score = _quizScore.value,
          total = _quizQuestions.value.size,
          quizType = _currentQuizType.value.label
        )
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    ttsHelper.shutdown()
  }
}
