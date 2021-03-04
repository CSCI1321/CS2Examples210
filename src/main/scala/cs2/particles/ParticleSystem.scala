package cs2.particles

import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scala.collection.mutable.Buffer

class ParticleSystem(val origin:Vec2) {
    protected var particles:Buffer[Particle] = Buffer()

    def addParticle():Unit = {
        if(math.random() < 0.5) {
            particles += new RoundParticle(origin.clone, new Vec2(math.random()*4-2, math.random()*4-2))    
        } else {
            particles += new SquareParticle(Vec2(origin), new Vec2(math.random()*4-2, math.random()*4-2))
        }
    }
    def display(g:GraphicsContext):Unit = {
        for(p <- particles) {
            p.display(g)
        }
        //println(particles.length)
    }
    def timeStep():Unit = {
        particles.foreach((p:Particle) => p.timeStep())
        /* This is bad because you shouldn't change the size of
           a collection during a for loop over it
        for(p <- particles) {
            if(p.isOffScreen) {
                particles -= p
            }
        }*/
        /* This works, but you may accidentally create an infinite loop
        var i = 0
        while(i < particles.length) {
            if(particles(i).isOffScreen) {
                particles.remove(i)
            } else {
                i += 1
            }
        }*/
        particles = particles.filterNot((p:Particle) => p.isOffScreen)
    }
    def applyForce(force:Vec2):Unit = {
        for(p <- particles) {
            p.applyForce(force)
        }
    }

}