package dev.chhen.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window

private val Background = Color(0xFF0B0F0D)
private val Surface = Color(0xFF111713)
private val SurfaceHigh = Color(0xFF161D19)
private val Border = Color(0xFF2A342E)
private val Text = Color(0xFFEFF2ED)
private val Muted = Color(0xFF9DA7A0)
private val Accent = Color(0xFFB9F66D)
private val Mono = FontFamily.Monospace

private data class Project(val index: String, val category: String, val title: String, val description: String, val tags: List<String>)

private val projects = listOf(
    Project("01", "Multi-module platform", "Android Foundation", "A reusable foundation for Android products, structured around common models, networking, HTTP diagnostics, and clean module boundaries.", listOf("Kotlin", "Gradle", "Clean Architecture")),
    Project("02", "Android security library", "Secure Preferences", "Encrypted DataStore preferences with Android Keystore protection, authenticated encryption, serialization, and support back to API 21.", listOf("DataStore", "AES-GCM", "Keystore")),
    Project("03", "Pure Kotlin library", "Core Masking", "A dependency-free masking toolkit for sensitive values and recursive JSON fields, designed for safe logging across applications.", listOf("Kotlin/JVM", "PII Safety", "Library API")),
    Project("04", "Gradle convention plugin", "Publishing Toolchain", "Consistent local and Nexus publishing for Android and Kotlin libraries, including provenance metadata, release notes, and mapping artifacts.", listOf("Gradle Kotlin DSL", "Maven", "CI/CD")),
)

@Composable
fun App() {
    MaterialTheme(colorScheme = darkColorScheme(primary = Accent, background = Background, surface = Surface, onBackground = Text, onSurface = Text)) {
        BoxWithConstraints(Modifier.fillMaxSize().background(Background)) {
            val compact = maxWidth < 760.dp
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
                Navigation(compact)
                Hero(compact)
                Metrics(compact)
                SectionHeader("SELECTED SYSTEMS", "Reusable work, built for real constraints.", "Focused examples from security, infrastructure, and developer tooling.")
                ProjectGrid(compact)
                Experience()
                Toolkit()
                Contact(compact)
                Footer(compact)
            }
        }
    }
}

@Composable
private fun Page(content: @Composable RowScope.() -> Unit) {
    Row(Modifier.fillMaxWidth().widthIn(max = 1180.dp).padding(horizontal = 24.dp), content = content)
}

@Composable
private fun Navigation(compact: Boolean) = Page {
    Row(Modifier.fillMaxWidth().height(84.dp).border(1.dp, Border.copy(alpha = .6f)).padding(horizontal = 18.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.size(36.dp).border(1.dp, Muted), contentAlignment = Alignment.Center) { Text("C", color = Accent, fontFamily = Mono, fontWeight = FontWeight.Bold) }
            Text("dev.chhen", fontFamily = Mono, fontWeight = FontWeight.Bold)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(if (compact) 14.dp else 30.dp)) {
            Text("Work", color = Muted, fontSize = 14.sp)
            if (!compact) Text("Experience", color = Muted, fontSize = 14.sp)
            Text("Contact", color = Muted, fontSize = 14.sp)
        }
    }
}

@Composable
private fun Hero(compact: Boolean) = Page {
    if (compact) Column(Modifier.fillMaxWidth().padding(vertical = 64.dp), verticalArrangement = Arrangement.spacedBy(42.dp)) {
        HeroCopy(); CodeCard()
    } else Row(Modifier.fillMaxWidth().padding(vertical = 96.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(64.dp)) {
        Box(Modifier.weight(1.2f)) { HeroCopy() }
        Box(Modifier.weight(.8f)) { CodeCard() }
    }
}

@Composable
private fun HeroCopy() = Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
    Label("ANDROID DEVELOPER · PHNOM PENH")
    Text("I build Android systems that stay clear as they scale.", fontSize = 56.sp, lineHeight = 58.sp, fontWeight = FontWeight.Bold, letterSpacing = (-2).sp)
    Text("I’m Chhen, an Android developer focused on Kotlin, Jetpack Compose, reusable foundations, and the engineering details that make mobile products reliable.", color = Muted, fontSize = 18.sp, lineHeight = 30.sp)
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Action("Selected work ↘", primary = true) {}
        Action("GitHub ↗") { window.open("https://github.com/thornchhen", "_blank") }
    }
}

