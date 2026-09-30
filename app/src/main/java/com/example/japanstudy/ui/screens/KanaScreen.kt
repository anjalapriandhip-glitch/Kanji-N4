package com.example.japanstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.japanstudy.data.KanaItem
import com.example.japanstudy.data.KanaType
import com.example.ui.theme.JapaneseCrimson

@Composable
fun KanaScreen(
  hiraganaList: List<KanaItem>,
  katakanaList: List<KanaItem>,
  onSpeak: (String) -> Unit,
  onKanaClick: (KanaItem) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedTab by remember { mutableStateOf(KanaType.HIRAGANA) }
  val activeList = if (selectedTab == KanaType.HIRAGANA) hiraganaList else katakanaList

  Column(
    modifier = modifier.fillMaxSize()
  ) {
    // Top Bar
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      Text(
        text = "Tabel Huruf Kana (五十音図)",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = "Kuasai 46 huruf dasar Hiragana & Katakana beserta pelafalannya",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(12.dp))

      TabRow(
        selectedTabIndex = if (selectedTab == KanaType.HIRAGANA) 0 else 1,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = JapaneseCrimson
      ) {
        Tab(
          selected = selectedTab == KanaType.HIRAGANA,
          onClick = { selectedTab = KanaType.HIRAGANA },
          text = {
            Text(
              "Hiragana (ひらがな)",
              fontWeight = if (selectedTab == KanaType.HIRAGANA) FontWeight.Bold else FontWeight.Normal
            )
          },
          modifier = Modifier.testTag("tab_hiragana")
        )
        Tab(
          selected = selectedTab == KanaType.KATAKANA,
          onClick = { selectedTab = KanaType.KATAKANA },
          text = {
            Text(
              "Katakana (カタカナ)",
              fontWeight = if (selectedTab == KanaType.KATAKANA) FontWeight.Bold else FontWeight.Normal
            )
          },
          modifier = Modifier.testTag("tab_katakana")
        )
      }
    }

    // Grid of Kana Tiles (5 columns representing A, I, U, E, O)
    LazyVerticalGrid(
      columns = GridCells.Fixed(5),
      contentPadding = PaddingValues(12.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      items(activeList) { kana ->
        KanaGridTile(
          kana = kana,
          onSpeak = onSpeak,
          onClick = { onKanaClick(kana) }
        )
      }
    }
  }
}

@Composable
private fun KanaGridTile(
  kana: KanaItem,
  onSpeak: (String) -> Unit,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .aspectRatio(0.9f)
      .clickable { onClick() }
      .testTag("kana_tile_${kana.romaji}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(4.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = kana.character,
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 24.sp
        ),
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = kana.romaji,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Medium
      )

      Icon(
        imageVector = Icons.Default.VolumeUp,
        contentDescription = "Suara",
        modifier = Modifier
          .size(14.dp)
          .clickable { onSpeak(kana.character) },
        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
      )
    }
  }
}
