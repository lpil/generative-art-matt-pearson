(ns art.tunnel
  (:require [quil.core :as q]
            [quil.middleware]))

; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def colour [133 17 17])
(def fade-speed 10)
(def noise-speed 0.009)
(def noise-amp 150)
(def step-size 40)

(defn to-noise [n]
  (-> (q/frame-count)
      (+ n)
      (* noise-speed)
      (quil.core/noise)
      (* noise-amp)))

(defn stepped-sizes []
  (map #(* step-size %) (range)))

(defn on-screen? [size]
  (< size (* width 1.2)))


(defn render []
  (q/fill 0 fade-speed)
  (q/rect 0 0 width height)
  (q/translate (/ width 2) (/ height 2))

  (let [noises (map to-noise (range))
        noisey-sizes (map + noises (stepped-sizes))
        values (take-while on-screen? noisey-sizes)]
    (apply q/stroke colour)
    (doseq [size values]
      (q/fill 0 0)
      (q/ellipse 0 0 size size))))

(defn setup []
  (q/fill 0 0)
  (q/stroke 255)
  (q/stroke-weight 1)
  ; (render)
  ; (q/save "flower.png")
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
