(ns art.moire-circles-00
  (:require [quil.core :as q]
            [quil.middleware]))

; en.wikipedia.org/wiki/Moir%C3%A9_pattern#/media/File:Moire_Circles.svg

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def step-size 33)


(defn circles [max-size]
  (let [sizes (->> (range)
                   (map #(* step-size %))
                   (drop 1)
                   (take-while #(< % max-size)))]
    (doseq [size sizes]
      (q/ellipse 0 0 size size))))


(defn render []
  (q/stroke 240)
  (q/stroke-weight 5)

  (q/translate 0 (/ (q/height) 2))
  (q/background 0)

  (let [left 64.5
        right (* left 2)
        max-size (* 1.6 (q/height))]

    (-> (q/height)
        (/ 2) 
        (+ left)
        (q/translate 0))
    (circles max-size)

    (-> (q/height)
        (/ 2) 
        (- right)
        (q/translate 0))
    (circles max-size)
    ))

(defn setup []
  (q/fill 0 0)
  (render)
  (q/save "moire.png")
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
