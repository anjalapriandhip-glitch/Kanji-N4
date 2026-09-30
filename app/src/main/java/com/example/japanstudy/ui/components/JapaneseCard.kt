package com.example.japanstudy.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.japanstudy.data.KanaItem
import com.example.japanstudy.data.repository.WordWithProgress
import com.example.ui.theme.JapaneseCrimson
import com.example.ui.theme.MatchaGreen

@Composable
fun WordListItemCard(
  word: WordWithProgress,
  onSpeak: (String) -> Unit,
  onToggleFavorite: (Int) -> Unit,
  onToggleMastered: (Int) -> Unit,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .testTag("word_card_${word.item.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Kanji / Kana prominent badge
      Box(
        modifier = Modifier
          .size(54.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = if (word.item.kanji.length <= 3) word.item.kanji else word.item.kanji.take(2),
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 20.sp
          )
        )
      }

      Spacer(modifier = Modifier.width(14.dp))

      // Word details
      Column(
        modifier = Modifier.weight(1f)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = word.item.kanji,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          if (word.item.kanji != word.item.kana) {
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "(${word.item.kana})",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.primary
            )
          }
        }

        Text(
          text = word.item.romaji,
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = word.item.meaningId,
          style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      // Actions: Speak, Favorite, Mastered
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = { onSpeak(word.item.kana) },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.VolumeUp,
            contentDescription = "Dengarkan pelafalan",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
          )
        }

        IconButton(
          onClick = { onToggleFavorite(word.item.id) },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = if (word.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorit",
            tint = if (word.isFavorite) JapaneseCrimson else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
          )
        }

        IconButton(
          onClick = { onToggleMastered(word.item.id) },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = if (word.isMastered) Icons.Default.CheckCircle else Icons.Outlined.CheckCircle,
            contentDescription = "Dikuasai",
            tint = if (word.isMastered) MatchaGreen else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.size(20.dp)
          )
        }
      }
    }
  }
}

@Composable
fun WordDetailDialog(
  word: WordWithProgress,
  onDismiss: () -> Unit,
  onSpeak: (String) -> Unit,
  onToggleFavorite: (Int) -> Unit,
  onToggleMastered: (Int) -> Unit
) {
  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(24.dp),
      color = MaterialTheme.colorScheme.surface,
      tonalElevation = 6.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        // Header with close button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer
          ) {
            Text(
              text = word.item.category.titleId,
              style = MaterialTheme.typography.labelMedium,
              color = MaterialTheme.colorScheme.onPrimaryContainer,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Tutup")
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Large Japanese Display
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = word.item.kanji,
            style = MaterialTheme.typography.headlineLarge.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 36.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )

          if (word.item.kanji != word.item.kana) {
            Text(
              text = word.item.kana,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.primary
            )
          }

          Text(
            text = word.item.romaji,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Spacer(modifier = Modifier.height(12.dp))

          // Speaker Audio Button
          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
              .clickable { onSpeak(word.item.kana) }
              .size(48.dp)
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

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        // Meanings
        Text(
          text = "Arti (Bahasa Indonesia):",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
          text = word.item.meaningId,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
          color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "Meaning (English):",
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
          text = word.item.meaningEn,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Example Sentence
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Contoh Kalimat (例文):",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
              )
              IconButton(
                onClick = { onSpeak(word.item.exampleJp) },
                modifier = Modifier.size(24.dp)
              ) {
                Icon(
                  Icons.Default.VolumeUp,
                  contentDescription = "Dengarkan contoh",
                  modifier = Modifier.size(16.dp),
                  tint = MaterialTheme.colorScheme.primary
                )
              }
            }
            Text(
              text = word.item.exampleJp,
              style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
              text = word.item.exampleRomaji,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "Artinya: ${word.item.exampleMeaningId}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Actions (Favorite & Mastered Toggles)
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (word.isFavorite) JapaneseCrimson.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
              .clickable { onToggleFavorite(word.item.id) }
              .padding(4.dp)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                if (word.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = if (word.isFavorite) JapaneseCrimson else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (word.isFavorite) "Favorit" else "+ Favorit",
                style = MaterialTheme.typography.labelMedium,
                color = if (word.isFavorite) JapaneseCrimson else MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (word.isMastered) MatchaGreen.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
              .clickable { onToggleMastered(word.item.id) }
              .padding(4.dp)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                if (word.isMastered) Icons.Default.CheckCircle else Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = if (word.isMastered) MatchaGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (word.isMastered) "Dikuasai" else "Tandai Dikuasai",
                style = MaterialTheme.typography.labelMedium,
                color = if (word.isMastered) MatchaGreen else MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun KanaDetailDialog(
  kana: KanaItem,
  onDismiss: () -> Unit,
  onSpeak: (String) -> Unit
) {
  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(24.dp),
      color = MaterialTheme.colorScheme.surface,
      tonalElevation = 6.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = if (kana.type.name == "HIRAGANA") "Hiragana (ひらがな)" else "Katakana (カタカナ)",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
          )
          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Tutup")
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Giant Kana Character
        Text(
          text = kana.character,
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 64.sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = "/ ${kana.romaji} /",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
          color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Audio Button
        Surface(
          shape = CircleShape,
          color = MaterialTheme.colorScheme.primaryContainer,
          modifier = Modifier
            .clickable { onSpeak(kana.character) }
            .size(52.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              Icons.Default.VolumeUp,
              contentDescription = "Dengarkan pelafalan",
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(28.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Example word
        if (kana.exampleWord.isNotEmpty()) {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
          ) {
            Column(
              modifier = Modifier.padding(14.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "Contoh Kata:",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = kana.exampleWord,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = "Artinya: ${kana.exampleMeaning}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }
  }
}
