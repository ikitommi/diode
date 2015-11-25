package example


import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._
import org.scalajs.dom._

@JSExport("SimpleApp")
object SimpleApp extends JSApp {
  // create a view for the counter
  val counter = new CounterView(AppCircuit.zoom(_.counter), AppCircuit)

  @JSExport
  override def main(): Unit = {
    val root = document.getElementById("root")
    // subscribe to changes in the application model and call render when anything changes
    AppCircuit.subscribe(() => render(root))
    // start the application by dispatching a Reset action
    AppCircuit(Reset)
  }

  def render(root: Element) = {
    val e = div(
      h1("Diode example"),
      counter.render // renders the counter view
    ).render
    // clear and update contents
    root.innerHTML = ""
    root.appendChild(e)
  }
}
