import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.attributes.AttrsBuilder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.Element

fun main() {
    renderComposable(rootElementId = "root") {
        Div({ style { padding(25.px) } }) {
            Div({
                style(split, left)
            }) {
                Div({
                    style(center)
                }) {
                    content()
                }
            }
            Div({
                style(split, right)
            }) {

            }
        }
    }
}

private val center = makeStyle {
    position(Position.Absolute)
    top(50.percent)
    left(50.percent)
}

private val split = makeStyle {
    height(100.percent)
    width(50.percent)
    position(Position.Fixed)
    top(0.percent)
}

private val left = makeStyle {
    left(0.percent)
    backgroundColor("#00FF00")
}

private val right = makeStyle {
    right(0.percent)
    backgroundColor("#FF0000")
}

@Composable
fun content() {
    var count: Int by mutableStateOf(0)

    Button(attrs = {
        onClick { count -= 1 }
    }) {
        Text("-")
    }

    Span({ style { padding(15.px) } }) {
        Text("$count")
    }

    Button({
        onClick { count += 1 }
    }) {
        Text("+")
    }
}

fun makeStyle(function: StyleBuilder.() -> Unit): StyleBuilder.() -> Unit = function

fun <TElement : Element> AttrsBuilder<TElement>.style(vararg builder: StyleBuilder.() -> Unit) {
    builder.forEach(styleBuilder::apply)
}

fun <TElement : Element> AttrsBuilder<TElement>.style(
    vararg builder: StyleBuilder.() -> Unit,
    build: StyleBuilder.() -> Unit
) {
    builder.forEach(styleBuilder::apply)
    styleBuilder.apply(build)
}
