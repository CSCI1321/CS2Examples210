package cs2.particles

import scalafx.scene.paint.Color

trait Rainbowness {
    private var hue:Double = 0

    protected def stepColor():Color = {
        hue += 4
        Color.hsb(hue, 1,1)
    }
}