@Composable
private fun CodeCard() = Column(Modifier.fillMaxWidth().background(SurfaceHigh).border(1.dp, Border)) {
    Row(Modifier.fillMaxWidth().height(46.dp).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("●  ●  ●", color = Muted.copy(alpha = .5f), fontSize = 11.sp)
        Spacer(Modifier.weight(1f)); Text("profile.kt", color = Muted, fontFamily = Mono, fontSize = 12.sp)
    }
    Text("""data class Developer(
  val name = "Chhen",
  val focus = "Android",
  val craft = listOf(
    "Architecture",
    "Security",
    "Developer Experience"
  )
)""", Modifier.fillMaxWidth().border(1.dp, Border.copy(alpha = .7f)).padding(26.dp), color = Accent, fontFamily = Mono, fontSize = 14.sp, lineHeight = 24.sp)
    Text("●  Building reliable mobile foundations", Modifier.padding(16.dp), color = Muted, fontFamily = Mono, fontSize = 12.sp)
}

@Composable
private fun Metrics(compact: Boolean) = Page {
    val items = listOf("4+" to "years building Android", "API 21+" to "compatibility experience", "End-to-end" to "architecture to delivery")
    if (compact) Column(Modifier.fillMaxWidth().border(1.dp, Border)) { items.forEach { Metric(it.first, it.second) } }
    else Row(Modifier.fillMaxWidth().border(1.dp, Border)) { items.forEach { Box(Modifier.weight(1f)) { Metric(it.first, it.second) } } }
}

@Composable
private fun Metric(value: String, label: String) = Column(Modifier.fillMaxWidth().height(126.dp).border(1.dp, Border.copy(alpha = .6f)).padding(28.dp), verticalArrangement = Arrangement.Center) {
    Text(value, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Text(label, color = Muted, fontSize = 14.sp)
}

@Composable
private fun SectionHeader(kicker: String, title: String, description: String) = Page {
    Column(Modifier.fillMaxWidth().padding(top = 120.dp, bottom = 48.dp)) {
        Label(kicker); Spacer(Modifier.height(16.dp))
        Text(title, fontSize = 44.sp, lineHeight = 48.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(14.dp)); Text(description, color = Muted, fontSize = 17.sp)
    }
}

@Composable
private fun ProjectGrid(compact: Boolean) = Page {
    if (compact) Column(Modifier.fillMaxWidth()) { projects.forEach { ProjectCard(it) } }
    else Column(Modifier.fillMaxWidth()) { projects.chunked(2).forEach { row -> Row(Modifier.fillMaxWidth()) { row.forEach { Box(Modifier.weight(1f)) { ProjectCard(it) } } } } }
}

@Composable
private fun ProjectCard(project: Project) = Column(Modifier.fillMaxWidth().heightIn(min = 350.dp).background(Surface).border(1.dp, Border).padding(30.dp)) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(project.index + " / 04", color = Muted, fontFamily = Mono, fontSize = 12.sp)
        Text("{ }", color = Accent, fontFamily = Mono)
    }
    Spacer(Modifier.height(42.dp)); Label(project.category.uppercase()); Spacer(Modifier.height(10.dp))
    Text(project.title, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(12.dp)); Text(project.description, color = Muted, lineHeight = 24.sp)
    Spacer(Modifier.weight(1f))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        project.tags.forEach { Text(it, Modifier.border(1.dp, Border).padding(horizontal = 8.dp, vertical = 5.dp), color = Muted, fontFamily = Mono, fontSize = 10.sp) }
    }
}

@Composable
private fun Experience() {
    SectionHeader("EXPERIENCE", "Product thinking with platform depth.", "From user-facing features to the shared foundations behind them.")
    Page {
        Column(Modifier.fillMaxWidth().padding(bottom = 110.dp)) {
            Role("2023 — PRESENT", "Senior Android Developer", "Wing Bank", "Building Android capabilities for financial products, with a focus on reusable platform libraries, architecture, security, analytics, logging, and delivery workflows.")
            Role("2022 — 2023", "Android Engineer", "KOSIGN Investment", "Developed Android applications using Kotlin and Java, translating product requirements into maintainable mobile experiences.")
            Role("2022 — 2023", "Computer Science & Android Training", "University of Cambodia · HRD Center", "Earned a BSc in Computer Science and completed basic through advanced Android development training.")
        }
    }
}

