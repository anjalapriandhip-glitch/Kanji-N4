package com.example.japanstudy.data

enum class VocabularyCategory(val titleId: String, val kanjiTitle: String) {
  ALL("Semua", "全部"),
  GREETINGS("Salam & Frasa", "挨拶"),
  NUMBERS("Angka & Hitungan", "数字"),
  FOOD("Makanan & Minuman", "食べ物"),
  TRAVEL("Perjalanan & Tempat", "旅行"),
  FAMILY("Keluarga & Orang", "家族"),
  VERBS("Kata Kerja", "動詞"),
  ADJECTIVES("Kata Sifat", "形容詞"),
  TIME("Waktu & Hari", "時間"),
  DAILY("Keseharian", "日常")
}

data class VocabularyItem(
  val id: Int,
  val kanji: String,
  val kana: String,
  val romaji: String,
  val meaningId: String,
  val meaningEn: String,
  val category: VocabularyCategory,
  val jlptLevel: String = "N5",
  val exampleJp: String,
  val exampleRomaji: String,
  val exampleMeaningId: String
)

enum class KanaType { HIRAGANA, KATAKANA }
enum class KanaGroup { BASIC, DAKUON, HANDAKUON, YOON }

data class KanaItem(
  val character: String,
  val romaji: String,
  val type: KanaType,
  val group: KanaGroup = KanaGroup.BASIC,
  val row: String = "",
  val exampleWord: String = "",
  val exampleReading: String = "",
  val exampleMeaning: String = ""
)

object VocabularyData {

