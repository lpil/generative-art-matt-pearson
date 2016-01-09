(ns art.flag-two
  (:require [quil.core :as q]
            [quil.middleware]))

(defn setup []
  1
  )

(defn update-state [state]
  (+ 1 state)
  )

(defn draw [state]
  (q/background 0 101 189)
  (q/stroke-weight 30)
  (q/stroke 255)

  (q/translate (/ (q/width) 2) (/ (q/height) 2))
  (q/rotate (/ state 100))
  (q/line -161 -100 161 100)
  (q/line -161 100 161 -100)
  (q/line 0 200 0 -200)
  )


(defn run [] 
  (q/defsketch flag-two
    :title "Art!"
    :middleware [quil.middleware/fun-mode]
    :settings #(q/smooth 2)
    :setup setup
    :update update-state
    :draw draw
    :size [323 200]))
; (run)
