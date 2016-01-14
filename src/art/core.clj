(ns art.core
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
  ; (render)
  ; (q/save "flower.png")
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