  val vocabularyList: List<VocabularyItem> = listOf(
    // --- GREETINGS (Aisatsu) ---
    VocabularyItem(
      id = 1,
      kanji = "おはようございます",
      kana = "おはようございます",
      romaji = "Ohayou gozaimasu",
      meaningId = "Selamat pagi (sopan)",
      meaningEn = "Good morning (polite)",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "先生、おはようございます。",
      exampleRomaji = "Sensei, ohayou gozaimasu.",
      exampleMeaningId = "Guru, selamat pagi."
    ),
    VocabularyItem(
      id = 2,
      kanji = "こんにちは",
      kana = "こんにちは",
      romaji = "Konnichiwa",
      meaningId = "Halo / Selamat siang",
      meaningEn = "Hello / Good afternoon",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "田中さん、こんにちは！",
      exampleRomaji = "Tanaka-san, konnichiwa!",
      exampleMeaningId = "Tanaka-san, halo!"
    ),
    VocabularyItem(
      id = 3,
      kanji = "こんばんは",
      kana = "こんばんは",
      romaji = "Konbanwa",
      meaningId = "Selamat malam",
      meaningEn = "Good evening",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "皆さん、こんばんは。",
      exampleRomaji = "Minasan, konbanwa.",
      exampleMeaningId = "Semuanya, selamat malam."
    ),
    VocabularyItem(
      id = 4,
      kanji = "ありがとうございます",
      kana = "ありがとうございます",
      romaji = "Arigatou gozaimasu",
      meaningId = "Terima kasih banyak",
      meaningEn = "Thank you very much",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "手伝ってくれてありがとうございます。",
      exampleRomaji = "Tetsudatte kurete arigatou gozaimasu.",
      exampleMeaningId = "Terima kasih banyak telah membantu saya."
    ),
    VocabularyItem(
      id = 5,
      kanji = "すみません",
      kana = "すみません",
      romaji = "Sumimasen",
      meaningId = "Permisi / Maaf",
      meaningEn = "Excuse me / Sorry",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "すみません、駅はどこですか？",
      exampleRomaji = "Sumimasen, eki wa doko desu ka?",
      exampleMeaningId = "Permisi, stasiun ada di mana?"
    ),
    VocabularyItem(
      id = 6,
      kanji = "はじめまして",
      kana = "はじめまして",
      romaji = "Hajimemashite",
      meaningId = "Senang berkenalan (pertama kali)",
      meaningEn = "Nice to meet you (first time)",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "はじめまして、アディと申します。",
      exampleRomaji = "Hajimemashite, Adi to moushimasu.",
      exampleMeaningId = "Senang berkenalan, nama saya Adi."
    ),
    VocabularyItem(
      id = 7,
      kanji = "よろしくお願(ねが)いします",
      kana = "よろしくおねがいします",
      romaji = "Yoroshiku onegaishimasu",
      meaningId = "Mohon bimbingannya / Senang bekerja sama",
      meaningEn = "Please treat me well / Looking forward to working with you",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "これからよろしくお願いします。",
      exampleRomaji = "Kore kara yoroshiku onegaishimasu.",
      exampleMeaningId = "Mulai sekarang mohon kerja samanya."
    ),
    VocabularyItem(
      id = 8,
      kanji = "さようなら",
      kana = "さようなら",
      romaji = "Sayounara",
      meaningId = "Selamat tinggal",
      meaningEn = "Goodbye",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "それでは、さようなら。",
      exampleRomaji = "Soredewa, sayounara.",
      exampleMeaningId = "Kalau begitu, selamat tinggal."
    ),
    VocabularyItem(
      id = 9,
      kanji = "ごめんなさい",
      kana = "ごめんなさい",
      romaji = "Gomennasai",
      meaningId = "Mohon maaf / Minta maaf",
      meaningEn = "I am sorry",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "遅れてごめんなさい。",
      exampleRomaji = "Okurete gomennasai.",
      exampleMeaningId = "Maaf saya terlambat."
    ),
    VocabularyItem(
      id = 10,
      kanji = "いただきます",
      kana = "いただきます",
      romaji = "Itadakimasu",
      meaningId = "Selamat makan (sebelum makan)",
      meaningEn = "Let's eat / Bon appétit",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "美味しそうなご飯、いただきます！",
      exampleRomaji = "Oishisou na gohan, itadakimasu!",
      exampleMeaningId = "Makanannya kelihatannya enak, selamat makan!"
    ),
    VocabularyItem(
      id = 11,
      kanji = "ごちそうさまでした",
      kana = "ごちそうさまでした",
      romaji = "Gochisousama deshita",
      meaningId = "Terima kasih atas hidangannya (setelah makan)",
      meaningEn = "Thank you for the meal",
      category = VocabularyCategory.GREETINGS,
      exampleJp = "とても美味(おい)しかったです。ごちそうさまでした。",
      exampleRomaji = "Totemo oishikatta desu. Gochisousama deshita.",
      exampleMeaningId = "Tadi sangat lezat. Terima kasih atas makanannya."
    ),

    // --- NUMBERS (Suuji) ---
    VocabularyItem(
      id = 12,
      kanji = "一",
      kana = "いち",
      romaji = "Ichi",
      meaningId = "Satu (1)",
      meaningEn = "One (1)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "りんごを一つください。",
      exampleRomaji = "Ringo o hitotsu kudasai.",
      exampleMeaningId = "Tolong beri saya satu apel."
    ),
    VocabularyItem(
      id = 13,
      kanji = "二",
      kana = "に",
      romaji = "Ni",
      meaningId = "Dua (2)",
      meaningEn = "Two (2)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "二人で行きましょう。",
      exampleRomaji = "Futari de ikimashou.",
      exampleMeaningId = "Ayo pergi berdua."
    ),
    VocabularyItem(
      id = 14,
      kanji = "三",
      kana = "さん",
      romaji = "San",
      meaningId = "Tiga (3)",
      meaningEn = "Three (3)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "三時にお茶を飲みます。",
      exampleRomaji = "San-ji ni ocha o nomimasu.",
      exampleMeaningId = "Jam tiga saya minum teh."
    ),
    VocabularyItem(
      id = 15,
      kanji = "四",
      kana = "よん / し",
      romaji = "Yon / Shi",
      meaningId = "Empat (4)",
      meaningEn = "Four (4)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "四月に日本へ行きます。",
      exampleRomaji = "Shigatsu ni Nihon e ikimasu.",
      exampleMeaningId = "Pada bulan April saya akan pergi ke Jepang."
    ),
    VocabularyItem(
      id = 16,
      kanji = "五",
      kana = "ご",
      romaji = "Go",
      meaningId = "Lima (5)",
      meaningEn = "Five (5)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "五分待ってください。",
      exampleRomaji = "Gofun matte kudasai.",
      exampleMeaningId = "Tolong tunggu lima menit."
    ),
    VocabularyItem(
      id = 17,
      kanji = "六",
      kana = "ろく",
      romaji = "Roku",
      meaningId = "Enam (6)",
      meaningEn = "Six (6)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "朝六時に起きます。",
      exampleRomaji = "Asa roku-ji ni okimasu.",
      exampleMeaningId = "Saya bangun jam enam pagi."
    ),
    VocabularyItem(
      id = 18,
      kanji = "七",
      kana = "なな / しち",
      romaji = "Nana / Shichi",
      meaningId = "Tujuh (7)",
      meaningEn = "Seven (7)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "七つの海。",
      exampleRomaji = "Nanatsu no umi.",
      exampleMeaningId = "Tujuh lautan."
    ),
    VocabularyItem(
      id = 19,
      kanji = "八",
      kana = "はち",
      romaji = "Hachi",
      meaningId = "Delapan (8)",
      meaningEn = "Eight (8)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "八月に祭りがあります。",
      exampleRomaji = "Hachigatsu ni matsuri ga arimasu.",
      exampleMeaningId = "Bulan Agustus ada festival."
    ),
    VocabularyItem(
      id = 20,
      kanji = "九",
      kana = "きゅう / く",
      romaji = "Kyuu / Ku",
      meaningId = "Sembilan (9)",
      meaningEn = "Nine (9)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "九時に寝ます。",
      exampleRomaji = "Kuji ni nemasu.",
      exampleMeaningId = "Saya tidur jam sembilan."
    ),
    VocabularyItem(
      id = 21,
      kanji = "十",
      kana = "じゅう",
      romaji = "Juu",
      meaningId = "Sepuluh (10)",
      meaningEn = "Ten (10)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "十本のペンがあります。",
      exampleRomaji = "Juppon no pen ga arimasu.",
      exampleMeaningId = "Ada sepuluh pulpen."
    ),
    VocabularyItem(
      id = 22,
      kanji = "百",
      kana = "ひゃく",
      romaji = "Hyaku",
      meaningId = "Seratus (100)",
      meaningEn = "Hundred (100)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "これは百円です。",
      exampleRomaji = "Kore wa hyaku-en desu.",
      exampleMeaningId = "Ini seratus yen."
    ),
    VocabularyItem(
      id = 23,
      kanji = "千",
      kana = "せん",
      romaji = "Sen",
      meaningId = "Seribu (1000)",
      meaningEn = "Thousand (1000)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "千円札を出します。",
      exampleRomaji = "Sen-en satsu o dashimasu.",
      exampleMeaningId = "Mengeluarkan uang seribu yen."
    ),
    VocabularyItem(
      id = 24,
      kanji = "万",
      kana = "まん",
      romaji = "Man",
      meaningId = "Sepuluh ribu (10.000)",
      meaningEn = "Ten thousand (10,000)",
      category = VocabularyCategory.NUMBERS,
      exampleJp = "一万円は高いですね。",
      exampleRomaji = "Ichiman-en wa takai desu ne.",
      exampleMeaningId = "Satu man yen (10.000 yen) mahal ya."
    ),

    // --- FOOD (Tabemono) ---
    VocabularyItem(
      id = 25,
      kanji = "水",
      kana = "みず",
      romaji = "Mizu",
      meaningId = "Air putih",
      meaningEn = "Water",
      category = VocabularyCategory.FOOD,
      exampleJp = "冷たい水を飲みたいです。",
      exampleRomaji = "Tsumetai mizu o nomitai desu.",
      exampleMeaningId = "Saya ingin minum air dingin."
    ),
    VocabularyItem(
      id = 26,
      kanji = "お茶",
      kana = "おちゃ",
      romaji = "Ocha",
      meaningId = "Teh Jepang / Teh hijau",
      meaningEn = "Japanese green tea",
      category = VocabularyCategory.FOOD,
      exampleJp = "温かいお茶はいかがですか？",
      exampleRomaji = "Atatakai ocha wa ikaga desu ka?",
      exampleMeaningId = "Bagaimana kalau minum teh hangat?"
    ),
    VocabularyItem(
      id = 27,
      kanji = "ご飯",
      kana = "ごはん",
      romaji = "Gohan",
      meaningId = "Nasi / Makanan",
      meaningEn = "Cooked rice / Meal",
      category = VocabularyCategory.FOOD,
      exampleJp = "朝ご飯を食べましたか？",
      exampleRomaji = "Asagohan o tabemashita ka?",
      exampleMeaningId = "Apakah kamu sudah sarapan pagi?"
    ),
    VocabularyItem(
      id = 28,
      kanji = "魚",
      kana = "さかな",
      romaji = "Sakana",
      meaningId = "Ikan",
      meaningEn = "Fish",
      category = VocabularyCategory.FOOD,
      exampleJp = "新鮮な魚が好きです。",
      exampleRomaji = "Shinsen na sakana ga suki desu.",
      exampleMeaningId = "Saya suka ikan segar."
    ),
    VocabularyItem(
      id = 29,
      kanji = "肉",
      kana = "にく",
      romaji = "Niku",
      meaningId = "Daging",
      meaningEn = "Meat",
      category = VocabularyCategory.FOOD,
      exampleJp = "牛肉の料理を作ります。",
      exampleRomaji = "Gyuuniku no ryouri o tsukurimasu.",
      exampleMeaningId = "Membuat masakan daging sapi."
    ),
    VocabularyItem(
      id = 30,
      kanji = "野菜",
      kana = "やさい",
      romaji = "Yasai",
      meaningId = "Sayuran",
      meaningEn = "Vegetables",
      category = VocabularyCategory.FOOD,
      exampleJp = "毎日野菜を食べます。",
      exampleRomaji = "Mainichi yasai o tabemasu.",
      exampleMeaningId = "Setiap hari saya makan sayur."
    ),
    VocabularyItem(
      id = 31,
      kanji = "寿司",
      kana = "すし",
      romaji = "Sushi",
      meaningId = "Sushi",
      meaningEn = "Sushi",
      category = VocabularyCategory.FOOD,
      exampleJp = "日本の寿司は最高です。",
      exampleRomaji = "Nihon no sushi wa saikou desu.",
      exampleMeaningId = "Sushi Jepang sangat luar biasa."
    ),
    VocabularyItem(
      id = 32,
      kanji = "ラーメン",
      kana = "ラーメン",
      romaji = "Raamen",
      meaningId = "Ramen (mie kuah)",
      meaningEn = "Ramen noodles",
      category = VocabularyCategory.FOOD,
      exampleJp = "豚骨ラーメンを食べました。",
      exampleRomaji = "Tonkotsu raamen o tabemashita.",
      exampleMeaningId = "Saya makan tonkotsu ramen."
    ),

    // --- TRAVEL (Ryokou) ---
    VocabularyItem(
      id = 33,
      kanji = "駅",
      kana = "えき",
      romaji = "Eki",
      meaningId = "Stasiun kereta",
      meaningEn = "Train station",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "駅の前で待ち合わせしましょう。",
      exampleRomaji = "Eki no mae de machiawase shimashou.",
      exampleMeaningId = "Mari bertemu di depan stasiun."
    ),
    VocabularyItem(
      id = 34,
      kanji = "電車",
      kana = "でんしゃ",
      romaji = "Densha",
      meaningId = "Kereta listrik",
      meaningEn = "Train",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "電車で東京へ行きます。",
      exampleRomaji = "Densha de Toukyou e ikimasu.",
      exampleMeaningId = "Pergi ke Tokyo naik kereta."
    ),
    VocabularyItem(
      id = 35,
      kanji = "空港",
      kana = "くうこう",
      romaji = "Kuukou",
      meaningId = "Bandara",
      meaningEn = "Airport",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "成田空港に着きました。",
      exampleRomaji = "Narita kuukou ni tsukimashita.",
      exampleMeaningId = "Sudah tiba di Bandara Narita."
    ),
    VocabularyItem(
      id = 36,
      kanji = "切符",
      kana = "きっぷ",
      romaji = "Kippu",
      meaningId = "Tiket",
      meaningEn = "Ticket",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "切符を二枚買いました。",
      exampleRomaji = "Kippu o nimai kaimashita.",
      exampleMeaningId = "Saya membeli dua lembar tiket."
    ),
    VocabularyItem(
      id = 37,
      kanji = "ホテル",
      kana = "ホテル",
      romaji = "Hoteru",
      meaningId = "Hotel",
      meaningEn = "Hotel",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "ホテルを予約しました。",
      exampleRomaji = "Hoteru o yoyaku shimashita.",
      exampleMeaningId = "Saya sudah memesan hotel."
    ),
    VocabularyItem(
      id = 38,
      kanji = "右",
      kana = "みぎ",
      romaji = "Migi",
      meaningId = "Kanan",
      meaningEn = "Right",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "次の角を右に曲がってください。",
      exampleRomaji = "Tsugi no kado o migi ni magatte kudasai.",
      exampleMeaningId = "Tolong belok ke kanan di sudut berikutnya."
    ),
    VocabularyItem(
      id = 39,
      kanji = "左",
      kana = "ひだり",
      romaji = "Hidari",
      meaningId = "Kiri",
      meaningEn = "Left",
      category = VocabularyCategory.TRAVEL,
      exampleJp = "銀行は左側にあります。",
      exampleRomaji = "Ginkou wa hidarigawa ni arimasu.",
      exampleMeaningId = "Bank ada di sebelah kiri."
    ),

    // --- FAMILY & PEOPLE (Kazoku) ---
    VocabularyItem(
      id = 40,
      kanji = "私",
      kana = "わたし",
      romaji = "Watashi",
      meaningId = "Saya / Aku",
      meaningEn = "I / Me",
      category = VocabularyCategory.FAMILY,
      exampleJp = "私は学生です。",
      exampleRomaji = "Watashi wa gakusei desu.",
      exampleMeaningId = "Saya adalah seorang siswa."
    ),
    VocabularyItem(
      id = 41,
      kanji = "友達",
      kana = "ともだち",
      romaji = "Tomodachi",
      meaningId = "Teman / Sahabat",
      meaningEn = "Friend",
      category = VocabularyCategory.FAMILY,
      exampleJp = "友達と映画を見ました。",
      exampleRomaji = "Tomodachi to eiga o mimashita.",
      exampleMeaningId = "Saya menonton film bersama teman."
    ),
    VocabularyItem(
      id = 42,
      kanji = "先生",
      kana = "せんせい",
      romaji = "Sensei",
      meaningId = "Guru / Dosen",
      meaningEn = "Teacher / Doctor",
      category = VocabularyCategory.FAMILY,
      exampleJp = "日本語の先生は親切です。",
      exampleRomaji = "Nihongo no sensei wa shinsetsu desu.",
      exampleMeaningId = "Guru bahasa Jepang sangat ramah."
    ),
    VocabularyItem(
      id = 43,
      kanji = "家族",
      kana = "かぞく",
      romaji = "Kazoku",
      meaningId = "Keluarga",
      meaningEn = "Family",
      category = VocabularyCategory.FAMILY,
      exampleJp = "私の家族は四人です。",
      exampleRomaji = "Watashi no kazoku wa yonin desu.",
      exampleMeaningId = "Keluarga saya ada empat orang."
    ),
    VocabularyItem(
      id = 44,
      kanji = "お父さん",
      kana = "おとうさん",
      romaji = "Otousan",
      meaningId = "Ayah",
      meaningEn = "Father",
      category = VocabularyCategory.FAMILY,
      exampleJp = "お父さんは会社員です。",
      exampleRomaji = "Otousan wa kaishain desu.",
      exampleMeaningId = "Ayah adalah pegawai kantor."
    ),
    VocabularyItem(
      id = 45,
      kanji = "お母さん",
      kana = "おかあさん",
      romaji = "Okaasan",
      meaningId = "Ibu",
      meaningEn = "Mother",
      category = VocabularyCategory.FAMILY,
      exampleJp = "お母さんの料理が好きです。",
      exampleRomaji = "Okaasan no ryouri ga suki desu.",
      exampleMeaningId = "Saya suka masakan ibu."
    ),

    // --- VERBS (Doushi) ---
    VocabularyItem(
      id = 46,
      kanji = "食(た)べる",
      kana = "たべる",
      romaji = "Taberu",
      meaningId = "Makan",
      meaningEn = "To eat",
      category = VocabularyCategory.VERBS,
      exampleJp = "一緒にご飯を食べましょう。",
      exampleRomaji = "Issho ni gohan o tabemashou.",
      exampleMeaningId = "Ayo makan bersama."
    ),
    VocabularyItem(
      id = 47,
      kanji = "飲(の)む",
      kana = "のむ",
      romaji = "Nomu",
      meaningId = "Minum",
      meaningEn = "To drink",
      category = VocabularyCategory.VERBS,
      exampleJp = "コーヒーを飲みます。",
      exampleRomaji = "Koohii o nomimasu.",
      exampleMeaningId = "Saya minum kopi."
    ),
    VocabularyItem(
      id = 48,
      kanji = "行(い)く",
      kana = "いく",
      romaji = "Iku",
      meaningId = "Pergi",
      meaningEn = "To go",
      category = VocabularyCategory.VERBS,
      exampleJp = "明日学校へ行きます。",
      exampleRomaji = "Ashita gakkou e ikimasu.",
      exampleMeaningId = "Besok saya pergi ke sekolah."
    ),
    VocabularyItem(
      id = 49,
      kanji = "来(く)る",
      kana = "くる",
      romaji = "Kuru",
      meaningId = "Datang",
      meaningEn = "To come",
      category = VocabularyCategory.VERBS,
      exampleJp = "友人が日本へ来ました。",
      exampleRomaji = "Yuujin ga Nihon e kimashita.",
      exampleMeaningId = "Teman sudah datang ke Jepang."
    ),
    VocabularyItem(
      id = 50,
      kanji = "見(み)る",
      kana = "みる",
      romaji = "Miru",
      meaningId = "Melihat / Menonton",
      meaningEn = "To see / To watch",
      category = VocabularyCategory.VERBS,
      exampleJp = "アニメを見るのが好きです。",
      exampleRomaji = "Anime o miru no ga suki desu.",
      exampleMeaningId = "Saya suka menonton anime."
    ),
    VocabularyItem(
      id = 51,
      kanji = "聞(き)く",
      kana = "きく",
      romaji = "Kiku",
      meaningId = "Mendengar / Bertanya",
      meaningEn = "To hear / To listen / To ask",
      category = VocabularyCategory.VERBS,
      exampleJp = "日本の音楽を聞きます。",
      exampleRomaji = "Nihon no ongaku o kikimasu.",
      exampleMeaningId = "Saya mendengarkan musik Jepang."
    ),
    VocabularyItem(
      id = 52,
      kanji = "話(はな)す",
      kana = "はなす",
      romaji = "Hanasu",
      meaningId = "Berbicara",
      meaningEn = "To speak",
      category = VocabularyCategory.VERBS,
      exampleJp = "日本語で話しましょう。",
      exampleRomaji = "Nihongo de hanashimashou.",
      exampleMeaningId = "Mari berbicara dalam bahasa Jepang."
    ),
    VocabularyItem(
      id = 53,
      kanji = "勉強(べんきょう)する",
      kana = "べんきょうする",
      romaji = "Benkyou suru",
      meaningId = "Belajar",
      meaningEn = "To study",
      category = VocabularyCategory.VERBS,
      exampleJp = "毎日漢字を勉強します。",
      exampleRomaji = "Mainichi kanji o benkyou shimasu.",
      exampleMeaningId = "Setiap hari saya belajar kanji."
    ),
    VocabularyItem(
      id = 54,
      kanji = "買(か)う",
      kana = "かう",
      romaji = "Kau",
      meaningId = "Membeli",
      meaningEn = "To buy",
      category = VocabularyCategory.VERBS,
      exampleJp = "コンビニでお土産を買いました。",
      exampleRomaji = "Konbini de omiyage o kaimashita.",
      exampleMeaningId = "Saya membeli oleh-oleh di minimarket."
    ),

    // --- ADJECTIVES (Keiyoushi) ---
    VocabularyItem(
      id = 55,
      kanji = "大(おお)きい",
      kana = "おおきい",
      romaji = "Ookii",
      meaningId = "Besar",
      meaningEn = "Big / Large",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "東京は大きな都市です。",
      exampleRomaji = "Toukyou wa ookina toshi desu.",
      exampleMeaningId = "Tokyo adalah kota yang besar."
    ),
    VocabularyItem(
      id = 56,
      kanji = "小(ちい)さい",
      kana = "ちいさい",
      romaji = "Chiisai",
      meaningId = "Kecil",
      meaningEn = "Small",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "小さくて可愛い猫ですね。",
      exampleRomaji = "Chiisakute kawaii neko desu ne.",
      exampleMeaningId = "Kucing yang kecil dan lucu ya."
    ),
    VocabularyItem(
      id = 57,
      kanji = "美味(おい)しい",
      kana = "おいしい",
      romaji = "Oishii",
      meaningId = "Enak / Lezat",
      meaningEn = "Delicious / Tasty",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "この料理はとても美味しいです。",
      exampleRomaji = "Kono ryouri wa totemo oishii desu.",
      exampleMeaningId = "Masakan ini sangat enak."
    ),
    VocabularyItem(
      id = 58,
      kanji = "楽(たの)しい",
      kana = "たのしい",
      romaji = "Tanoshii",
      meaningId = "Menyenangkan",
      meaningEn = "Fun / Enjoyable",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "日本語の勉強は楽しいです！",
      exampleRomaji = "Nihongo no benkyou wa tanoshii desu!",
      exampleMeaningId = "Belajar bahasa Jepang itu menyenangkan!"
    ),
    VocabularyItem(
      id = 59,
      kanji = "高(たか)い",
      kana = "たかい",
      romaji = "Takai",
      meaningId = "Tinggi / Mahal",
      meaningEn = "High / Expensive",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "富士山は高い山です。",
      exampleRomaji = "Fujisan wa takai yama desu.",
      exampleMeaningId = "Gunung Fuji adalah gunung yang tinggi."
    ),
    VocabularyItem(
      id = 60,
      kanji = "安(やす)い",
      kana = "やすい",
      romaji = "Yasui",
      meaningId = "Murah",
      meaningEn = "Cheap / Inexpensive",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "この店の商品は安いです。",
      exampleRomaji = "Kono mise no shouhin wa yasui desu.",
      exampleMeaningId = "Barang di toko ini murah."
    ),
    VocabularyItem(
      id = 61,
      kanji = "新(あたら)しい",
      kana = "あたらしい",
      romaji = "Atarashii",
      meaningId = "Baru",
      meaningEn = "New",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "新しい本を買いました。",
      exampleRomaji = "Atarashii hon o kaimashita.",
      exampleMeaningId = "Saya membeli buku baru."
    ),
    VocabularyItem(
      id = 62,
      kanji = "綺麗(きれい)",
      kana = "きれい",
      romaji = "Kirei",
      meaningId = "Cantik / Bersih / Indah",
      meaningEn = "Pretty / Clean / Beautiful",
      category = VocabularyCategory.ADJECTIVES,
      exampleJp = "桜の花はとても綺麗です。",
      exampleRomaji = "Sakura no hana wa totemo kirei desu.",
      exampleMeaningId = "Bunga sakura sangat indah."
    ),

    // --- TIME & DAYS (Jikan) ---
    VocabularyItem(
      id = 63,
      kanji = "今日",
      kana = "きょう",
      romaji = "Kyou",
      meaningId = "Hari ini",
      meaningEn = "Today",
      category = VocabularyCategory.TIME,
      exampleJp = "今日はいい天気ですね。",
      exampleRomaji = "Kyou wa ii tenki desu ne.",
      exampleMeaningId = "Hari ini cuacanya bagus ya."
    ),
    VocabularyItem(
      id = 64,
      kanji = "明日",
      kana = "あした",
      romaji = "Ashita",
      meaningId = "Besok",
      meaningEn = "Tomorrow",
      category = VocabularyCategory.TIME,
      exampleJp = "また明日会いましょう。",
      exampleRomaji = "Mata ashita aimashou.",
      exampleMeaningId = "Sampai jumpa lagi besok."
    ),
    VocabularyItem(
      id = 65,
      kanji = "昨日",
      kana = "きのう",
      romaji = "Kinou",
      meaningId = "Kemarin",
      meaningEn = "Yesterday",
      category = VocabularyCategory.TIME,
      exampleJp = "昨日は忙しかったです。",
      exampleRomaji = "Kinou wa isogashikatta desu.",
      exampleMeaningId = "Kemarin saya sangat sibuk."
    ),
    VocabularyItem(
      id = 66,
      kanji = "月曜日",
      kana = "げつようび",
      romaji = "Getsuyoubi",
      meaningId = "Hari Senin",
      meaningEn = "Monday",
      category = VocabularyCategory.TIME,
      exampleJp = "月曜日からテストです。",
      exampleRomaji = "Getsuyoubi kara tesuto desu.",
      exampleMeaningId = "Mulai Senin ada ujian."
    ),
    VocabularyItem(
      id = 67,
      kanji = "日曜日",
      kana = "にちようび",
      romaji = "Nichiyoubi",
      meaningId = "Hari Minggu",
      meaningEn = "Sunday",
      category = VocabularyCategory.TIME,
      exampleJp = "日曜日は休みです。",
      exampleRomaji = "Nichiyoubi wa yasumi desu.",
      exampleMeaningId = "Hari Minggu libur."
    ),
    VocabularyItem(
      id = 68,
      kanji = "今",
      kana = "いま",
      romaji = "Ima",
      meaningId = "Sekarang",
      meaningEn = "Now",
      category = VocabularyCategory.TIME,
      exampleJp = "今何時ですか？",
      exampleRomaji = "Ima nanji desu ka?",
      exampleMeaningId = "Sekarang jam berapa?"
    ),

    // --- DAILY LIFE (Seikatsu) ---
    VocabularyItem(
      id = 69,
      kanji = "本",
      kana = "ほん",
      romaji = "Hon",
      meaningId = "Buku",
      meaningEn = "Book",
      category = VocabularyCategory.DAILY,
      exampleJp = "図書館で本を借りました。",
      exampleRomaji = "Toshokan de hon o karimashita.",
      exampleMeaningId = "Saya meminjam buku di perpustakaan."
    ),
    VocabularyItem(
      id = 70,
      kanji = "車",
      kana = "くるま",
      romaji = "Kuruma",
      meaningId = "Mobil",
      meaningEn = "Car",
      category = VocabularyCategory.DAILY,
      exampleJp = "新しい車を運転します。",
      exampleRomaji = "Atarashii kuruma o unten shimasu.",
      exampleMeaningId = "Mengemudikan mobil baru."
    ),
    VocabularyItem(
      id = 71,
      kanji = "家",
      kana = "いえ / うち",
      romaji = "Ie / Uchi",
      meaningId = "Rumah",
      meaningEn = "House / Home",
      category = VocabularyCategory.DAILY,
      exampleJp = "家に帰ります。",
      exampleRomaji = "Ie ni kaerimasu.",
      exampleMeaningId = "Saya pulang ke rumah."
    ),
    VocabularyItem(
      id = 72,
      kanji = "学校",
      kana = "がっこう",
      romaji = "Gakkou",
      meaningId = "Sekolah",
      meaningEn = "School",
      category = VocabularyCategory.DAILY,
      exampleJp = "学校は駅から近いです。",
      exampleRomaji = "Gakkou wa eki kara chikai desu.",
      exampleMeaningId = "Sekolah dekat dari stasiun."
    )
  )

