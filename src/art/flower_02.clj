(ns art.flower-02
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(defn flower [n]
  (let [parts 1
        portion (q/radians (/ 360 parts))
        half (/ n 2)]
    (doseq [_ (range parts)]
      (q/ellipse 0 half n n)
      (q/rotate portion)
      )))


(defn render []
  (q/background 0)
  (q/translate (/ width 2) (/ height 2))

  (let [sizes (range 1 1337 100)
        num-flowers 25
        num-rings (- (* num-flowers 2) 1)
        flower-sizes (take num-flowers sizes)
        ring-sizes (take num-rings sizes)]

    (q/stroke-weight 1)
    (doseq [size flower-sizes] (flower size))
    (q/stroke-weight 1)
    (doseq [size ring-sizes] (q/ellipse 0 0 size size))

    (q/display-filter :blur 1)
    (q/stroke-weight 1)
    (doseq [size flower-sizes] (flower size))
    (q/stroke-weight 1.5)
    (doseq [size ring-sizes] (q/ellipse 0 0 size size))
    ))



(defn setup []
  (q/fill 0 0)
  (q/stroke 255)
  (q/stroke-weight 1)
  (render)
  (q/save "flower.png")
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
; (run)
