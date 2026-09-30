package com.example.japanstudy.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.japanstudy.ui.QuizQuestion
import com.example.japanstudy.ui.QuizType
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.JapaneseCrimson
import com.example.ui.theme.MatchaGreen

@Composable
fun QuizScreen(
  currentQuizType: QuizType,
  questions: List<QuizQuestion>,
  currentIndex: Int,
  selectedOption: Int?,
  hasAnswered: Boolean,
  score: Int,
  streak: Int,
  isQuizCompleted: Boolean,
  onStartQuiz: (QuizType) -> Unit,
  onAnswerOption: (Int) -> Unit,
  onNextQuestion: () -> Unit,
  onSpeak: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "Kuis Latihan (クイズ)",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Uji pemahaman kosakata Jepang Anda",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      // Quiz mode indicator
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer
      ) {
        Text(
          text = currentQuizType.label,
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Quiz Mode Selector Buttons
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      QuizType.values().forEach { type ->
        val isSelected = currentQuizType == type
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = if (isSelected) JapaneseCrimson else MaterialTheme.colorScheme.surfaceVariant,
          modifier = Modifier
            .weight(1f)
            .clickable { onStartQuiz(type) }
            .testTag("quiz_type_${type.name}")
        ) {
          Text(
            text = type.label,
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
              fontSize = 11.sp
            ),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    if (isQuizCompleted || questions.isEmpty()) {
      // Quiz Finished Screen
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .padding(vertical = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Box(
            modifier = Modifier
              .size(72.dp)
              .background(GoldAccent.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              Icons.Default.EmojiEvents,
              contentDescription = null,
              tint = GoldAccent,
              modifier = Modifier.size(40.dp)
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "すばらしい！ (Luar Biasa!)",
            style = MaterialTheme.typography.headlineSmall.copy(
              fontWeight = FontWeight.Bold,
              color = JapaneseCrimson
            )
          )
          Text(
            text = "Kuis telah diselesaikan!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Spacer(modifier = Modifier.height(20.dp))

          Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.padding(horizontal = 24.dp)
          ) {
            Column(
              modifier = Modifier.padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "Skor Akhir Kamu",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = "$score Poin",
                style = MaterialTheme.typography.headlineMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
              Text(
                text = "Mode: ${currentQuizType.label}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(28.dp))

          Button(
            onClick = { onStartQuiz(currentQuizType) },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = JapaneseCrimson),
            modifier = Modifier.testTag("quiz_restart_btn")
          ) {
            Icon(Icons.Default.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Coba Kuis Lagi")
          }
        }
      }
    } else {
      val question = questions.getOrNull(currentIndex) ?: questions.first()
      val progress = (currentIndex + 1).toFloat() / questions.size.toFloat()

      // Progress & Score Bar
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Pertanyaan ${currentIndex + 1} / ${questions.size}",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.primary
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
          if (streak > 1) {
            Icon(
              Icons.Default.LocalFireDepartment,
              contentDescription = "Streak",
              tint = JapaneseCrimson,
              modifier = Modifier.size(18.dp)
            )
            Text(
              text = "$streak",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = JapaneseCrimson
            )
            Spacer(modifier = Modifier.width(12.dp))
          }

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
          ) {
            Text(
              text = "$score Poin",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }

      LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
          .fillMaxWidth()
          .height(6.dp)
          .padding(vertical = 4.dp),
        color = JapaneseCrimson,
        trackColor = MaterialTheme.colorScheme.surfaceVariant
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Question Card
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = question.prompt,
            style = MaterialTheme.typography.headlineLarge.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 34.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = question.subPrompt,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
          )

          Spacer(modifier = Modifier.height(8.dp))

          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
              .size(36.dp)
              .clickable { onSpeak(question.questionWord.item.kana) }
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                Icons.Default.VolumeUp,
                contentDescription = "Putar Suara",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Multiple Choice Options
      Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        question.options.forEachIndexed { index, option ->
          val isSelected = selectedOption == index
          val isCorrect = index == question.correctOptionIndex

          val backgroundColor = when {
            hasAnswered && isCorrect -> MatchaGreen.copy(alpha = 0.2f)
            hasAnswered && isSelected && !isCorrect -> JapaneseCrimson.copy(alpha = 0.2f)
            else -> MaterialTheme.colorScheme.surface
          }

          val borderColor = when {
            hasAnswered && isCorrect -> MatchaGreen
            hasAnswered && isSelected && !isCorrect -> JapaneseCrimson
            else -> Color.Transparent
          }

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable(enabled = !hasAnswered) { onAnswerOption(index) }
              .testTag("quiz_option_$index"),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            border = if (borderColor != Color.Transparent) androidx.compose.foundation.BorderStroke(2.dp, borderColor) else null,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "${('A'.code + index).toChar()}. $option",
                style = MaterialTheme.typography.bodyLarge.copy(
                  fontWeight = if (isSelected || (hasAnswered && isCorrect)) FontWeight.Bold else FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
              )

              if (hasAnswered) {
                if (isCorrect) {
                  Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Benar",
                    tint = MatchaGreen,
                    modifier = Modifier.size(22.dp)
                  )
                } else if (isSelected) {
                  Icon(
                    Icons.Default.Close,
                    contentDescription = "Salah",
                    tint = JapaneseCrimson,
                    modifier = Modifier.size(22.dp)
                  )
                }
              }
            }
          }
        }
      }

      // Next Question Button
      AnimatedVisibility(visible = hasAnswered) {
        Button(
          onClick = onNextQuestion,
          modifier = Modifier
            .fillMaxWidth()
            .testTag("quiz_next_btn"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(containerColor = JapaneseCrimson)
        ) {
          Text(
            text = if (currentIndex + 1 < questions.size) "Pertanyaan Selanjutnya →" else "Lihat Hasil Kuis ✨",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 4.dp)
          )
        }
      }
    }
  }
}
