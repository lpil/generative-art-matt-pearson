(ns art.teardrop-rainbow
  (:require [quil.core :as q]
            [quil.middleware]))

(def width  810)
(def height 540)
; (def width 1080)
; (def height 720)

(defn random-color []
  (+ 40 (rand-int 215)))

(defn teardrop [x y size]
  (let [half (* 0.5 size)]
    (q/arc x y size size (q/radians 0) (q/radians 180))
    (q/triangle (- x half) y
                x (- y size)
                (+ x half) y)))

(defn render []
  (let [x (rand-int (q/width))
        y (rand-int (q/height))
        r (random-color)
        g (random-color)
        b (random-color)]
    (q/fill r g b)
    (teardrop x y 50)
    ))

(defn setup []
  (q/fill 0 0)
  (q/no-stroke)
  (q/background 255)
  ; (render)
  )

(defn step [state] state)

(defn draw [state]
  (render)
  )

(defn run []
  (q/defsketch example
    :title "Art!"
    :middleware [quil.middleware/fun-mode]
    :settings #(q/smooth 2)
    :setup setup
    :update step
    :draw draw
    :size [width height]
    ))
(run)
