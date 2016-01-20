(ns art.circle-grid
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width  702)
(def height 468)
; (def width 1080)
; (def height 720)

(def dot-radius 10)
; (def radius 56.8)
(def radius 65.0)
(def x-size 15)
(def y-size 10)
(def speed 7)

(defn record! []
  (if (<= (q/frame-count) (/ 360 speed))
    (q/save-frame "frame-####.gif")
    (q/exit)))

(defn points [step-size x-size y-size]
  (for [x (range x-size)
        y (range y-size)]
    (map #(* step-size %) [x y])))


(defn orbit [x y n]
  (q/with-translation [x y]
    (q/fill 0 0)
    (q/ellipse 0 0 radius radius)
    (q/fill 255)
    (let [degrees (+ x y (* speed n))]
      (q/with-rotation [(q/radians degrees)]
        (q/ellipse (* 0.5 radius) 0 dot-radius dot-radius)))))


(defn render []
  (q/background 0)
  (q/translate -11 -11)
  (doseq [[x y] (points radius x-size y-size)]
    (let [half (* 0.5 radius)
          xx (+ half x)
          yy (+ half y)
          step (q/frame-count)]
      (orbit x y step)
      (orbit xx yy step)))
  (record!)
  )


(defn setup []
  (q/fill 0 0)
  (q/stroke 255)
  (q/stroke-weight 1))

(defn step [state] state)

(defn draw [state] (render))

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
