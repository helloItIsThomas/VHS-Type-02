//
//
//import demos.classes.Animation
//import org.openrndr.application
//import org.openrndr.color.ColorRGBa
//import org.openrndr.extra.olive.oliveProgram
//import org.openrndr.extra.shapes.roundedRectangle
//import org.openrndr.math.IntVector2
//import org.openrndr.shape.ShapeNode
//import org.openrndr.svg.loadSVG
//import java.io.File
//
//
//fun main() = application {
//    configure {
//        width = 608
//        height = 342
//        hideWindowDecorations = true
//        windowAlwaysOnTop = true
//        position = IntVector2(1285,110)
//    }
//    oliveProgram {
//        val animation = Animation()
//        val t0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/t.svg")
//        val u0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/u.svg")
//        val f0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/f.svg")
//        val f1 = loadSVG("data/fonts/tuff_shed_glyphs_svg/f2.svg")
//        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
//        val shapeNodesT = t0.findShapes()
//        val shapeNodesU = u0.findShapes()
//        val shapeNodesF0 = f0.findShapes()
//        val shapeNodesF1 = f1.findShapes()
//        val tuffArr = mutableListOf<List<ShapeNode>>()
//        tuffArr.add(shapeNodesT)
//        tuffArr.add(shapeNodesU)
//        tuffArr.add(shapeNodesF0)
//        tuffArr.add(shapeNodesF1)
//
//        val numPoints = 8.0
//        extend {
//            drawer.clear(ColorRGBa.BLACK)
//            animation(((frameCount * 0.01) ) % 2.0)
//            drawer.translate(0.0, 100.0)
////            drawer.composition(f0)
//            drawer.stroke = null
//
//            tuffArr.forEachIndexed { i0, t->
//                drawer.translate(100.0, 0.0)
//                t.forEachIndexed { i, e ->
//                    e.shape.contours.forEachIndexed {  i2, n  ->
//                        for(u in 0.. numPoints.toInt())drawer.roundedRectangle(
//                            (n.position(u / numPoints).x),
//                            n.position(u / numPoints).y,
//                            10.0,
//                            5.0,
//                            10.0
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//}