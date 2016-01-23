(ns art.sj
  (:require [quil.core :as q]
            [quil.middleware]))

(def width  810)
(def height 540)
; (def width 1080)
; (def height 720)

(defn setup []
  (q/fill 0 0)
  (q/stroke 255)
  (q/stroke-weight 1)
  )

(defn step [state] state)

(defn draw [state]
  (q/background 0)
  (q/translate (* 0.5 (q/width)) (* 0.5 (q/height)))
  (q/stroke-weight 3)
  (q/color-mode :hsb)

  (doseq [n (range 35)]
    (let [size (* n 30)
          hue (mod (+ (* 5 n) (q/frame-count)) 255)
          step (* (q/frame-count) 0.003)
          noise (q/noise step)
          new-size (* size noise 5)
          ]
      (q/stroke hue 150 150)
      (q/ellipse 0 0 new-size new-size))))

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
