(ns art.moire-triangles-01
  (:require [quil.core :as q]
            [quil.middleware]))

(def width  405)
(def height 270)
; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def smallness 35)
(defn len        [] (/ width smallness))
(defn tri-width  [] (* (len) 1.735))
(defn tri-height [] (* (len) 1.51))


(defn record! []
  ; (if (<= (q/frame-count) 360)
  (if (<= (q/frame-count) 720)
    (q/save-frame "frame-####.png")
    (q/exit)))



(defn triangle []
  (q/begin-shape)
  (doseq [angle [0 120 240]]
    (q/vertex (* (len) (Math/sin (q/radians angle)))
              (* (len) (Math/cos (q/radians angle)))))
  (q/end-shape :close))


(defn row [nx]
  (let [half    (* 0.5 nx)
        rng     (range (- half) (+ 1 half))
        width   (tri-width)
        offsets (map #(* width %) rng)]
    (doseq [offset offsets]
      (q/with-translation [offset 0] (triangle)))))

(defn grid [nx ny]
  (let [half       (* 0.5 ny)
        rng        (range (- half) (+ 1 half))
        height     (tri-height)
        scaled     (map #(* height %) rng)
        half-width (* 0.5 (tri-width))
        bumps      (cycle [0 half-width])
        offsets    (map vector bumps scaled)]
    (doseq [[x y] offsets]
      (q/with-translation [x y] (row nx))
      )))


(defn render []
  (q/background 255)
  (q/fill 0)
  (q/stroke 0)
  (q/stroke-weight 0)

  (q/translate (* 0.5 (q/width)) (* 0.5 (q/height)))

  (grid 20 16)
  (q/rotate (q/radians (* 0.5 (q/frame-count))))
  (grid 22 28)
  )

(defn setup []
  ; (render)
  )

(defn step [state] state)

(defn draw [state]
  (render)
  (record!)
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
