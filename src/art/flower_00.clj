(ns art.flower-00
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(defn flower [n]
  (let [parts 9
        portion (q/radians (/ 360 parts))
        half (/ n 2)]
    (doseq [_ (range parts)]
      (q/rotate portion)
      (q/ellipse half 0 n n))))


(defn render []
  (q/background 0)
  (q/translate (/ width 2) (/ height 2))
  (doseq [n (range 1 30)]
    (flower (* n 25))))



(defn setup []
  (q/fill 0 0)
  (q/stroke 255)
  (q/stroke-weight 1)
  (render)
  ; (q/save "flower.png")
  )

(defn step [state] state)

(defn draw [state]
  ; (render)
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
