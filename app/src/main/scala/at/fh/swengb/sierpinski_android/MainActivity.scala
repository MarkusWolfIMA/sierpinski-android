package at.fh.swengb.sierpinski_android

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView

class MainActivity extends AppCompatActivity with SurfaceHolder.Callback {
  private[sierpinski_android] var surfaceView: SurfaceView = null
  private[sierpinski_android] var surfaceHolder: SurfaceHolder = null
  private[sierpinski_android] var cnvs: Canvas = null
  private[sierpinski_android] var paint: Paint = null
  private[sierpinski_android] var depth: Int = 0

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    surfaceView = findViewById(R.id.surfaceView).asInstanceOf[SurfaceView]
    surfaceHolder = surfaceView.getHolder
    surfaceHolder.addCallback(this)
    cnvs = new Canvas
    depth = 7
    paint = new Paint
    paint.setColor(Color.BLACK)
    paint.setStrokeWidth(2)
  }

  def surfaceCreated(holder: SurfaceHolder) {
    cnvs = holder.lockCanvas(null)
    cnvs.drawColor(Color.WHITE)
    val width: Double = surfaceView.getWidth
    val height: Double = Math.sqrt(Math.pow(width, 2) - Math.pow((width / 2), 2))
    drawSierpinski(0, (width / 2).round.toInt, 0, 0, height.round.toInt, width.round.toInt, height.round.toInt)
    holder.unlockCanvasAndPost(cnvs)
  }

  def surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
  }

  def surfaceDestroyed(holder: SurfaceHolder) {
  }

  private def drawTriangle(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int) {
    cnvs.drawLine(x1, y1, x2, y2, paint)
    cnvs.drawLine(x2, y2, x3, y3, paint)
    cnvs.drawLine(x3, y3, x1, y1, paint)
  }

  private def drawSierpinski(n: Int, x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int) {
    drawTriangle(x1, y1, x2, y2, x3, y3)
    if (n < depth) {
      if (n != 0) {
        drawSierpinski(n + 1, (x1 + x2) / 2 + (x2 - x3) / 2, (y1 + y2) / 2 + (y2 - y3) / 2, (x1 + x2) / 2 + (x1 - x3) / 2, (y1 + y2) / 2 + (y1 - y3) / 2, (x1 + x2) / 2, (y1 + y2) / 2)
        drawSierpinski(n + 1, (x3 + x2) / 2 + (x2 - x1) / 2, (y3 + y2) / 2 + (y2 - y1) / 2, (x3 + x2) / 2 + (x3 - x1) / 2, (y3 + y2) / 2 + (y3 - y1) / 2, (x3 + x2) / 2, (y3 + y2) / 2)
        drawSierpinski(n + 1, (x1 + x3) / 2 + (x3 - x2) / 2, (y1 + y3) / 2 + (y3 - y2) / 2, (x1 + x3) / 2 + (x1 - x2) / 2, (y1 + y3) / 2 + (y1 - y2) / 2, (x1 + x3) / 2, (y1 + y3) / 2)
      }
      else {
        drawSierpinski(1, (x1 + x2) / 2, (y1 + y2) / 2, (x1 + x3) / 2, (y1 + y3) / 2, (x2 + x3) / 2, (y2 + y3) / 2)
      }
    }
  }
}
