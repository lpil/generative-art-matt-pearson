(ns art.moire-triangles-01
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def len (* width 0.1))
(defn tri-width [] (* len 1.735))



(defn triangle []
  (q/begin-shape)
  (doseq [angle [0 120 240]]
    (q/vertex (* len (Math/sin (q/radians angle)))
              (* len (Math/cos (q/radians angle)))))
  (q/end-shape :close))


(defn row [nx]
  (let [half    (* 0.5 nx)
        rng     (range (- half) (+ 1 half))
        width   (tri-width)
        offsets (map #(* width %) rng)]
    (doseq [offset offsets]
      (q/with-translation [offset 0] (triangle)))))


(defn render []
  (q/background 0)
  (q/stroke-weight 0)
  (q/translate (* 0.5 (q/width)) (* 0.5 (q/height)))
  (q/rotate (q/radians (q/frame-count)))


  (row 6)
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
(run)
