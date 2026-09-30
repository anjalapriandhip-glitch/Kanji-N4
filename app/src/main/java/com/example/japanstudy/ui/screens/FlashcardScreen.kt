package com.example.japanstudy.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.japanstudy.data.VocabularyCategory
import com.example.japanstudy.data.repository.WordWithProgress
import com.example.ui.theme.JapaneseCrimson
import com.example.ui.theme.MatchaGreen
import com.example.ui.theme.SakuraPink

@Composable
fun FlashcardScreen(
  cards: List<WordWithProgress>,
  currentIndex: Int,
  isFlipped: Boolean,
  sessionComplete: Boolean,
  onFlipCard: () -> Unit,
  onMarkCard: (Boolean) -> Unit,
  onPreviousCard: () -> Unit,
  onStartSession: (VocabularyCategory?) -> Unit,
  onSpeak: (String) -> Unit,
  onToggleFavorite: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedCategory by remember { mutableStateOf(VocabularyCategory.ALL) }

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
          text = "Flashcard (フラッシュカード)",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Ketuk kartu untuk melihat arti & contoh",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      IconButton(
        onClick = { onStartSession(if (selectedCategory == VocabularyCategory.ALL) null else selectedCategory) },
        modifier = Modifier.testTag("flashcard_shuffle_btn")
      ) {
        Icon(Icons.Default.Shuffle, contentDescription = "Acak Kartu", tint = MaterialTheme.colorScheme.primary)
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Category Selector
    LazyRow(
      modifier = Modifier.fillMaxWidth(),
      contentPadding = PaddingValues(vertical = 4.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(VocabularyCategory.values()) { category ->
        val isSelected = selectedCategory == category
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
          modifier = Modifier.clickable {
            selectedCategory = category
            onStartSession(if (category == VocabularyCategory.ALL) null else category)
          }
        ) {
          Text(
            text = category.titleId,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    if (sessionComplete || cards.isEmpty()) {
      // Completed state card
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .padding(vertical = 24.dp),
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
          Text(
            text = "お疲れ様でした！",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Bold,
              color = JapaneseCrimson
            )
          )
          Text(
            text = "Otsukaresama deshita!",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "Sesi Flashcard Selesai 🎉",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Kamu telah meninjau ${cards.size} kata dalam sesi ini.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
          )

          Spacer(modifier = Modifier.height(24.dp))

          Button(
            onClick = { onStartSession(if (selectedCategory == VocabularyCategory.ALL) null else selectedCategory) },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = JapaneseCrimson),
            modifier = Modifier.testTag("flashcard_restart_btn")
          ) {
            Icon(Icons.Default.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Mulai Ulang Sesi")
          }
        }
      }
    } else {
      val currentCard = cards.getOrNull(currentIndex) ?: cards.first()
      val progress = (currentIndex + 1).toFloat() / cards.size.toFloat()

      // Progress info
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Kartu ${currentIndex + 1} dari ${cards.size}",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.primary
        )

        IconButton(
          onClick = { onToggleFavorite(currentCard.item.id) },
          modifier = Modifier.size(32.dp)
        ) {
          Icon(
            imageVector = if (currentCard.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorit",
            tint = if (currentCard.isFavorite) JapaneseCrimson else MaterialTheme.colorScheme.onSurfaceVariant
          )
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

      Spacer(modifier = Modifier.height(16.dp))

      // 3D Flippable Card
      val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "flashcard_flip"
      )

      Card(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .graphicsLayer {
            rotationY = rotation
            cameraDistance = 12f * density
          }
          .clickable { onFlipCard() }
          .testTag("flashcard_main_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
      ) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
        ) {
          if (rotation <= 90f) {
            // FRONT OF CARD
            Column(
              modifier = Modifier.fillMaxSize(),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.SpaceBetween
            ) {
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer
              ) {
                Text(
                  text = currentCard.item.category.titleId,
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onPrimaryContainer,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }

              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                  text = currentCard.item.kanji,
                  style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 42.sp
                  ),
                  color = MaterialTheme.colorScheme.onSurface,
                  textAlign = TextAlign.Center
                )

                if (currentCard.item.kanji != currentCard.item.kana) {
                  Spacer(modifier = Modifier.height(6.dp))
                  Text(
                    text = currentCard.item.kana,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                  )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = currentCard.item.romaji,
                  style = MaterialTheme.typography.bodyLarge,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                  shape = CircleShape,
                  color = MaterialTheme.colorScheme.primaryContainer,
                  modifier = Modifier
                    .size(46.dp)
                    .clickable { onSpeak(currentCard.item.kana) }
                ) {
                  Box(contentAlignment = Alignment.Center) {
                    Icon(
                      Icons.Default.VolumeUp,
                      contentDescription = "Putar Suara",
                      tint = MaterialTheme.colorScheme.primary
                    )
                  }
                }
              }

              Text(
                text = "👆 Ketuk kartu untuk melihat arti",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
              )
            }
          } else {
            // BACK OF CARD (Mirrored graphicLayer fix: rotationY 180f)
            Column(
              modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationY = 180f },
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.SpaceBetween
            ) {
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = SakuraPink.copy(alpha = 0.15f)
              ) {
                Text(
                  text = "Arti & Terjemahan",
                  style = MaterialTheme.typography.labelSmall,
                  color = SakuraPink,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  fontWeight = FontWeight.Bold
                )
              }

              Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text(
                  text = currentCard.item.meaningId,
                  style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                  color = MaterialTheme.colorScheme.onSurface,
                  textAlign = TextAlign.Center
                )

                Text(
                  text = "(${currentCard.item.meaningEn})",
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Example Sentence Card
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant,
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.CenterVertically
                    ) {
                      Text(
                        text = "Contoh Kalimat:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                      )
                      IconButton(
                        onClick = { onSpeak(currentCard.item.exampleJp) },
                        modifier = Modifier.size(24.dp)
                      ) {
                        Icon(
                          Icons.Default.VolumeUp,
                          contentDescription = "Audio Contoh",
                          tint = MaterialTheme.colorScheme.primary,
                          modifier = Modifier.size(16.dp)
                        )
                      }
                    }

                    Text(
                      text = currentCard.item.exampleJp,
                      style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                      color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                      text = currentCard.item.exampleRomaji,
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = "Artinya: ${currentCard.item.exampleMeaningId}",
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  }
                }
              }

              Text(
                text = "👆 Ketuk kartu untuk membalik kembali",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Bottom Action Controls
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        if (currentIndex > 0) {
          OutlinedButton(
            onClick = onPreviousCard,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.testTag("flashcard_prev_btn")
          ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Sebelumnya")
          }
        }

        Button(
          onClick = { onMarkCard(false) },
          modifier = Modifier
            .weight(1f)
            .testTag("flashcard_repeat_btn"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
          )
        ) {
          Icon(Icons.Default.Close, contentDescription = null)
          Spacer(modifier = Modifier.width(6.dp))
          Text("Belum Paham")
        }

        Button(
          onClick = { onMarkCard(true) },
          modifier = Modifier
            .weight(1f)
            .testTag("flashcard_mastered_btn"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MatchaGreen,
            contentColor = Color.White
          )
        ) {
          Icon(Icons.Default.Check, contentDescription = null)
          Spacer(modifier = Modifier.width(6.dp))
          Text("Sudah Hafal!")
        }
      }
    }
  }
}