  // Full Hiragana Gojūon (46 basic characters)
  val hiraganaBasicList: List<KanaItem> = listOf(
    // A-row
    KanaItem("あ", "a", KanaType.HIRAGANA, row = "A", exampleWord = "あさ (asa)", exampleReading = "あさ", exampleMeaning = "Pagi"),
    KanaItem("い", "i", KanaType.HIRAGANA, row = "A", exampleWord = "いぬ (inu)", exampleReading = "いぬ", exampleMeaning = "Anjing"),
    KanaItem("う", "u", KanaType.HIRAGANA, row = "A", exampleWord = "うみ (umi)", exampleReading = "うみ", exampleMeaning = "Laut"),
    KanaItem("え", "e", KanaType.HIRAGANA, row = "A", exampleWord = "えき (eki)", exampleReading = "えき", exampleMeaning = "Stasiun"),
    KanaItem("お", "o", KanaType.HIRAGANA, row = "A", exampleWord = "おちゃ (ocha)", exampleReading = "おちゃ", exampleMeaning = "Teh"),

    // Ka-row
    KanaItem("か", "ka", KanaType.HIRAGANA, row = "KA", exampleWord = "かさ (kasa)", exampleReading = "かさ", exampleMeaning = "Payung"),
    KanaItem("き", "ki", KanaType.HIRAGANA, row = "KA", exampleWord = "き (ki)", exampleReading = "き", exampleMeaning = "Pohon"),
    KanaItem("く", "ku", KanaType.HIRAGANA, row = "KA", exampleWord = "くるま (kuruma)", exampleReading = "くるま", exampleMeaning = "Mobil"),
    KanaItem("け", "ke", KanaType.HIRAGANA, row = "KA", exampleWord = "けいさつ (keisatsu)", exampleReading = "けいさつ", exampleMeaning = "Polisi"),
    KanaItem("こ", "ko", KanaType.HIRAGANA, row = "KA", exampleWord = "こども (kodomo)", exampleReading = "こども", exampleMeaning = "Anak-anak"),

    // Sa-row
    KanaItem("さ", "sa", KanaType.HIRAGANA, row = "SA", exampleWord = "さくら (sakura)", exampleReading = "さくら", exampleMeaning = "Bunga Sakura"),
    KanaItem("し", "shi", KanaType.HIRAGANA, row = "SA", exampleWord = "しんぶん (shinbun)", exampleReading = "しんぶん", exampleMeaning = "Koran"),
    KanaItem("す", "su", KanaType.HIRAGANA, row = "SA", exampleWord = "すし (sushi)", exampleReading = "すし", exampleMeaning = "Sushi"),
    KanaItem("せ", "se", KanaType.HIRAGANA, row = "SA", exampleWord = "せんせい (sensei)", exampleReading = "せんせい", exampleMeaning = "Guru"),
    KanaItem("そ", "so", KanaType.HIRAGANA, row = "SA", exampleWord = "そら (sora)", exampleReading = "そら", exampleMeaning = "Langit"),

    // Ta-row
    KanaItem("た", "ta", KanaType.HIRAGANA, row = "TA", exampleWord = "たまご (tamago)", exampleReading = "たまご", exampleMeaning = "Telur"),
    KanaItem("ち", "chi", KanaType.HIRAGANA, row = "TA", exampleWord = "ちず (chizu)", exampleReading = "ちず", exampleMeaning = "Peta"),
    KanaItem("つ", "tsu", KanaType.HIRAGANA, row = "TA", exampleWord = "つき (tsuki)", exampleReading = "つき", exampleMeaning = "Bulan"),
    KanaItem("て", "te", KanaType.HIRAGANA, row = "TA", exampleWord = "てがみ (tegami)", exampleReading = "てがみ", exampleMeaning = "Surat"),
    KanaItem("と", "to", KanaType.HIRAGANA, row = "TA", exampleWord = "とり (tori)", exampleReading = "とり", exampleMeaning = "Burung"),

    // Na-row
    KanaItem("な", "na", KanaType.HIRAGANA, row = "NA", exampleWord = "なつ (natsu)", exampleReading = "なつ", exampleMeaning = "Musim panas"),
    KanaItem("に", "ni", KanaType.HIRAGANA, row = "NA", exampleWord = "にほん (nihon)", exampleReading = "にほん", exampleMeaning = "Jepang"),
    KanaItem("ぬ", "nu", KanaType.HIRAGANA, row = "NA", exampleWord = "ぬいぐるみ (nuigurumi)", exampleReading = "ぬいぐるみ", exampleMeaning = "Boneka"),
    KanaItem("ね", "ne", KanaType.HIRAGANA, row = "NA", exampleWord = "ねこ (neko)", exampleReading = "ねこ", exampleMeaning = "Kucing"),
    KanaItem("の", "no", KanaType.HIRAGANA, row = "NA", exampleWord = "のみもの (nomimono)", exampleReading = "のみもの", exampleMeaning = "Minuman"),

    // Ha-row
    KanaItem("は", "ha", KanaType.HIRAGANA, row = "HA", exampleWord = "はな (hana)", exampleReading = "はな", exampleMeaning = "Bunga"),
    KanaItem("ひ", "hi", KanaType.HIRAGANA, row = "HA", exampleWord = "ひかり (hikari)", exampleReading = "ひかり", exampleMeaning = "Cahaya"),
    KanaItem("ふ", "fu", KanaType.HIRAGANA, row = "HA", exampleWord = "ふね (fune)", exampleReading = "ふね", exampleMeaning = "Kapal"),
    KanaItem("へ", "he", KanaType.HIRAGANA, row = "HA", exampleWord = "へや (heya)", exampleReading = "へや", exampleMeaning = "Kamar"),
    KanaItem("ほ", "ho", KanaType.HIRAGANA, row = "HA", exampleWord = "ほし (hoshi)", exampleReading = "ほし", exampleMeaning = "Bintang"),

    // Ma-row
    KanaItem("ま", "ma", KanaType.HIRAGANA, row = "MA", exampleWord = "まち (machi)", exampleReading = "まち", exampleMeaning = "Kota"),
    KanaItem("み", "mi", KanaType.HIRAGANA, row = "MA", exampleWord = "みず (mizu)", exampleReading = "みず", exampleMeaning = "Air"),
    KanaItem("む", "mu", KanaType.HIRAGANA, row = "MA", exampleWord = "むし (mushi)", exampleReading = "むし", exampleMeaning = "Serangga"),
    KanaItem("め", "me", KanaType.HIRAGANA, row = "MA", exampleWord = "めがね (megane)", exampleReading = "めがね", exampleMeaning = "Kacamata"),
    KanaItem("も", "mo", KanaType.HIRAGANA, row = "MA", exampleWord = "もり (mori)", exampleReading = "もり", exampleMeaning = "Hutan"),

    // Ya-row
    KanaItem("や", "ya", KanaType.HIRAGANA, row = "YA", exampleWord = "やま (yama)", exampleReading = "やま", exampleMeaning = "Gunung"),
    KanaItem("ゆ", "yu", KanaType.HIRAGANA, row = "YA", exampleWord = "ゆき (yuki)", exampleReading = "ゆき", exampleMeaning = "Salju"),
    KanaItem("よ", "yo", KanaType.HIRAGANA, row = "YA", exampleWord = "よる (yoru)", exampleReading = "よる", exampleMeaning = "Malam"),

    // Ra-row
    KanaItem("ら", "ra", KanaType.HIRAGANA, row = "RA", exampleWord = "らいおん (raion)", exampleReading = "らいおん", exampleMeaning = "Singa"),
    KanaItem("り", "ri", KanaType.HIRAGANA, row = "RA", exampleWord = "りんご (ringo)", exampleReading = "りんご", exampleMeaning = "Apel"),
    KanaItem("る", "ru", KanaType.HIRAGANA, row = "RA", exampleWord = "るす (rusu)", exampleReading = "るす", exampleMeaning = "Tidak ada di rumah"),
    KanaItem("れ", "re", KanaType.HIRAGANA, row = "RA", exampleWord = "れんしゅう (renshuu)", exampleReading = "れんしゅう", exampleMeaning = "Latihan"),
    KanaItem("ろ", "ro", KanaType.HIRAGANA, row = "RA", exampleWord = "ろうそく (rousoku)", exampleReading = "ろうそく", exampleMeaning = "Lilin"),

    // Wa-row & N
    KanaItem("わ", "wa", KanaType.HIRAGANA, row = "WA", exampleWord = "わたし (watashi)", exampleReading = "わたし", exampleMeaning = "Saya"),
    KanaItem("を", "wo / o", KanaType.HIRAGANA, row = "WA", exampleWord = "パンを食べる (o)", exampleReading = "を", exampleMeaning = "Partikel objek"),
    KanaItem("ん", "n", KanaType.HIRAGANA, row = "WA", exampleWord = "ほん (hon)", exampleReading = "ほん", exampleMeaning = "Buku")
  )

