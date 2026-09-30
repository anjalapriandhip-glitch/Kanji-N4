package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.japanstudy.ui.NavigationTab
import com.example.japanstudy.ui.StudyViewModel
import com.example.japanstudy.ui.components.KanaDetailDialog
import com.example.japanstudy.ui.components.WordDetailDialog
import com.example.japanstudy.ui.screens.FlashcardScreen
import com.example.japanstudy.ui.screens.HomeScreen
import com.example.japanstudy.ui.screens.KanaScreen
import com.example.japanstudy.ui.screens.QuizScreen
import com.example.japanstudy.ui.screens.StatsScreen
import com.example.japanstudy.ui.screens.VocabularyListScreen
import com.example.ui.theme.JapanStudyTheme
import com.example.ui.theme.JapaneseCrimson

class MainActivity : ComponentActivity() {

  private val viewModel: StudyViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      JapanStudyTheme {
        JapanStudyApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun JapanStudyApp(viewModel: StudyViewModel) {
  val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
  val wordsWithProgress by viewModel.wordsWithProgress.collectAsStateWithLifecycle()
  val filteredWords by viewModel.filteredWords.collectAsStateWithLifecycle()
  val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
  val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
  val selectedFilterMode by viewModel.selectedFilterMode.collectAsStateWithLifecycle()

  val masteredCount by viewModel.masteredCount.collectAsStateWithLifecycle()
  val favoriteCount by viewModel.favoriteCount.collectAsStateWithLifecycle()
  val quizHistory by viewModel.quizHistory.collectAsStateWithLifecycle()

  // Flashcards state
  val flashcardWords by viewModel.flashcardWords.collectAsStateWithLifecycle()
  val currentCardIndex by viewModel.currentFlashcardIndex.collectAsStateWithLifecycle()
  val isCardFlipped by viewModel.isCardFlipped.collectAsStateWithLifecycle()
  val flashcardSessionComplete by viewModel.flashcardSessionComplete.collectAsStateWithLifecycle()

  // Quiz state
  val currentQuizType by viewModel.currentQuizType.collectAsStateWithLifecycle()
  val quizQuestions by viewModel.quizQuestions.collectAsStateWithLifecycle()
  val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
  val selectedQuizOption by viewModel.selectedQuizOption.collectAsStateWithLifecycle()
  val hasAnsweredCurrent by viewModel.hasAnsweredCurrent.collectAsStateWithLifecycle()
  val quizScore by viewModel.quizScore.collectAsStateWithLifecycle()
  val quizStreak by viewModel.quizStreak.collectAsStateWithLifecycle()
  val isQuizCompleted by viewModel.isQuizCompleted.collectAsStateWithLifecycle()

  // Dialogs
  val selectedWordForDetail by viewModel.selectedWordForDetail.collectAsStateWithLifecycle()
  val selectedKanaForDetail by viewModel.selectedKanaForDetail.collectAsStateWithLifecycle()

  // Back handling
  BackHandler(enabled = selectedWordForDetail != null || selectedKanaForDetail != null || currentTab != NavigationTab.HOME) {
    when {
      selectedWordForDetail != null -> viewModel.selectedWordForDetail.value = null
      selectedKanaForDetail != null -> viewModel.selectedKanaForDetail.value = null
      currentTab != NavigationTab.HOME -> viewModel.selectTab(NavigationTab.HOME)
    }
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = {
      NavigationBar(
        modifier = Modifier.testTag("main_navigation_bar"),
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
      ) {
        NavigationBarItem(
          selected = currentTab == NavigationTab.HOME,
          onClick = { viewModel.selectTab(NavigationTab.HOME) },
          icon = { Icon(Icons.Default.Home, contentDescription = "Beranda") },
          label = { Text("Beranda", fontSize = 10.sp, fontWeight = if (currentTab == NavigationTab.HOME) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(indicatorColor = JapaneseCrimson.copy(alpha = 0.15f)),
          modifier = Modifier.testTag("nav_item_home")
        )
        NavigationBarItem(
          selected = currentTab == NavigationTab.VOCABULARY,
          onClick = { viewModel.selectTab(NavigationTab.VOCABULARY) },
          icon = { Icon(Icons.Default.Translate, contentDescription = "Kosakata") },
          label = { Text("Kosakata", fontSize = 10.sp, fontWeight = if (currentTab == NavigationTab.VOCABULARY) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(indicatorColor = JapaneseCrimson.copy(alpha = 0.15f)),
          modifier = Modifier.testTag("nav_item_vocabulary")
        )
        NavigationBarItem(
          selected = currentTab == NavigationTab.FLASHCARDS,
          onClick = { viewModel.selectTab(NavigationTab.FLASHCARDS) },
          icon = { Icon(Icons.Default.Style, contentDescription = "Flashcard") },
          label = { Text("Flashcard", fontSize = 10.sp, fontWeight = if (currentTab == NavigationTab.FLASHCARDS) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(indicatorColor = JapaneseCrimson.copy(alpha = 0.15f)),
          modifier = Modifier.testTag("nav_item_flashcards")
        )
        NavigationBarItem(
          selected = currentTab == NavigationTab.QUIZ,
          onClick = { viewModel.selectTab(NavigationTab.QUIZ) },
          icon = { Icon(Icons.Default.Quiz, contentDescription = "Kuis") },
          label = { Text("Kuis", fontSize = 10.sp, fontWeight = if (currentTab == NavigationTab.QUIZ) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(indicatorColor = JapaneseCrimson.copy(alpha = 0.15f)),
          modifier = Modifier.testTag("nav_item_quiz")
        )
        NavigationBarItem(
          selected = currentTab == NavigationTab.KANA,
          onClick = { viewModel.selectTab(NavigationTab.KANA) },
          icon = { Icon(Icons.Default.Psychology, contentDescription = "Kana") },
          label = { Text("Kana", fontSize = 10.sp, fontWeight = if (currentTab == NavigationTab.KANA) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(indicatorColor = JapaneseCrimson.copy(alpha = 0.15f)),
          modifier = Modifier.testTag("nav_item_kana")
        )
        NavigationBarItem(
          selected = currentTab == NavigationTab.STATS,
          onClick = { viewModel.selectTab(NavigationTab.STATS) },
          icon = { Icon(Icons.Default.Timeline, contentDescription = "Kemajuan") },
          label = { Text("Kemajuan", fontSize = 10.sp, fontWeight = if (currentTab == NavigationTab.STATS) FontWeight.Bold else FontWeight.Normal) },
          colors = NavigationBarItemDefaults.colors(indicatorColor = JapaneseCrimson.copy(alpha = 0.15f)),
          modifier = Modifier.testTag("nav_item_stats")
        )
      }
    }
  ) { innerPadding ->
    val contentModifier = Modifier.padding(innerPadding)

    when (currentTab) {
      NavigationTab.HOME -> {
        HomeScreen(
          totalWordsCount = wordsWithProgress.size,
          masteredCount = masteredCount,
          favoriteCount = favoriteCount,
          words = wordsWithProgress,
          onNavigateTab = { viewModel.selectTab(it) },
          onSelectCategory = { viewModel.selectedCategory.value = it },
          onSpeak = { viewModel.speak(it) },
          onWordClick = { viewModel.selectedWordForDetail.value = it },
          modifier = contentModifier
        )
      }
      NavigationTab.VOCABULARY -> {
        VocabularyListScreen(
          words = filteredWords,
          searchQuery = searchQuery,
          onSearchQueryChange = { viewModel.searchQuery.value = it },
          selectedCategory = selectedCategory,
          onCategorySelect = { viewModel.selectedCategory.value = it },
          selectedFilterMode = selectedFilterMode,
          onFilterModeSelect = { viewModel.selectedFilterMode.value = it },
          onSpeak = { viewModel.speak(it) },
          onToggleFavorite = { viewModel.toggleFavorite(it) },
          onToggleMastered = { viewModel.toggleMastered(it) },
          onWordClick = { viewModel.selectedWordForDetail.value = it },
          modifier = contentModifier
        )
      }
      NavigationTab.FLASHCARDS -> {
        FlashcardScreen(
          cards = flashcardWords,
          currentIndex = currentCardIndex,
          isFlipped = isCardFlipped,
          sessionComplete = flashcardSessionComplete,
          onFlipCard = { viewModel.flipCard() },
          onMarkCard = { viewModel.markFlashcard(it) },
          onPreviousCard = { viewModel.previousFlashcard() },
          onStartSession = { viewModel.startFlashcards(it) },
          onSpeak = { viewModel.speak(it) },
          onToggleFavorite = { viewModel.toggleFavorite(it) },
          modifier = contentModifier
        )
      }
      NavigationTab.QUIZ -> {
        QuizScreen(
          currentQuizType = currentQuizType,
          questions = quizQuestions,
          currentIndex = currentQuestionIndex,
          selectedOption = selectedQuizOption,
          hasAnswered = hasAnsweredCurrent,
          score = quizScore,
          streak = quizStreak,
          isQuizCompleted = isQuizCompleted,
          onStartQuiz = { viewModel.startQuiz(it) },
          onAnswerOption = { viewModel.answerQuiz(it) },
          onNextQuestion = { viewModel.nextQuizQuestion() },
          onSpeak = { viewModel.speak(it) },
          modifier = contentModifier
        )
      }
      NavigationTab.KANA -> {
        KanaScreen(
          hiraganaList = viewModel.hiraganaList,
          katakanaList = viewModel.katakanaList,
          onSpeak = { viewModel.speak(it) },
          onKanaClick = { viewModel.selectedKanaForDetail.value = it },
          modifier = contentModifier
        )
      }
      NavigationTab.STATS -> {
        StatsScreen(
          totalWords = wordsWithProgress.size,
          masteredCount = masteredCount,
          favoriteCount = favoriteCount,
          quizHistory = quizHistory,
          modifier = contentModifier
        )
      }
    }

    // Word Detail Dialog
    selectedWordForDetail?.let { word ->
      WordDetailDialog(
        word = word,
        onDismiss = { viewModel.selectedWordForDetail.value = null },
        onSpeak = { viewModel.speak(it) },
        onToggleFavorite = { viewModel.toggleFavorite(it) },
        onToggleMastered = { viewModel.toggleMastered(it) }
      )
    }

    // Kana Detail Dialog
    selectedKanaForDetail?.let { kana ->
      KanaDetailDialog(
        kana = kana,
        onDismiss = { viewModel.selectedKanaForDetail.value = null },
        onSpeak = { viewModel.speak(it) }
      )
    }
  }
}
