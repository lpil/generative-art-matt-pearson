(ns art.moire-lines-00
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def width-ratio 0.5)
(def num-lines 20)


(defn lines [x y n]
  (let [step (/ x n)
        half-step (* 0.5 step)
        width (* step width-ratio)
        x-nudge (* 0.5 (- width))
        x-offset (+ x-nudge (* -0.5 x))
        y-offset (* -0.5 y)
        ]



    (q/with-translation [x-offset y-offset]
      (doseq [i (range n)]
        (q/rect 0 0 width y)
        (q/translate step 0))
      )))

(defn record! [n]
  (cond (> 0 n)            nil
        (<= n (* 360 2.5)) (q/save-frame "frame-####.png")
        :else              (q/exit)))


(defn render [n]
  (q/fill 0 50)
  (q/rect 0 0 (q/width) (q/height))
  (q/stroke-weight 0)
  (q/fill 255 50)

  (q/with-translation [(* (q/width) 0.5) (* (q/height) 0.5)]
    (let [rotation (q/radians (* 0.2 n))
          size (* 1.3 (q/width))]
      ; Static lines
      (lines width height num-lines)
      ; Dynamic lines

      (doseq [n (range 1 4)] ; 4 dynamic
        (q/rotate (* n rotation))
        (lines size size num-lines))))
  (record! n)
  )

(defn setup []
  (q/fill 255)
  (q/stroke 255)
  (q/stroke-weight 2)
  -15
  )

(defn step [n]
  ; (* 360 2.5)
  ; 0
  (inc n)
  )

(defn draw [state]
  (render state)
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