  // Full Katakana Gojūon (46 basic characters)
  val katakanaBasicList: List<KanaItem> = listOf(
    // A-row
    KanaItem("ア", "a", KanaType.KATAKANA, row = "A", exampleWord = "アイス (aisu)", exampleReading = "アイス", exampleMeaning = "Es krim"),
    KanaItem("イ", "i", KanaType.KATAKANA, row = "A", exampleWord = "インドネシア (indoneshia)", exampleReading = "インドネシア", exampleMeaning = "Indonesia"),
    KanaItem("ウ", "u", KanaType.KATAKANA, row = "A", exampleWord = "ウェブ (webu)", exampleReading = "ウェブ", exampleMeaning = "Web"),
    KanaItem("エ", "e", KanaType.KATAKANA, row = "A", exampleWord = "エレベーター (erebeetaa)", exampleReading = "エレベーター", exampleMeaning = "Lift / Elevator"),
    KanaItem("オ", "o", KanaType.KATAKANA, row = "A", exampleWord = "オレンジ (orenji)", exampleReading = "オレンジ", exampleMeaning = "Jeruk"),

    // Ka-row
    KanaItem("カ", "ka", KanaType.KATAKANA, row = "KA", exampleWord = "カメラ (kamera)", exampleReading = "カメラ", exampleMeaning = "Kamera"),
    KanaItem("キ", "ki", KanaType.KATAKANA, row = "KA", exampleWord = "キッチン (kicchin)", exampleReading = "キッチン", exampleMeaning = "Dapur"),
    KanaItem("ク", "ku", KanaType.KATAKANA, row = "KA", exampleWord = "クラス (kurasu)", exampleReading = "クラス", exampleMeaning = "Kelas"),
    KanaItem("ケ", "ke", KanaType.KATAKANA, row = "KA", exampleWord = "ケーキ (keeki)", exampleReading = "ケーキ", exampleMeaning = "Kue"),
    KanaItem("コ", "ko", KanaType.KATAKANA, row = "KA", exampleWord = "コーヒー (koohii)", exampleReading = "コーヒー", exampleMeaning = "Kopi"),

    // Sa-row
    KanaItem("サ", "sa", KanaType.KATAKANA, row = "SA", exampleWord = "サラダ (sarada)", exampleReading = "サラダ", exampleMeaning = "Salad"),
    KanaItem("シ", "shi", KanaType.KATAKANA, row = "SA", exampleWord = "シャツ (shatsu)", exampleReading = "シャツ", exampleMeaning = "Kemeja"),
    KanaItem("ス", "su", KanaType.KATAKANA, row = "SA", exampleWord = "スーパー (suupaa)", exampleReading = "スーパー", exampleMeaning = "Supermarket"),
    KanaItem("セ", "se", KanaType.KATAKANA, row = "SA", exampleWord = "セーター (seetaa)", exampleReading = "セーター", exampleMeaning = "Sweater"),
    KanaItem("ソ", "so", KanaType.KATAKANA, row = "SA", exampleWord = "ソファ (sofa)", exampleReading = "ソファ", exampleMeaning = "Sofa"),

    // Ta-row
    KanaItem("タ", "ta", KanaType.KATAKANA, row = "TA", exampleWord = "タクシー (takushii)", exampleReading = "タクシー", exampleMeaning = "Taksi"),
    KanaItem("チ", "chi", KanaType.KATAKANA, row = "TA", exampleWord = "チーズ (chiizu)", exampleReading = "チーズ", exampleMeaning = "Keju"),
    KanaItem("ツ", "tsu", KanaType.KATAKANA, row = "TA", exampleWord = "ツアー (tsuaa)", exampleReading = "ツアー", exampleMeaning = "Tur"),
    KanaItem("テ", "te", KanaType.KATAKANA, row = "TA", exampleWord = "テレビ (terebi)", exampleReading = "テレビ", exampleMeaning = "Televisi"),
    KanaItem("ト", "to", KanaType.KATAKANA, row = "TA", exampleWord = "トイレ (toire)", exampleReading = "トイレ", exampleMeaning = "Toilet"),

    // Na-row
    KanaItem("ナ", "na", KanaType.KATAKANA, row = "NA", exampleWord = "ナイフ (naifu)", exampleReading = "ナイフ", exampleMeaning = "Pisau"),
    KanaItem("ニ", "ni", KanaType.KATAKANA, row = "NA", exampleWord = "ニュース (nyuusu)", exampleReading = "ニュース", exampleMeaning = "Berita"),
    KanaItem("ヌ", "nu", KanaType.KATAKANA, row = "NA", exampleWord = "ヌードル (nuudoru)", exampleReading = "ヌードル", exampleMeaning = "Mie"),
    KanaItem("ネ", "ne", KanaType.KATAKANA, row = "NA", exampleWord = "ネクタイ (nekutai)", exampleReading = "ネクタイ", exampleMeaning = "Dasi"),
    KanaItem("ノ", "no", KanaType.KATAKANA, row = "NA", exampleWord = "ノート (nooto)", exampleReading = "ノート", exampleMeaning = "Buku catatan"),

    // Ha-row
    KanaItem("ハ", "ha", KanaType.KATAKANA, row = "HA", exampleWord = "ハンバーガー (hanbaagaa)", exampleReading = "ハンバーガー", exampleMeaning = "Hamburger"),
    KanaItem("ヒ", "hi", KanaType.KATAKANA, row = "HA", exampleWord = "ヒーター (hiitaa)", exampleReading = "ヒーター", exampleMeaning = "Pemanas ruangan"),
    KanaItem("フ", "fu", KanaType.KATAKANA, row = "HA", exampleWord = "フォーク (fooku)", exampleReading = "フォーク", exampleMeaning = "Garpu"),
    KanaItem("ヘ", "he", KanaType.KATAKANA, row = "HA", exampleWord = "ヘルメット (herumetto)", exampleReading = "ヘルメット", exampleMeaning = "Helm"),
    KanaItem("ホ", "ho", KanaType.KATAKANA, row = "HA", exampleWord = "ホテル (hoteru)", exampleReading = "ホテル", exampleMeaning = "Hotel"),

    // Ma-row
    KanaItem("マ", "ma", KanaType.KATAKANA, row = "MA", exampleWord = "マスク (masuku)", exampleReading = "マスク", exampleMeaning = "Masker"),
    KanaItem("ミ", "mi", KanaType.KATAKANA, row = "MA", exampleWord = "ミルク (miruku)", exampleReading = "ミルク", exampleMeaning = "Susu"),
    KanaItem("ム", "mu", KanaType.KATAKANA, row = "MA", exampleWord = "ムービー (muubii)", exampleReading = "ムービー", exampleMeaning = "Film"),
    KanaItem("メ", "me", KanaType.KATAKANA, row = "MA", exampleWord = "メニュー (menyuu)", exampleReading = "メニュー", exampleMeaning = "Menu"),
    KanaItem("モ", "mo", KanaType.KATAKANA, row = "MA", exampleWord = "モデル (moderu)", exampleReading = "モデル", exampleMeaning = "Model"),

    // Ya-row
    KanaItem("ヤ", "ya", KanaType.KATAKANA, row = "YA", exampleWord = "ヤング (yangu)", exampleReading = "ヤング", exampleMeaning = "Muda"),
    KanaItem("ユ", "yu", KanaType.KATAKANA, row = "YA", exampleWord = "ユーザー (yuuzaa)", exampleReading = "ユーザー", exampleMeaning = "Pengguna"),
    KanaItem("ヨ", "yo", KanaType.KATAKANA, row = "YA", exampleWord = "ヨーグルト (yooguruto)", exampleReading = "ヨーグルト", exampleMeaning = "Yogurt"),

    // Ra-row
    KanaItem("ラ", "ra", KanaType.KATAKANA, row = "RA", exampleWord = "ラジオ (rajio)", exampleReading = "ラジオ", exampleMeaning = "Radio"),
    KanaItem("リ", "ri", KanaType.KATAKANA, row = "RA", exampleWord = "リンゴ (ringo)", exampleReading = "リンゴ", exampleMeaning = "Apel"),
    KanaItem("ル", "ru", KanaType.KATAKANA, row = "RA", exampleWord = "ルール (ruuru)", exampleReading = "ルール", exampleMeaning = "Aturan"),
    KanaItem("レ", "re", KanaType.KATAKANA, row = "RA", exampleWord = "レストラン (resutoran)", exampleReading = "レストラン", exampleMeaning = "Restoran"),
    KanaItem("ロ", "ro", KanaType.KATAKANA, row = "RA", exampleWord = "ロボット (robotto)", exampleReading = "ロボット", exampleMeaning = "Robot"),

    // Wa-row & N
    KanaItem("ワ", "wa", KanaType.KATAKANA, row = "WA", exampleWord = "ワイン (wain)", exampleReading = "ワイン", exampleMeaning = "Anggur / Wine"),
    KanaItem("ヲ", "wo", KanaType.KATAKANA, row = "WA", exampleWord = "ヲ", exampleReading = "ヲ", exampleMeaning = "Karakter langka"),
    KanaItem("ン", "n", KanaType.KATAKANA, row = "WA", exampleWord = "パン (pan)", exampleReading = "パン", exampleMeaning = "Roti")
  )
}
