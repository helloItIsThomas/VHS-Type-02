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
//        val v0 = loadSVG("data/fonts/tuff_shed_glyphs_svg/v.svg")
//        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
//        val shapeNodesT = t0.findShapes()
//        val shapeNodesU = u0.findShapes()
//        val shapeNodesF0 = f0.findShapes()
//        val shapeNodesF1 = f1.findShapes()
//        val shapeNodesV0 = v0.findShapes()
//        val tuffArr = mutableListOf<List<ShapeNode>>()
//        tuffArr.add(shapeNodesT)
//        tuffArr.add(shapeNodesU)
//        tuffArr.add(shapeNodesF0)
//        tuffArr.add(shapeNodesF1)
//        tuffArr.add(shapeNodesV0)
//
//        val numPoints = 2.0
//        val numPoints2 = 40
//        extend {
//            drawer.clear(ColorRGBa.BLACK)
//            animation(((frameCount * 0.01) ) % 2.0)
//            drawer.translate(100.0, 100.0)
//            drawer.stroke = ColorRGBa.PINK
//            drawer.strokeWeight = 1.0
//
//            shapeNodesV0.forEachIndexed { i, e ->
//                e.shape.contours.forEachIndexed {  i2, n  ->
//                    for(u in 0.. numPoints.toInt())
//                        drawer.roundedRectangle(
//                            n.equidistantPositions(numPoints2,2.0)[((frameCount*2.5) % numPoints2).toInt()],
//                            10.0, 5.0, 10.0)
//                }
//            }
//        }
//    }
//}