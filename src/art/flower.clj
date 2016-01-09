(ns art.flower
  (:require [quil.core :as q]
            [quil.middleware]))

(defn setup []
  0)

(defn update-state [n]
  (+ n 0.1) 0)

(defn draw [n]
  (q/fill 0 0)
  (q/stroke 255)
  (q/stroke-weight 1)
  (q/background 0)
  (q/translate (/ (q/width)  2)
               (/ (q/height) 2))

  (let [x  (/ (q/height) 2.5)
        -x (- 0 x)
        x2 (* x 2)
        sections 8]

    (q/rotate (q/radians (/ 360 (* sections 2))))

    (dotimes [_ sections]
      (q/rotate (q/radians 45))
      (q/ellipse 0 -x  x x2)
      (q/ellipse 0 -x x2  x)
      (q/ellipse 0 -x x2 x2)
      ))
  ; (q/save "flower.png")
  )


(q/defsketch example
  :title "Art!"
  :middleware [quil.middleware/fun-mode]
  :settings #(q/smooth 2)
  :setup setup
  :update update-state
  :draw draw
  ; :size [540 350]
  :size [1080 720]
  )
