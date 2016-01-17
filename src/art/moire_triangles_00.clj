(ns art.moire-triangles-00
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def tri-width (* width 0.1))
(def tri-height (* (Math/sqrt 3) 0.5 tri-width))

(defn rotate-about-center [radians]
  (q/with-translation [(* 0.5 (q/width)) (* 0.5 (q/height))]
    (q/rotate radians)))

(defn triangle []
  (q/triangle 0 tri-height,
              (* 0.5 tri-width) 0,
              tri-width tri-height))


(defn triangle-row [n]
  (doseq [x (->> (range n) (map #(* tri-width %)))]
    (q/with-translation [x 0] (triangle))))

(defn triangle-block [nx ny]
  (doseq [y (->> (range ny) (map #(* tri-height %)))]
    (q/with-translation [0 y] (triangle-row nx)
      )))


(defn render []
  (q/background 0)
  (q/stroke-weight 0)
  (triangle-block 15 10)
  )

(defn setup []
  (q/fill 255)
  (q/stroke 255)
  (q/stroke-weight 1)
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
; (run)
