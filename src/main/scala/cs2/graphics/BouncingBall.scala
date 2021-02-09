package cs2.graphics

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color
import scalafx.scene.input.MouseEvent
import scalafx.animation.AnimationTimer

object BouncingBall extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "Bouncing!"
        scene = new Scene(600,600) {
            val canvas = new Canvas(600,600)
            content = canvas
            val g = canvas.graphicsContext2D

            var posX = 300.0
            var posY = 300.0
            var rad = 50.0
            //0-1 ==> 0-10 ==> -5 to 5
            var velX = math.random() * 10 - 5
            var velY = math.random() * 10 - 5
            val timer = AnimationTimer(t => {
                g.setFill(Color.White)
                g.fillRect(0,0, 600,600)
                g.setFill(Color.OrangeRed)
                g.fillOval(posX-rad, posY-rad, rad*2,rad*2)
                posX += velX
                posY += velY
                if(posX + rad > 600 || posX - rad < 0) {
                    velX = -velX
                }
                if(posY + rad > 600 || posY - rad < 0) {
                    velY = -velY
                }
            })
            timer.start

        }
    }
}