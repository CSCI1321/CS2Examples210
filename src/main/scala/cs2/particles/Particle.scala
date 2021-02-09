package cs2.particles

import cs2.util.Vec2
import scalafx.scene.paint.Color
import scalafx.scene.canvas.GraphicsContext

class Particle(var pos:Vec2, var vel:Vec2) {
    var col:Color = Color.OrangeRed
    var rad = 10.0

    def display(g:GraphicsContext):Unit = {
        g.setFill(col)
        g.fillOval(pos.x - rad, pos.y - rad, rad*2,rad*2)
    }
    def timeStep():Unit = {
        pos += vel
    }

}