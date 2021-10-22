package mx.tecnm.tepic.ladm_u2_practica1_presentaciondiademuertos

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
class Copo {
    var x = 0f
    var y = 0f
    var tam = 0f

    init {
        x = (Math.random()*2100).toFloat()
        y = ((Math.random()*2500)*-1).toFloat()
        tam = ((Math.random()*5)+5).toFloat()
    }

    fun moverCopo(){
        y+= tam
        if(y>1950) y = ((Math.random()*2500)*-1).toFloat()
    }
    fun pintarCopo(c:Canvas){
        val p = Paint()
        p.color = Color.WHITE
        c.drawCircle(x,y,tam, p)
    }
}