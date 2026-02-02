package com.example.prayertime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prayertime.ui.theme.NightSky
import com.example.prayertime.ui.theme.PrayerTimesTheme
import com.example.prayertime.ui.theme.SunriseGold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrayerTimesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PrayerTimesScreen()
                }
            }
        }
    }
}

private val samplePrayerTimes = listOf(
    PrayerTime("الفجر", "05:12", isNext = true),
    PrayerTime("الشروق", "06:34"),
    PrayerTime("الظهر", "12:18"),
    PrayerTime("العصر", "15:42"),
    PrayerTime("المغرب", "18:06"),
    PrayerTime("العشاء", "19:25")
)

@Composable
fun PrayerTimesScreen(
    location: String = "الرياض، السعودية",
    hijriDate: String = "11 شعبان 1445",
    gregorianDate: String = "20 فبراير 2024"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(NightSky, Color(0xFF1F3A5F))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeaderSection(location = location, hijriDate = hijriDate, gregorianDate = gregorianDate)
            NextPrayerCard(prayer = samplePrayerTimes.first())
            PrayerTimesList(prayerTimes = samplePrayerTimes)
            NotificationSettingsCard(prayerTimes = samplePrayerTimes)
        }
    }
}

@Composable
private fun HeaderSection(location: String, hijriDate: String, gregorianDate: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0x33FFFFFF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = location, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "تنبيه مواقيت الصلاة", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            DateChip(label = hijriDate, backgroundColor = Color(0x22FFFFFF))
            DateChip(label = gregorianDate, backgroundColor = Color(0x22FFFFFF))
        }
    }
}

@Composable
private fun DateChip(label: String, backgroundColor: Color) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun NextPrayerCard(prayer: PrayerTime) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A4C7E))
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "الصلاة القادمة", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = prayer.name, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "بعد 01:12", color = SunriseGold, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = prayer.time, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun PrayerTimesList(prayerTimes: List<PrayerTime>) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF162A45))
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "مواقيت اليوم",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.height(260.dp)
            ) {
                items(prayerTimes) { prayer ->
                    PrayerTimeRow(prayer = prayer)
                }
            }
        }
    }
}

@Composable
private fun NotificationSettingsCard(prayerTimes: List<PrayerTime>) {
    val initialSettings = prayerTimes.map { it.name to true }
    val reminderStates = remember { initialSettings.map { mutableStateOf(it) } }

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A3254))
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "تنبيهات الأذان",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            reminderStates.forEachIndexed { index, state ->
                val prayer = prayerTimes[index]
                NotificationRow(
                    prayerName = prayer.name,
                    time = prayer.time,
                    isEnabled = state.value.second,
                    onToggle = { enabled -> state.value = prayer.name to enabled }
                )
            }
        }
    }
}

@Composable
private fun NotificationRow(
    prayerName: String,
    time: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x22FFFFFF), RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = prayerName, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "تنبيه عند $time", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
        }
        Switch(
            checked = isEnabled,
            onCheckedChange = onToggle,
            thumbContent = null
        )
    }
}

@Composable
private fun PrayerTimeRow(prayer: PrayerTime) {
    val rowBackground = if (prayer.isNext) Color(0x33FFFFFF) else Color.Transparent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(rowBackground, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = prayer.name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Text(
            text = prayer.time,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
}

data class PrayerTime(
    val name: String,
    val time: String,
    val isNext: Boolean = false
)
