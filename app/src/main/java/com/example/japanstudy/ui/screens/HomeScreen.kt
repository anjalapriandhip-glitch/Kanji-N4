package com.example.japanstudy.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.japanstudy.data.VocabularyCategory
import com.example.japanstudy.data.repository.WordWithProgress
import com.example.japanstudy.ui.NavigationTab
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.JapaneseCrimson
import com.example.ui.theme.MatchaGreen
import com.example.ui.theme.SakuraPink

@Composable
fun HomeScreen(
  totalWordsCount: Int,
  masteredCount: Int,
  favoriteCount: Int,
  words: List<WordWithProgress>,
  onNavigateTab: (NavigationTab) -> Unit,
  onSelectCategory: (VocabularyCategory) -> Unit,
  onSpeak: (String) -> Unit,
  onWordClick: (WordWithProgress) -> Unit,
  modifier: Modifier = Modifier
) {
  val featuredWord = words.firstOrNull { it.item.id == 2 } ?: words.firstOrNull()

  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    // Top Hero Banner
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(210.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_study_banner),
          contentDescription = "Japan Landscape Banner",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        // Gradient overlay for readability
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color.Black.copy(alpha = 0.75f)
                )
              )
            )
        )

        // Banner text overlay
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(16.dp)
        ) {
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = JapaneseCrimson.copy(alpha = 0.9f)
          ) {
            Text(
              text = "日本語を勉強しましょう！",
              style = MaterialTheme.typography.labelMedium,
              color = Color.White,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = "Selamat Belajar Bahasa Jepang",
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )
          Text(
            text = "Kuasai Hiragana, Katakana, dan Kosakata JLPT N5",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White.copy(alpha = 0.9f))
          )
        }
      }
    }

    // Stats Overview Row
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        StatCard(
          title = "Total Kata",
          value = "$totalWordsCount",
          icon = Icons.Default.Book,
          iconColor = MaterialTheme.colorScheme.primary,
          modifier = Modifier.weight(1f)
        )
        StatCard(
          title = "Dikuasai",
          value = "$masteredCount",
          icon = Icons.Default.CheckCircle,
          iconColor = MatchaGreen,
          modifier = Modifier.weight(1f)
        )
        StatCard(
          title = "Favorit",
          value = "$favoriteCount",
          icon = Icons.Default.Favorite,
          iconColor = JapaneseCrimson,
          modifier = Modifier.weight(1f)
        )
      }
    }

    // Quick Action Shortcuts Grid
    item {
      Text(
        text = "Menu Pembelajaran",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
      )

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          ActionTile(
            title = "Daftar Kosakata",
            japaneseTitle = "単語リスト",
            subtitle = "70+ kosakata JLPT N5 lengkap",
            icon = Icons.Default.Translate,
            accentColor = JapaneseCrimson,
            onClick = { onNavigateTab(NavigationTab.VOCABULARY) },
            modifier = Modifier.weight(1f),
            testTag = "action_vocab"
          )
          ActionTile(
            title = "Latihan Flashcard",
            japaneseTitle = "フラッシュカード",
            subtitle = "Metode kartu bolak-balik",
            icon = Icons.Default.Style,
            accentColor = SakuraPink,
            onClick = { onNavigateTab(NavigationTab.FLASHCARDS) },
            modifier = Modifier.weight(1f),
            testTag = "action_flashcards"
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          ActionTile(
            title = "Kuis Ujian",
            japaneseTitle = "クイズテスト",
            subtitle = "Tebak arti & bacaan",
            icon = Icons.Default.Quiz,
            accentColor = GoldAccent,
            onClick = { onNavigateTab(NavigationTab.QUIZ) },
            modifier = Modifier.weight(1f),
            testTag = "action_quiz"
          )
          ActionTile(
            title = "Huruf Kana",
            japaneseTitle = "五十音図",
            subtitle = "Tabel Hiragana & Katakana",
            icon = Icons.Default.Psychology,
            accentColor = MatchaGreen,
            onClick = { onNavigateTab(NavigationTab.KANA) },
            modifier = Modifier.weight(1f),
            testTag = "action_kana"
          )
        }
      }
    }

    // Word of the Day (Kata Pilihan Hari Ini)
    if (featuredWord != null) {
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          text = "Kata Pilihan Hari Ini (今日の言葉)",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface,
          modifier = Modifier.padding(horizontal = 16.dp)
        )

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onWordClick(featuredWord) }
            .testTag("featured_word_card"),
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
          ),
          elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = SakuraPink.copy(alpha = 0.15f)
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = SakuraPink,
                    modifier = Modifier.size(14.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "Kosakata Populer",
                    style = MaterialTheme.typography.labelSmall,
                    color = SakuraPink,
                    fontWeight = FontWeight.SemiBold
                  )
                }
              }

              Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                  .size(36.dp)
                  .clickable { onSpeak(featuredWord.item.kana) }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    Icons.Default.VolumeUp,
                    contentDescription = "Dengarkan pelafalan",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = featuredWord.item.kanji,
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
            )

            Text(
              text = "${featuredWord.item.kana} • ${featuredWord.item.romaji}",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = "Arti: ${featuredWord.item.meaningId}",
              style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
              color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
              shape = RoundedCornerShape(10.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = "💬 \"${featuredWord.item.exampleJp}\" (${featuredWord.item.exampleMeaningId})",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(10.dp)
              )
            }
          }
        }
      }
    }

    // Categories Carousel
    item {
      Text(
        text = "Kategori Pembelajaran",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
      )

      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        val categories = VocabularyCategory.values().filter { it != VocabularyCategory.ALL }
        items(categories) { category ->
          Card(
            modifier = Modifier
              .clickable {
                onSelectCategory(category)
                onNavigateTab(NavigationTab.VOCABULARY)
              }
              .testTag("category_chip_${category.name}"),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
              containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Column(
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = category.kanjiTitle,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
              Text(
                text = category.titleId,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun StatCard(
  title: String,
  value: String,
  icon: ImageVector,
  iconColor: Color,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = iconColor,
        modifier = Modifier.size(24.dp)
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = value,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}

@Composable
private fun ActionTile(
  title: String,
  japaneseTitle: String,
  subtitle: String,
  icon: ImageVector,
  accentColor: Color,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  testTag: String
) {
  Card(
    modifier = modifier
      .clickable { onClick() }
      .testTag(testTag),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Box(
        modifier = Modifier
          .size(40.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(accentColor.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = accentColor,
          modifier = Modifier.size(22.dp)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = japaneseTitle,
        style = MaterialTheme.typography.labelSmall,
        color = accentColor,
        fontWeight = FontWeight.Bold
      )

      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1
      )
    }
  }
}
