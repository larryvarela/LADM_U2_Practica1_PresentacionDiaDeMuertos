package mx.tecnm.tepic.ladm_u2_practica1_presentaciondiademuertos

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.View

class Lienzo (p:MainActivity): View(p){
        val principal = p
        val catrina =  BitmapFactory.decodeResource(principal.resources,R.drawable.icono3)
        var posX = 1450f
    var posX1 = 1450f
    var posX2 = 1550f
    var posX3 = 1650f

    var posX4 = 100f
    var posX5 = 200f
    var posX6 = 300f

    val hiloNieve = MovimientoNieve(this)

    val moviento = object: CountDownTimer(2000,80){
        override fun onTick(p0: Long) {
            posX1 -=10
            posX2 -=10
            posX3 -=10


            if (posX1 < -500) {
                posX1 = 1450f
                posX2 = 1550f
                posX3 = 1650f
            }
            invalidate()
        }

        override fun onFinish() {
            start()
        }
    }

    val moviento2 = object: CountDownTimer(2000,80){
        override fun onTick(p0: Long) {
            posX4 +=10
            posX5 +=10
            posX6 +=10


            if (posX4 > 2050) {
                 posX4 = 100f
                 posX5 = 200f
                 posX6 = 300f
            }
            invalidate()
        }

        override fun onFinish() {
            start()
        }
    }
    val moviento3 = object: CountDownTimer(2000,80){
        override fun onTick(p0: Long) {
            posX -=5

            if (posX < -500){
                posX = 1450f
            }

            invalidate()
        }

        override fun onFinish() {
            start()
        }
    }
    //constructor
    init {
        moviento.start()
        moviento2.start()
        moviento3.start()
        hiloNieve.start()
    }
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p = Paint()
        c.drawColor(Color.BLACK)
        //LUNA
        p.color = Color.WHITE;
        c.drawCircle( 200f, 170f ,90f ,p);
        p.color = Color.BLACK
        c.drawCircle(220f,175f, 70f,p)
        //nubes
        p.color = Color.rgb(215,215,215)

        c.drawCircle(posX1,700f,90f,p)
        c.drawCircle(posX2,700f,90f,p)
        c.drawCircle(posX3,700f,90f,p)
        c.drawCircle(posX2,650f,90f,p)


        c.drawCircle(posX4,400f,90f,p)
        c.drawCircle(posX5,400f,90f,p)
        c.drawCircle(posX6,400f,90f,p)
        c.drawCircle(posX5,350f,90f,p)


        //Motaña
        p.color = Color.GREEN
        c.drawOval(-100f,650f,1950f,1200f,p)

        //lapidas
        p.color = Color.DKGRAY
        c.drawCircle(235f,735f, 65f,p)
        c.drawRect(300f,700f,170f,950f,p)
        c.drawCircle(600f,730f, 50f,p)
        c.drawRect(550f,700f,650f,850f,p)
        c.drawCircle(1000f,735f, 90f,p)
        c.drawRect(900f,700f,1100f,950f,p)

        //altar
        p.color = Color.rgb(153,102,204)
        c.drawRect(1200f,700f,1700f,760f,p)
        c.drawRect(1250f,650f,1650f,700f,p)
        c.drawRect(1300f,600f,1600f,650f,p)
        c.drawRect(1350f,550f,1550f,610f,p)
        c.drawRect(1400f,500f,1500f,590f,p)
        p.color = Color.WHITE
        c.drawRect(1425f,500f,1475f,760f,p)
        p.color = Color.BLACK
        c.drawLine(1200f,700f,1700f,700f,p)
        c.drawLine(1250f,650f,1650f,650f,p)
        c.drawLine(1300f,600f,1600f,600f,p)
        c.drawLine(1350f,550f,1550f,550f,p)

        //decoraciones
        p.color = Color.rgb(166,123,91)
        c.drawCircle(1250f,740f, 20f,p)
        c.drawCircle(1650f,740f, 20f,p)
        c.drawCircle(1380f,680f, 20f,p)
        c.drawCircle(1550f,680f, 20f,p)
        p.color = Color.YELLOW
        c.drawCircle(1350f,630f, 18f,p)
        c.drawCircle(1510f,580f, 18f,p)
        p.color = Color.rgb(233,227,206)
        c.drawRect(1350f,720f,1370f,760f,p)
        c.drawRect(1550f,720f,1570f,760f,p)
        c.drawRect(1300f,660f,1320f,700f,p)
        c.drawRect(1600f,660f,1620f,700f,p)
        p.color = Color.rgb(0,170,228)
        c.drawRect(1520f,610f,1540f,650f,p)
        c.drawRect(1380f,560f,1400f,600f,p)
        //retrato
        p.color = Color.rgb(81,209,246)
        c.drawRect(1428f,450f,1472f,500f,p)
        p.color = Color.rgb(233,227,206)
        c.drawRect(1370f,480f,1390f,550f,p)
        c.drawRect(1510f,480f,1530f,550f,p)
        //cempasuchil
        p.color = Color.rgb(255,128,0)
        c.drawCircle(1240f,800f, 22f,p)
        c.drawCircle(1330f,820f, 22f,p)

        c.drawCircle(1450f,800f, 22f,p)

        c.drawCircle(1580f,820f, 22f,p)
        c.drawCircle(1690f,800f, 22f,p)

        c.drawBitmap(catrina, posX, 250f,p)

        (0..499).forEach {
            hiloNieve.nieve[it].pintarCopo(c)

        }
    }
}//clase lienzo

class MovimientoNieve(p:Lienzo): Thread(){
    val  puntero = p
    val nieve = ArrayList<Copo>()

    init{
        (1..500).forEach {
            val copo = Copo()
            nieve.add(copo)
        }
    }

    override fun run(){
        super.run()
        while (true){
            (0..499).forEach {
                //nieve[it].y += 5
                nieve[it].moverCopo()
            }
            puntero.principal.runOnUiThread {
                puntero.invalidate()
            }
            sleep(80)
        }
    }

}//moviento nieve