import { useEffect, useRef, useState, type MouseEvent } from "react"
import SideBar from "../widgets/Sidebar"
import ColorPicker from "./toolbar/ColorPicker"
import { color2string, hsv2Hsl, type HsvColor } from "../utils/color/ColorUtils"

function Paint() {
    const canvasRef = useRef<HTMLCanvasElement>(null)

    const [color, setColor] = useState<HsvColor>({ h: 0, s: 100, v: 100 })

    let isDrawing = false
    let drawingArr: number[][] = []

    const [drawTip, setDrawTip] = useState(true)

    useEffect(() => {
        if (drawTip === true) {
            return
        }
        const canvas = canvasRef.current!

        const ctx = canvas.getContext("2d")!

        ctx.fillStyle = "#fff"
        ctx.fillRect(0, 0, canvas.width, canvas.height)
    }, [drawTip])

    let mouseEvent = (e: MouseEvent) => {
        const canvas = canvasRef.current!
        const ctx = canvas.getContext("2d")!

        if (e.type === "mousedown") {
            if (drawTip === true) {
                setDrawTip(false)
            }
            isDrawing = true
            drawingArr = []
            return
        }

        if (e.type === "mousemove" && isDrawing) {

            const rect = canvas.getBoundingClientRect()
            const scaleX = canvas.width / rect.width
            const scaleY = canvas.height / rect.height
            const y = (e.clientY - rect.top) * scaleX
            const x = (e.clientX - rect.left) * scaleY

            drawingArr.push([x, y])

            ctx.lineJoin = "round"
            ctx.lineWidth = 5
            ctx.strokeStyle = color2string(hsv2Hsl(color))
            ctx.beginPath()
            drawingArr.length > 1 && ctx.moveTo(drawingArr[drawingArr.length - 2][0], drawingArr[drawingArr.length - 2][1])
            ctx.lineTo(drawingArr[drawingArr.length - 1][0], drawingArr[drawingArr.length - 1][1])
            ctx.closePath()
            ctx.stroke()
        }

        if (e.type === "mouseup") {
            isDrawing = false
        }
    }

    return (
        <>
            <div
                style={{
                    position: "relative",
                    maxWidth: "100%",
                    maxHeight: "80vh",

                    backgroundColor: "#d4d4d4ff",

                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                }}
            >
                <canvas
                    ref={canvasRef}
                    onMouseDown={mouseEvent}
                    onMouseMove={mouseEvent}
                    onMouseUp={mouseEvent}
                    width={1920} height={1080}
                    style={{
                        maxWidth: "100%",
                        maxHeight: "80vh",
                        border: "1px solid #9c9c9cff",
                    }}>
                </canvas>
                { drawTip && (
                    <div
                        style={{
                            position: "absolute",
                            color: "black",
                            fontSize: "32px",
                        }}
                    >
                        Draw SomeThing Here...
                    </div>
                )}
            </div>

            <SideBar
                size={"20vh"}
                side={"bottom"}
                background={"linear-gradient(120deg, #2f3f4fff, #555555ff)"}
                hasLabel={false}
                isOpen={true}
            >
                <div
                    style={{
                        border: "1px solid #5e5e5eff",
                        padding: "20px"
                    }}
                >
                    <ColorPicker
                        size={200}
                        color={color}
                        setColor={setColor}
                    >
                    </ColorPicker>

                </div>
            </SideBar>
        </>
    )
}

export default Paint