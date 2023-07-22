

import demos.classes.Animation
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extra.keyframer.Keyframer
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.shapes.RoundedRectangle
import org.openrndr.extra.shapes.grid
import org.openrndr.extra.shapes.roundedRectangle
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2
import org.openrndr.math.map
import org.openrndr.shape.*
import org.openrndr.svg.loadSVG
import java.io.File


fun main() = application {
    configure {
        width = 608
        height = 342
        hideWindowDecorations = true
        windowAlwaysOnTop = true
        position = IntVector2(1285,110)
        windowTransparent = true
    }



    program {
        val animation = Animation()
        val t0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/t.svg")
        val u0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/u.svg")
        val f0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/f.svg")
        val f1 = loadSVG("data/fonts/tuff_shed_glyphs_svg/f2.svg")
        val h0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/h.svg")
        val v0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/v.svg")
        val s0: Composition = loadSVG("data/fonts/tuff_shed_glyphs_svg/s.svg")
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
        val shapeNodesT: List<ShapeNode> = t0.findShapes()
        val shapeNodesU = u0.findShapes()
        val shapeNodesF0 = f0.findShapes()
        val shapeNodesF1 = f1.findShapes()
        val shapeNodesV0 = v0.findShapes()
        val shapeNodesH0 = h0.findShapes()
        val shapeNodesS0 = s0.findShapes()
        val tuffArr = mutableListOf<List<ShapeNode>>()
//        tuffArr.add(shapeNodesT)
        tuffArr.add(shapeNodesV0)
        tuffArr.add(shapeNodesH0)
        tuffArr.add(shapeNodesS0)
//        tuffArr.add(shapeNodesU)
//        tuffArr.add(shapeNodesF1)

        val columnCount = 1
        val rowCount = 75
        val marginX = -50.0
        val marginY = -50.0
        val gutterX = 2.0
        val gutterY = 0.0

        var grid: List<List<Rectangle>> = drawer.bounds.grid(columnCount, rowCount, marginX, marginY, gutterX, gutterY)
        val flatgrid = grid.flatten()

        class RectUnit(var pos: Vector2, var w: Double, var h: Double, var r: Double) : Keyframer(){
            val thisSlider by DoubleChannel("pathSlider")
            var thisRect = RoundedRectangle(pos, w, h, r)

            fun update(){
                thisRect = RoundedRectangle(
                    pos - (pos*(Vector2(thisSlider.map(
                        0.0,
                        1.0,
                        0.0,
                        0.1
                    ), 0.0))),
                    w * thisSlider,
                    h,
                    r
                )
            }
        }

        val segments = mutableListOf<RectUnit>()

        flatgrid.forEach { u ->
            u.contour.segments.forEach { s->
                tuffArr.forEachIndexed{ i, t ->
                    t.forEach { r ->
                        s.intersections(r.shape).forEach{ o ->
                            segments.add(RectUnit(
                                o.position + Vector2(80.0 * i, 0.0),
                                15.0,
                                (height / rowCount).toDouble() - gutterX,
                                10.0)
                            )
                        }
                    }
                }
            }
        }

        segments.forEach { seg ->
            seg.loadFromJson(File(
                "data/keyframes/keyframes-0.json"
            ))
        }

//        extend(ScreenRecorder()) {
//            contentScale = 2.0
//            frameRate = 60
//            maximumDuration =  10.0
//        }

        extend {
            drawer.clear(ColorRGBa.TRANSPARENT)
            animation(((frameCount * 0.01) ) % 2.0)
            drawer.translate(50.0, 200.0)

//            drawer.fill = null
//            drawer.stroke = ColorRGBa.GREEN
//            flatgrid.forEach{ r -> drawer.rectangle(r) }

            drawer.stroke = null
            drawer.fill = ColorRGBa.WHITE
            drawer.strokeWeight = 0.25
            segments.forEachIndexed { index, n->
                n(( frameCount*0.05 - (index*0.0095)) % 2.0)
                n.update()
                println(n.thisSlider)
                drawer.roundedRectangle(n.thisRect)
            }
        }
    }
}