@Composable
private fun Role(date: String, role: String, company: String, description: String) = Column(Modifier.fillMaxWidth().border(1.dp, Border.copy(alpha = .7f)).padding(28.dp)) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(role.uppercase(), color = Accent, fontFamily = Mono, fontSize = 12.sp)
        Text(date, color = Accent, fontFamily = Mono, fontSize = 12.sp)
    }
    Spacer(Modifier.height(10.dp)); Text(company, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp)); Text(description, color = Muted, lineHeight = 25.sp)
}

@Composable
private fun Toolkit() {
    SectionHeader("TOOLKIT", "Tools I use to move from idea to dependable release.", "A practical stack spanning product UI, platform architecture, and delivery.")
    val items = listOf(
        "Android" to "Kotlin · Java · Jetpack Compose · XML",
        "Architecture" to "MVVM · Clean Architecture · Multi-module",
        "Async & data" to "Coroutines · Flow · Room · DataStore · Paging 3",
        "Networking" to "Retrofit · OkHttp · REST · HTTP diagnostics",
        "Platform" to "Hilt · Koin · Firebase · Android Keystore",
        "Delivery" to "Gradle · GitLab CI/CD · GitHub Actions · Maven/Nexus",
    )
    Page {
        Column(Modifier.fillMaxWidth().padding(bottom = 120.dp)) {
            items.forEachIndexed { index, item ->
                Row(Modifier.fillMaxWidth().border(1.dp, Border.copy(alpha = .65f)).padding(22.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("0" + (index + 1), Modifier.width(54.dp), color = Muted, fontFamily = Mono, fontSize = 12.sp)
                    Text(item.first, Modifier.width(170.dp), fontWeight = FontWeight.Bold)
                    Text(item.second, Modifier.weight(1f), color = Muted)
                }
            }
        }
    }
}

@Composable
private fun Contact(compact: Boolean) = Page {
    val modifier = Modifier.fillMaxWidth().background(Accent).padding(if (compact) 34.dp else 64.dp)
    if (compact) Column(modifier, verticalArrangement = Arrangement.spacedBy(28.dp)) { ContactCopy(); ContactLink() }
    else Row(modifier, verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(70.dp)) {
        Box(Modifier.weight(1.2f)) { ContactCopy() }; Box(Modifier.weight(.8f)) { ContactLink() }
    }
}

@Composable
private fun ContactCopy() = Column {
    Text("CONTACT", color = Color(0xFF354523), fontFamily = Mono, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    Spacer(Modifier.height(14.dp))
    Text("Let’s build Android software that lasts.", color = Background, fontSize = 42.sp, lineHeight = 46.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(14.dp)); Text("Based in Phnom Penh, Cambodia.", color = Color(0xFF3E4A38))
}

@Composable
private fun ContactLink() = Column(Modifier.fillMaxWidth().background(Color(0xFFC7FB87)).border(1.dp, Color(0xFF789C4D)).clickable { window.open("https://github.com/thornchhen", "_blank") }.padding(18.dp)) {
    Text("GitHub", color = Background, fontWeight = FontWeight.Bold)
    Text("github.com/thornchhen ↗", color = Color(0xFF4E603A), fontSize = 13.sp)
}

@Composable
private fun Footer(compact: Boolean) = Page {
    Row(Modifier.fillMaxWidth().height(130.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text("dev.chhen", fontFamily = Mono, fontWeight = FontWeight.Bold)
        if (!compact) Text("Built with Kotlin & Compose Multiplatform.", color = Muted, fontSize = 13.sp)
        Text("CMP / WASM", color = Accent, fontFamily = Mono, fontSize = 12.sp)
    }
}

@Composable
private fun Label(text: String) = Text(text, color = Accent, fontFamily = Mono, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.sp)

@Composable
private fun Action(text: String, primary: Boolean = false, onClick: () -> Unit) {
    Box(Modifier.background(if (primary) Accent else Surface).border(1.dp, if (primary) Accent else Border).clickable(onClick = onClick).padding(horizontal = 18.dp, vertical = 14.dp)) {
        Text(text, color = if (primary) Background else Text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
