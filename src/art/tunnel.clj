(ns art.tunnel
  (:require [quil.core :as q]
            [quil.middleware]))

; (def sketch-width  810)
; (def sketch-height 540)
(def sketch-width 1080)
(def sketch-height 720)

(def colour [133 17 17])
(def fade-speed 10)
(def noise-speed 0.0091) ; TODO: add on each iteration instead of mult
(def noise-amp 250)
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
  (< size (* (q/width) 1.2)))


(defn render []
  (q/fill 0 fade-speed)
  (q/rect 0 0 (q/width) (q/height))
  (q/translate (/ (q/width) 2) (/ (q/height) 2))

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
  (q/background 0)
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
    :size [sketch-width sketch-height]
    ; :size :fullscreen ; compy can't take it ;-;
    ))
; (run)
