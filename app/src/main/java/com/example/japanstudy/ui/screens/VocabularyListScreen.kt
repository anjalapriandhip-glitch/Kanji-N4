package com.example.japanstudy.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.japanstudy.data.VocabularyCategory
import com.example.japanstudy.data.repository.WordWithProgress
import com.example.japanstudy.ui.VocabFilterMode
import com.example.japanstudy.ui.components.WordListItemCard
import com.example.ui.theme.JapaneseCrimson

@Composable
fun VocabularyListScreen(
  words: List<WordWithProgress>,
  searchQuery: String,
  onSearchQueryChange: (String) -> Unit,
  selectedCategory: VocabularyCategory,
  onCategorySelect: (VocabularyCategory) -> Unit,
  selectedFilterMode: VocabFilterMode,
  onFilterModeSelect: (VocabFilterMode) -> Unit,
  onSpeak: (String) -> Unit,
  onToggleFavorite: (Int) -> Unit,
  onToggleMastered: (Int) -> Unit,
  onWordClick: (WordWithProgress) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxSize()
  ) {
    // Top Bar & Search Field
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      Text(
        text = "Daftar Kosakata (単語)",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = "Kosakata JLPT N5 Lengkap dengan Audio & Contoh",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(10.dp))

      OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("vocab_search_field"),
        placeholder = { Text("Cari Kanji, Kana, Romaji, atau Arti...") },
        leadingIcon = {
          Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
          )
        },
        trailingIcon = {
          if (searchQuery.isNotEmpty()) {
            IconButton(onClick = { onSearchQueryChange("") }) {
              Icon(Icons.Default.Clear, contentDescription = "Hapus pencarian")
            }
          }
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = MaterialTheme.colorScheme.primary,
          unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
        )
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Filter Mode Row (Semua, Favorit, Dikuasai, Belum)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        VocabFilterMode.values().forEach { mode ->
          val isSelected = selectedFilterMode == mode
          FilterChip(
            selected = isSelected,
            onClick = { onFilterModeSelect(mode) },
            label = { Text(mode.label) },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = JapaneseCrimson,
              selectedLabelColor = androidx.compose.ui.graphics.Color.White
            ),
            modifier = Modifier.testTag("filter_chip_${mode.name}")
          )
        }
      }
    }

    // Category Selector Bar
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(VocabularyCategory.values()) { category ->
        val isSelected = selectedCategory == category
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
          tonalElevation = if (isSelected) 3.dp else 1.dp,
          modifier = Modifier
            .clickable { onCategorySelect(category) }
            .testTag("vocab_cat_${category.name}")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = category.kanjiTitle,
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
              color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = category.titleId,
              style = MaterialTheme.typography.bodySmall,
              color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
            )
          }
        }
      }
    }

    // Results Counter
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Menampilkan ${words.size} kata",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }

    // Vocabulary Cards List
    if (words.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(32.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Icon(
            Icons.Default.SentimentDissatisfied,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Tidak ada kata yang sesuai",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Text(
            text = "Coba ubah kata kunci pencarian atau kategori filter.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
          )
        }
      }
    } else {
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        items(words, key = { it.item.id }) { word ->
          WordListItemCard(
            word = word,
            onSpeak = onSpeak,
            onToggleFavorite = onToggleFavorite,
            onToggleMastered = onToggleMastered,
            onClick = { onWordClick(word) }
          )
        }
      }
    }
  }
}
