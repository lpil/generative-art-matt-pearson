(ns art.flag
  (:require [quil.core :as q]
            [quil.middleware]))

(defn setup []
  )

(defn update-state [_]
  )

(defn draw [_]
  (q/background 0 101 189)
  (q/stroke-weight 30)
  (q/stroke 255)
  (q/line 0 0 (q/width) (q/height))
  (q/line (q/width) 0 0 (q/height))
  )


(q/defsketch example
  :title "Art!"
  :middleware [quil.middleware/fun-mode]
  :settings #(q/smooth 2)
  :setup setup
  :update update-state
  :draw draw
  :size [323 200])
