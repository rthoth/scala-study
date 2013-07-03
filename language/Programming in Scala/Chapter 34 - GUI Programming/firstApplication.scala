import scala.swing._
import event._

object FirstSwingApp extends SimpleSwingApplication {
	def top = new MainFrame {
		title = "First Swing App"

		contents = new Button {
			text = "Click me"
		}
	}
}

object SecondSwingApp extends SimpleSwingApplication {
	def top = new MainFrame {
		title = "Second Swing App"

		val button = Button("Click me") {
			println("Button clicked!")
		}

		var label = new Label("No button clicks registered")

		contents = new BoxPanel(Orientation.Vertical) {
			/*contents += button
			contents += label*/

			contents ++= Seq(button, label)

			border = Swing.EmptyBorder(30, 30, 10, 30)
			var nClicks = 0;

			listenTo(button)

			reactions += {
				case ButtonClicked(b) =>
					nClicks += 1
					label.text  = "Number of button clicks: " + nClicks
			}
		}
	}
}