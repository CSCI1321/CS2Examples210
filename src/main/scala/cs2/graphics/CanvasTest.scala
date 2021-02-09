package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent

object CanvasTest extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Canvas!"
        scene = new Scene(600,400) {
            val canvas = new Canvas(600,400)
            content = canvas
            val g = canvas.graphicsContext2D
            /*
            g.setLineWidth(10)
            g.strokeRect(200,100, 200,150)
            g.setStroke(Color.HotPink)
            g.setFill(Color.GAINSBORO)
            g.fillOval(200,100, 200,150)
            g.setFill(Color.rgb(200,40,20))
            g.fillText("Hello there!", 200,100)
            g.strokeLine(0,0, 200,100)

            g.setLineWidth(1)
            for(x <- 0 to 600) {
                //I have numbers from 0 to 600; need numbers from 0 to 255
                //0-600 ==> 0-1 ==> 0-255
                g.setStroke(Color.rgb((x / 600.0 * 255).toInt, 0, 0))
                g.strokeLine(x,0, x,400)
            }
            */

            var prevX = 0.0
            var prevY = 0.0
            canvas.onMousePressed = (e:MouseEvent) => {
                prevX = e.x
                prevY = e.y
            }
            canvas.onMouseDragged = (e:MouseEvent) => {
                g.strokeLine(prevX,prevY, e.x,e.y)
                prevX = e.x
                prevY = e.y
            }

        }
    }
}