package cs2.particles

import cs2.util.Vec2

class RainbowParticleSystem(o:Vec2) extends ParticleSystem(o) {
    override def addParticle(): Unit = {
        particles ::= new RainbowParticle(origin.clone, new Vec2(math.random()*4-2, math.random()*4-2))
    }
}