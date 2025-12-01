import type { CSSProperties, PropsWithChildren } from "react"
import "./Button.css"

interface ButtonProps extends PropsWithChildren {
    x: number,
    y: number,
    width: number,
    height: number,
}

function Button(
    {x, y, width, height, children }: ButtonProps
) {
    const style = {
        "--x": `${x}%`,
        "--y": `${y}%`,
        width: `${width}%`,
        height: `${height}%`,
    } as CSSProperties

    return (
        <>
            <button className="btn btn-basic"
                style={style}
            >
                {children}
            </button>
        </>
    )
}

export default Button